package org.example.maidbookingsystem.infrastructure.persistence.adapter;

import org.example.maidbookingsystem.infrastructure.persistence.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface SpringDataBookingRepository extends JpaRepository<BookingEntity, UUID> {

    @Query("""
        select count(b) > 0 from BookingEntity b
        where b.maidId = :maidId
          and b.status in ('PENDING_PAYMENT', 'CONFIRMED')
          and b.startAt < :endAt
          and b.endAt > :startAt
        """)
    boolean existsActiveOverlap(UUID maidId, Instant startAt, Instant endAt);

    List<BookingEntity> findBySeries_Id(UUID seriesId);
}
