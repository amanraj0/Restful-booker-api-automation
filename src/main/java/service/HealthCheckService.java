package service;

import endpoints.HealthCheckEndpoint;
import io.restassured.response.Response;

public class HealthCheckService extends BaseService{

    public Response healthCheck(){
        return get(HealthCheckEndpoint.HEALTH_CHECK_ENDPOINT.getEndpoint());
    }
}
