package models.response.createBooking;

public record Booking(
        String firstname,
        String lastname,
        double totalprice,
        boolean depositpaid,
        BookingDates bookingdates,
        String additionalneeds
) {
}
