package org.example.maidbookingsystem.domain;

public interface NotificationSender {
    void bookingConfirmed(Booking booking);
    void bookingCancelled(Booking booking);
}