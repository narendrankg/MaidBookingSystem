# Maid Booking System LLD

## Requirements

- Onboard a maid with profile details and working hours.
- Search maids by locality, service, skills, price, rating, and availability.
- Check whether a maid is available for a requested time slot.
- Create a booking for a maid and customer.
- Cancel an existing booking.
- Prevent two overlapping bookings for the same maid.

## Core Entities

```text
BookingSystem
  maidsById: Map<UUID, Maid>
  bookingsById: Map<UUID, Booking>

Maid
  id, name, locality, services, skills, hourlyPrice, gender
  workingHours: List<WorkingHours>
  bookingIds: Set<UUID>

Booking
  id, customerId, maidId, slot, amount, currency, status

WorkingHours
  day, startTime, endTime

BookingSlot
  startAt, endAt
