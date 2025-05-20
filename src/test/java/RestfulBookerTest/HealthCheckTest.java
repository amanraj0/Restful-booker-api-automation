package RestfulBookerTest;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import service.HealthCheckService;

public class HealthCheckTest {

    @Test
    public static boolean healthCheck(){
        HealthCheckService healthCheckService = new HealthCheckService();
        Response healthCheckResponse = healthCheckService.healthCheck();
        return healthCheckResponse.getStatusCode() == 201;
    }
}
