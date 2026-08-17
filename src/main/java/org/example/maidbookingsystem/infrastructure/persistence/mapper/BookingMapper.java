package org.example.maidbookingsystem.infrastructure.persistence.mapper;

import org.example.maidbookingsystem.domain.Booking;
import org.example.maidbookingsystem.domain.BookingStatus;
import org.example.maidbookingsystem.infrastructure.persistence.entity.BookingEntity;
import org.example.maidbookingsystem.infrastructure.persistence.entity.BookingSeriesEntity;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {
    public Booking toDomain(BookingEntity entity) {
        return new Booking(
                entity.getId(), entity.getCustomerId(), entity.getMaidId(),
                entity.getSeries() == null ? null : entity.getSeries().getId(),
                entity.getStartAt(), entity.getEndAt(), entity.getAmount(),
                entity.getCurrency(), entity.getStatus()
        );
    }

    public BookingEntity toEntity(Booking booking, BookingSeriesEntity series) {
        BookingEntity entity = new BookingEntity(
                booking.id(), booking.customerId(), booking.maidId(), series,
                booking.startAt(), booking.endAt(), booking.amount(), booking.currency()
        );
        applyStatus(entity, booking.status());
        return entity;
    }

    private void applyStatus(BookingEntity entity, BookingStatus status) {
        if (status == BookingStatus.CONFIRMED) entity.confirm();
        if (status == BookingStatus.PAYMENT_FAILED) entity.markPaymentFailed();
        if (status == BookingStatus.CANCELLED) entity.cancel();
    }
}