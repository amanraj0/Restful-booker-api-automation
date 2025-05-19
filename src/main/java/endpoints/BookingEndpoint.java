package endpoints;

public enum BookingEndpoint {
    BOOKING("/booking"),
    UPDATE_BOOKING("/booking/{bookingId}");

    private final String endpoint;

    BookingEndpoint(String endpoint){
        this.endpoint = endpoint;
    }

    public String getEndpoint(){
        return endpoint;
    }
}
