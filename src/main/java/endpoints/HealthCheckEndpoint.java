package endpoints;

public enum HealthCheckEndpoint {

    HEALTH_CHECK_ENDPOINT("/ping");

    private final String endpoint;

    HealthCheckEndpoint(String endpoint){
        this.endpoint = endpoint;
    }

    public String getEndpoint(){
        return endpoint;
    }

}
