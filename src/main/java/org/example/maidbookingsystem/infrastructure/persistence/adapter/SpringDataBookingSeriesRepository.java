package org.example.maidbookingsystem.infrastructure.persistence.adapter;

import org.example.maidbookingsystem.infrastructure.persistence.entity.BookingSeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataBookingSeriesRepository
    extends JpaRepository<BookingSeriesEntity, UUID> {}