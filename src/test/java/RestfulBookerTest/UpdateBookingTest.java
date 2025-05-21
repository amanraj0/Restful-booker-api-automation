package RestfulBookerTest;

import io.restassured.response.Response;
import models.request.AuthTokenRequest;
import models.request.createBooking.BookingDates;
import models.request.createBooking.CreateBookingRequest;
import models.response.AuthTokenResponse;
import models.response.createBooking.CreateBookingResponse;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.AuthTokenService;
import service.BookingService;
import utils.Helper;

import java.util.HashMap;


public class UpdateBookingTest {

    Helper helper;

    @BeforeClass
    public void healthCheck(){
        if(!HealthCheckTest.healthCheck()){
            throw new SkipException("Health check API is down!!");
        }
        helper = new Helper();
    }
    BookingService bookingService = new BookingService();
    int bookingId;
    String token;

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
        bookingId = bookedResponse.bookingid();
    }
    @Test(
            priority = 1,
            description = "Verify that user gets forbidden error if no token is provided while updating the booking"
    )
    public void updateBookingWithoutCookie(){
        HashMap<String,Integer> pathParams = new HashMap<>();
        pathParams.put("bookingId",bookingId);

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

        Response updatedBookingResponse = bookingService.updateBooking(validBookingDetails,pathParams,null);
        Assert.assertEquals(updatedBookingResponse.getStatusCode(),403,"Status code should be 403 if not auth is provided");
    }


    @Test(
            priority = 2,
            description = "Verify that on providing valid username and password user gets a token"
    )
    public void createTokenWithValidUsernameAndPassword(){
        AuthTokenService authTokenService = new AuthTokenService();
        AuthTokenRequest validCredentialsPayload = new AuthTokenRequest("admin","password123");
        Response authTokenServiceResponse = authTokenService.generateToken(validCredentialsPayload);
        Assert.assertEquals(authTokenServiceResponse.getStatusCode(),200,"Success status code should be 200");
        AuthTokenResponse authTokenResponse = authTokenServiceResponse.as(AuthTokenResponse.class);

        token = authTokenResponse.token();
    }
    @Test(
            priority = 3,
            description = "Verify that user is able to update all booking details"
    )
    public void updateBookingDetails(){

        HashMap<String,String> cookie = new HashMap<>();
        String authToken = "token=" + token;
        cookie.put("Cookie",authToken);

        HashMap<String,Integer> pathParams = new HashMap<>();
        pathParams.put("bookingId",bookingId);

        BookingDates bookingDate = new BookingDates.Builder()
                .checkin(helper.get("updatedCheckin"))
                .checkout(helper.get("updatedCheckout"))
                .build();

        CreateBookingRequest validBookingDetails = new CreateBookingRequest.Builder()
                .firstname(helper.get("updatedFirstname"))
                .lastname(helper.get("updatedLastname"))
                .totalprice(Double.parseDouble(helper.get("updatedTotalprice")))
                .depositpaid(Boolean.parseBoolean(helper.get("updatedDepositpaid").toLowerCase()))
                .additionalneeds(helper.get("updatedAdditionalneeds"))
                .bookingdates(bookingDate)
                .build();

        Response updatedBookingResponse = bookingService.updateBooking(validBookingDetails,pathParams,cookie);
        Assert.assertEquals(updatedBookingResponse.getStatusCode(),200,"Status code on successful Update should be 200");

    }


}
