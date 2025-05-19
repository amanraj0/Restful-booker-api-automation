package endpoints;

public enum AuthEndpoint {

    CREATE_TOKEN("/auth");

    private final String endpoint;

    AuthEndpoint(String endpoint){
        this.endpoint = endpoint;
    }

    public String getEndpoint(){
        return endpoint;
    }

}
