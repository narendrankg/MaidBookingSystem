package org.example.maidbookingsystem.domain;

import java.time.Instant;

public interface ClockProvider {
    Instant now();
}