package RestfulBookerTest;

import io.restassured.response.Response;
import models.request.createBooking.BookingDates;
import models.request.createBooking.CreateBookingRequest;
import models.response.createBooking.CreateBookingResponse;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.BookingService;
import utils.Helper;

public class CreateBookingTest {

    Helper helper;
    @BeforeClass
    public void healthCheck(){
        if(!HealthCheckTest.healthCheck()){
            throw new SkipException("Health check API is down!!");
        }

        helper = new Helper();
    }

    BookingService bookingService = new BookingService();
    @Test(
            priority = 0,
            description = "Verify that user should be able to create a booking with all valid details"
    )
    public void createBookingWithValidDetails(){

        BookingDates bookingDate = new BookingDates.Builder()
                .checkin(helper.get("checkin"))
                .checkout(helper.get("checkout"))
                .build();

        CreateBookingRequest validBookingDetails = new CreateBookingRequest.Builder()
                .firstname(helper.get("firstname"))
                .lastname(helper.get("lastname"))
                .totalprice(Double.parseDouble(helper.get("totalprice")))
                .depositpaid(Boolean.parseBoolean(helper.get("depositpaid").toLowerCase()))
                .additionalneeds(helper.get("additionalneeds"))
                .bookingdates(bookingDate)
                .build();

        Response bookingResponse = bookingService.createBooking(validBookingDetails);
        Assert.assertEquals(bookingResponse.getStatusCode(),200,"Status code on successful creation should be 200");
        CreateBookingResponse bookedResponse = bookingResponse.as(CreateBookingResponse.class);
    }

    @Test(
            priority=1,
            description = "Verify that user gets an error on keeping the booking details as null"
    )
    public void createBookingWithoutBookingDates(){
        CreateBookingRequest validBookingDetails = new CreateBookingRequest.Builder()
                .firstname(helper.get("firstname"))
                .lastname(helper.get("lastname"))
                .totalprice(Double.parseDouble(helper.get("totalprice")))
                .depositpaid(Boolean.parseBoolean(helper.get("depositpaid").toLowerCase()))
                .additionalneeds(helper.get("additionalneeds"))
                .build();

        Response bookingResponse = bookingService.createBooking(validBookingDetails);
        Assert.assertEquals(bookingResponse.getStatusCode(),500,"Status code on keeping booking details as blank should be 500");

    }
}
