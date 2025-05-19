package RestfulBookerTest;

import io.restassured.response.Response;
import models.request.AuthTokenRequest;
import models.response.AuthTokenResponse;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.AuthTokenService;
import utils.Helper;

public class AuthTokenTest {

    @BeforeClass
    public void healthCheck(){
        if(!HealthCheckTest.healthCheck()){
            throw new SkipException("Health check API is down!!");
        }

    }
    AuthTokenService authTokenService = new AuthTokenService();

    @Test(
            priority = 0,
            description = "Verify that on providing valid username and password user gets a token"
    )
    public void createTokenWithValidUsernameAndPassword(){
        AuthTokenRequest validCredentialsPayload = new AuthTokenRequest("admin","password123");
        Response authTokenServiceResponse = authTokenService.generateToken(validCredentialsPayload);
        Assert.assertEquals(authTokenServiceResponse.getStatusCode(),200,"Success status code should be 200");
        AuthTokenResponse authTokenResponse = authTokenServiceResponse.as(AuthTokenResponse.class);

        System.out.println(authTokenResponse.token());
    }

    @Test(
            priority=1,
            description = "Verify that on providing incorrect username and password users gets an error"
    )
    public void createTokenWithIncorrectUsernameAndPassword(){
        AuthTokenRequest incorrectCredentialsPayload = new AuthTokenRequest("admin1","password1231");
        Response incorrectCredsAuthResponse = authTokenService.generateToken(incorrectCredentialsPayload);
        Assert.assertEquals(incorrectCredsAuthResponse.getStatusCode(),200,"Status message should be 200");
        String actualReason = Helper.toJson(incorrectCredsAuthResponse).getString("reason");
        Assert.assertEquals(actualReason,"Bad credentials","Bad credentials should be displayed on providing incorrect credentials");
    }


}
