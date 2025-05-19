package service;

import endpoints.AuthEndpoint;
import io.restassured.response.Response;
import models.request.AuthTokenRequest;

public class AuthTokenService extends BaseService {

    public Response generateToken(AuthTokenRequest payload){
        addJsonContentType();
        return post(payload, AuthEndpoint.CREATE_TOKEN.getEndpoint());
    }
}
