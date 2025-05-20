package filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class LoggingFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(LoggingFilter.class);

    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx){
        logRequest(requestSpec);
        Response response = ctx.next(requestSpec,responseSpec);
        logResponse(response);
        return response;
    }

    public void logRequest(FilterableRequestSpecification requestSpec){
        logger.info("Base URI: {}", requestSpec.getBaseUri());
        logger.info("Endpoint: {}", requestSpec.getURI().split("com")[1]);
        logger.info("Request Header: {}", requestSpec.getHeaders());
        logger.info("Path Params: {}", requestSpec.getPathParams());
        logger.info("Request Payload: {}", Optional.ofNullable(requestSpec.getBody()));
    }

    public void logResponse(Response response){
        logger.info("STATUS CODE: {}", response.getStatusCode());
        logger.info("Response Header: {}", response.getHeaders());
        logger.info("Response Body: {}", response.getBody().asPrettyString());
    }
}
