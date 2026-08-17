package org.example.maidbookingsystem.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.example.maidbookingsystem.application.booking.BookingRepository;
import org.example.maidbookingsystem.domain.Booking;

import org.example.maidbookingsystem.infrastructure.persistence.entity.BookingSeriesEntity;
import org.example.maidbookingsystem.infrastructure.persistence.mapper.BookingMapper;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaBookingRepositoryAdapter implements BookingRepository {
    private final SpringDataBookingRepository repository;
    private final SpringDataBookingSeriesRepository seriesRepository;
    private final BookingMapper mapper;

    @Override
    public Booking save(Booking booking) {
        BookingSeriesEntity series = booking.seriesId() == null ? null :
                seriesRepository.getReferenceById(booking.seriesId());
        return mapper.toDomain(repository.save(mapper.toEntity(booking, series)));
    }

    @Override
    public Optional<Booking> findById(UUID bookingId) {
        return repository.findById(bookingId).map(mapper::toDomain);
    }

    @Override
    public boolean existsOverlappingActiveBooking(UUID maidId, Instant startAt, Instant endAt) {
        return repository.existsActiveOverlap(maidId, startAt, endAt);
    }

    @Override
    public List<Booking> findBySeriesId(UUID seriesId) {
        return repository.findBySeries_Id(seriesId).stream().map(mapper::toDomain).toList();
    }
}