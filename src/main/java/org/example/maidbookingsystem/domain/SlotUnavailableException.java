package org.example.maidbookingsystem.domain;

public class SlotUnavailableException extends RuntimeException {
    public SlotUnavailableException() {
        super("The selected time slot is unavailable");
    }
}
