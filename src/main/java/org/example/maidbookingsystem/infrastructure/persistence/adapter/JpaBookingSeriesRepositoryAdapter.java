package org.example.maidbookingsystem.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.example.maidbookingsystem.application.booking.BookingSeriesRepository;
import org.example.maidbookingsystem.domain.BookingSeries;
import org.example.maidbookingsystem.infrastructure.persistence.mapper.BookingSeriesMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaBookingSeriesRepositoryAdapter implements BookingSeriesRepository {
    private final SpringDataBookingSeriesRepository repository;
    private final BookingSeriesMapper mapper;

    @Override
    public BookingSeries save(BookingSeries series) {
        return mapper.toDomain(repository.save(mapper.toEntity(series)));
    }

    @Override
    public Optional<BookingSeries> findById(UUID seriesId) {
        return repository.findById(seriesId).map(mapper::toDomain);
    }
}