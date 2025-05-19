package RestfulBookerTest;

import io.restassured.response.Response;
import service.HealthCheckService;

public class HealthCheckTest {

    public static boolean healthCheck(){
        HealthCheckService healthCheckService = new HealthCheckService();
        Response healthCheckResponse = healthCheckService.healthCheck();
        return healthCheckResponse.getStatusCode() == 201;
    }
}
