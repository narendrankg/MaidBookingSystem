package org.example.maidbookingsystem.application.booking;

import java.time.*;
import java.util.*;

public interface RecurrenceRule {
    List<Instant> occurrences(Instant from, Instant until);
}

