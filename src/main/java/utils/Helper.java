package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Helper {

    public static JsonPath toJson(Response response){
        return new JsonPath(response.asPrettyString());
    }

}
