package org.example.maidbookingsystem.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.maidbookingsystem.domain.BookingStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "bookings", indexes = {
    @Index(name = "idx_booking_maid_time", columnList = "maidId,startAt,endAt")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookingEntity {
    @Id
    private UUID id;
    private UUID customerId;
    private UUID maidId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id")
    private BookingSeriesEntity series;

    private Instant startAt;
    private Instant endAt;
    private BigDecimal amount;
    private String currency;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @Version
    private long version;

    public BookingEntity(UUID id, UUID customerId, UUID maidId, BookingSeriesEntity series,
                         Instant startAt, Instant endAt, BigDecimal amount, String currency) {
        if (!endAt.isAfter(startAt)) {
            throw new IllegalArgumentException("End must be after start");
        }
        this.id = id;
        this.customerId = customerId;
        this.maidId = maidId;
        this.series = series;
        this.startAt = startAt;
        this.endAt = endAt;
        this.amount = amount;
        this.currency = currency;
        this.status = BookingStatus.PENDING_PAYMENT;
    }

    public void confirm() {
        this.status = BookingStatus.CONFIRMED;
    }

    public void markPaymentFailed() {
        this.status = BookingStatus.PAYMENT_FAILED;
    }

    public void cancel() {
        this.status = BookingStatus.CANCELLED;
    }
}
