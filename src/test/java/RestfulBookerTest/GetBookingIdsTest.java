package RestfulBookerTest;

import io.restassured.response.Response;
import models.request.createBooking.BookingDates;
import models.request.createBooking.CreateBookingRequest;
import models.response.GetBookingIdsResponse;
import models.response.createBooking.CreateBookingResponse;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.BookingService;
import utils.Helper;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class GetBookingIdsTest {

    Helper helper;

    @BeforeClass
    public void healthCheck(){
        if(!HealthCheckTest.healthCheck()){
            throw new SkipException("Health check API is down!!");
        }
        helper = new Helper();
    }

    int bookingId;
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
        bookingId = bookedResponse.bookingid();
    }

    @Test(
            priority = 1,
            description = "Verify that user gets the booking id of the booking created"
    )
    public void getBookingId(){

        Response bookingIdsResponse = bookingService.getBookingId(null);
        Assert.assertEquals(bookingIdsResponse.getStatusCode(),200,"Status code should be 200");
        List<GetBookingIdsResponse> getBookingidsResponse = List.of(bookingIdsResponse.as(GetBookingIdsResponse[].class));

        List<GetBookingIdsResponse> matchedBookingId = getBookingidsResponse.stream()
                .filter(id -> id.bookingid()==bookingId)
                .toList();

        Assert.assertNotNull(matchedBookingId,"Booking Id not found");
    }

    @Test(
            priority = 2,
            description = "Verify that user gets the booking id by firstname"
    )
    public void getBookingIdByFirstname(){

        HashMap<String,String> queryParam = new HashMap<>();
        queryParam.put("firstname",helper.get("firstname"));

        Response bookingIdsResponse = bookingService.getBookingId(queryParam);
        Assert.assertEquals(bookingIdsResponse.getStatusCode(),200,"Status code should be 200");
        List<GetBookingIdsResponse> getBookingidsResponse = List.of(bookingIdsResponse.as(GetBookingIdsResponse[].class));

        List<GetBookingIdsResponse> matchedBookingId = getBookingidsResponse.stream()
                .filter(id -> id.bookingid()==bookingId)
                .toList();

        Assert.assertNotNull(matchedBookingId,"Booking Id not found");
        Assert.assertEquals(matchedBookingId.getFirst().bookingid(),bookingId,"Booking ID is not matching");
    }

    @Test(
            priority = 3,
            description = "Verify that user gets the booking id by lastname"
    )
    public void getBookingIdByLastname(){

        HashMap<String,String> queryParam = new HashMap<>();
        queryParam.put("lastname",helper.get("lastname"));

        Response bookingIdsResponse = bookingService.getBookingId(queryParam);
        Assert.assertEquals(bookingIdsResponse.getStatusCode(),200,"Status code should be 200");
        List<GetBookingIdsResponse> getBookingidsResponse = List.of(bookingIdsResponse.as(GetBookingIdsResponse[].class));

        List<GetBookingIdsResponse> matchedBookingId = getBookingidsResponse.stream()
                .filter(id -> id.bookingid()==bookingId)
                .toList();

        Assert.assertNotNull(matchedBookingId,"Booking Id not found");
        Assert.assertEquals(matchedBookingId.getFirst().bookingid(),bookingId,"Booking ID is not matching");
    }
}
