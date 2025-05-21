package utils;

import groovyjarjarpicocli.CommandLine;
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

}
