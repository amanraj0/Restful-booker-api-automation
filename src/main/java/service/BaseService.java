package service;

import filters.LoggingFilter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class BaseService {

    private final RequestSpecification requestSpecification;

    static {
        RestAssured.filters(new LoggingFilter());
    }

    public BaseService(){
        String baseUrl = "https://restful-booker.herokuapp.com";
        requestSpecification =  given().baseUri(baseUrl);
    }

    protected void addJsonContentType(){
        requestSpecification.contentType(ContentType.JSON);
    }

    protected <T> void addPathParameter(HashMap<String,T> pathParams){
        requestSpecification.pathParams(pathParams);
    }

    protected <T> void addQueryParameter(HashMap<String,T> queryParams){
        if(queryParams!=null){
            requestSpecification.queryParams(queryParams);
        }

    }

    protected void addAuthHeader(HashMap<String, String> cookie){
        if(cookie!=null){
            requestSpecification.headers(cookie);
        }
    }

    protected <T> Response get(String endpoint){
        return requestSpecification
                .get(endpoint);
    }
    protected <T> Response post(T payload, String endpoint){
        return requestSpecification
                .body(payload)
                .post(endpoint);
    }

    protected <T> Response put(T payload, String endpoint){
        return requestSpecification
                .body(payload)
                .put(endpoint);
    }

    protected <T> Response patch(T payload, String endpoint){
        return requestSpecification
                .body(payload)
                .put(endpoint);
    }
}
