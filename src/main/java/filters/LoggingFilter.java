package filters;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import listeners.TestListeners;
import listeners.TestsListeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Optional;

public class LoggingFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(LoggingFilter.class);

    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx){
        logRequest(requestSpec);
        logRequestInReport(requestSpec);
        Response response = ctx.next(requestSpec,responseSpec);
        logResponse(response);
        logResponseInReport(response);
        return response;
    }

    public void logRequest(FilterableRequestSpecification requestSpec){
        //logging in terminal and File
        logger.info("Base URI: {}", requestSpec.getBaseUri());
        logger.info("Endpoint: {}", requestSpec.getURI().split("com")[1]);
        logger.info("Request Header: {}", requestSpec.getHeaders());
        logger.info("Path Params: {}", requestSpec.getPathParams());
        logger.info("Request Payload: {}", Optional.ofNullable(requestSpec.getBody()));

        //logging in report
//        TestListeners.extentTest.log(Status.INFO,"Base URI: {}"+requestSpec.getBaseUri());
//        TestListeners.extentTest.log(Status.INFO,"Endpoint: {}" +requestSpec.getURI().split("com")[1]);
//        TestListeners.extentTest.log(Status.INFO,"Request Header: {}" +requestSpec.getHeaders());
//        TestListeners.extentTest.log(Status.INFO,"Path Params: {}" +requestSpec.getPathParams());
//        TestListeners.extentTest.log(Status.INFO,"Request Payload: {}" +Optional.ofNullable(requestSpec.getBody()));
    }

    public void logResponse(Response response){
        //logging in terminal and files
        logger.info("STATUS CODE: {}", response.getStatusCode());
        logger.info("Response Header: {}", response.getHeaders());
        logger.info("Response Body: {}", response.getBody().asPrettyString());

        //logging in report
//        TestListeners.extentTest.log(Status.INFO,"STATUS CODE: {}" +response.getStatusCode());
//        TestListeners.extentTest.log(Status.INFO,"Response Header: {}" +response.getHeaders());
//        TestListeners.extentTest.log(Status.INFO,"Response Body: {}" +response.getBody().asPrettyString());
    }

    public void logRequestInReport(FilterableRequestSpecification requestSpec) {
        String requestDetails = String.format(
                "Base URI: %s%nEndpoint: %s%nHeaders: %s%nPath Params: %s%nRequest Payload: %s",
                requestSpec.getBaseUri(),
                requestSpec.getURI(),
                requestSpec.getHeaders(),
                requestSpec.getPathParams(),
                Optional.ofNullable(requestSpec.getBody()).orElse("No Payload")
        );

        logger.info(requestDetails);

        ExtentTest test = TestsListeners.getCurrentTest();
        if (test != null) {
            test.log(Status.INFO, "REQUEST DETAILS:\n" + requestDetails);
        }
    }

    public void logResponseInReport(Response response) {
        String responseDetails = String.format(
                "Status Code: %d%nHeaders: %s%nResponse Body: %s",
                response.getStatusCode(),
                response.getHeaders(),
                response.getBody().asPrettyString()
        );

        logger.info(responseDetails);

        ExtentTest test = TestsListeners.getCurrentTest();
        if (test != null) {
            test.log(Status.INFO, "RESPONSE DETAILS:\n" + responseDetails);
        }
    }
}
