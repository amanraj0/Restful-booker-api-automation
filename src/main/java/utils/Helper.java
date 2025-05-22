package utils;

import groovyjarjarpicocli.CommandLine;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Helper {

    ExcelUtil excelUtil;

    public Helper(){
        excelUtil = new ExcelUtil();
        excelUtil.createExcel();
    }

    public static JsonPath toJson(Response response){
        return new JsonPath(response.asPrettyString());
    }

    public String get(String key){
        return excelUtil.readExcel(0,key);
    }

    public String readEnv(String key){
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        return dotenv.get(key,System.getenv(key));
    }

}
