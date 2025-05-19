package models.request;

public class AuthTokenRequest {

    private final String username;
    private final String password;

    public AuthTokenRequest(String username,String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
