package service;

import endpoints.BookingEndpoint;
import io.restassured.response.Response;
import models.request.createBooking.CreateBookingRequest;

import java.util.HashMap;

public class BookingService extends BaseService{

    public Response createBooking(CreateBookingRequest payload){
        addJsonContentType();
        return post(payload, BookingEndpoint.BOOKING.getEndpoint());
    }

    public Response updateBooking(CreateBookingRequest payload, HashMap<String,Integer> pathParams, HashMap<String,String> cookie){
        addJsonContentType();
        addAuthHeader(cookie);
        addPathParameter(pathParams);
        return put(payload,BookingEndpoint.UPDATE_BOOKING.getEndpoint());
    }

    public Response partialUpdateBooking(CreateBookingRequest payload, HashMap<String,Integer> pathParams, HashMap<String,String> cookie){
        addJsonContentType();
        addAuthHeader(cookie);
        addPathParameter(pathParams);
        return put(payload,BookingEndpoint.UPDATE_BOOKING.getEndpoint());
    }

    public Response getBookingId(HashMap<String,String> queryParam){
        addQueryParameter(queryParam);
        return get(BookingEndpoint.BOOKING.getEndpoint());
    }
}
