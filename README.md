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

```

## REQUESTS

```text

Maid onboarding:

curl -X POST http://localhost:8080/maids \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Anita Sharma",
    "locality": "Indiranagar",
    "services": ["CLEANING"],
    "skills": ["deep-cleaning"],
    "hourlyPrice": 250.00,
    "gender": "FEMALE",
    "workingHours": [{"day":"MONDAY","startTime":"09:00","endTime":"18:00"}]
  }'

Booking Search:

curl -X POST http://localhost:8080/maids/search \
  -H "Content-Type: application/json" \
  -d '{
    "locality": "Indiranagar",
    "minimumRating": 4.0,
    "maximumHourlyPrice": 300,
    "startAt": "2026-08-17T10:00:00Z",
    "endAt": "2026-08-17T12:00:00Z"
  }'

Booking Creation:

curl -X POST http://localhost:8080/bookings \
  -H "Content-Type: application/json" \
  -d '{
    "customerId": "11111111-1111-1111-1111-111111111111",
    "maidId": "{maidId}",
    "startAt": "2026-08-17T10:00:00Z",
    "endAt": "2026-08-17T12:00:00Z",
    "amount": 500.00,
    "currency": "INR"
  }'

Booking cancellation:

curl -X DELETE http://localhost:8080/bookings/{bookingId}/cancellation