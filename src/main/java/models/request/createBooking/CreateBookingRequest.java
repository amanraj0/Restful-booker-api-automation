package models.request.createBooking;

public class CreateBookingRequest {

    private String firstname;
    private String lastname;
    private double totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

    public CreateBookingRequest() {
    }

    public CreateBookingRequest(String firstname, String lastname, double totalprice, boolean depositpaid, BookingDates bookingdates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    public static class Builder{
        private String firstname;
        private String lastname;
        private double totalprice;
        private boolean depositpaid;
        private BookingDates bookingdates;
        private String additionalneeds;

        public Builder(){

        }

        public Builder firstname(String firstname){
            this.firstname = firstname;
            return this;
        }

        public Builder lastname(String lastname){
            this.lastname = lastname;
            return this;
        }

        public Builder totalprice(double totalprice){
            this.totalprice = totalprice;
            return this;
        }

        public Builder depositpaid(boolean depositpaid){
            this.depositpaid = depositpaid;
            return this;
        }

        public Builder bookingdates(BookingDates bookingdates){
            this.bookingdates = bookingdates;
            return this;
        }

        public Builder additionalneeds(String additionalneeds){
            this.additionalneeds = additionalneeds;
            return this;
        }

        public CreateBookingRequest build(){
            return new CreateBookingRequest(this.firstname,this.lastname,this.totalprice,this.depositpaid,this.bookingdates,this.additionalneeds);
        }
    }
}
