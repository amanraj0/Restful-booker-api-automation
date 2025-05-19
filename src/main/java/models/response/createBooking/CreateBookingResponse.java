package models.response.createBooking;

public record CreateBookingResponse(
        int bookingid,
        Booking booking
) {
}
