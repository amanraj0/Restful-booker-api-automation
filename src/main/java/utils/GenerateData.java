package utils;

import com.github.javafaker.Faker;
import org.codehaus.groovy.util.ListHashMap;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GenerateData {
    public Map<String,Object> createTestData(){
        Faker faker = new Faker();
        Random random = new Random();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Map<String, Object> testData = new ListHashMap<>();

        //Data
        testData.put("firstname", faker.name().firstName());
        testData.put("lastname",faker.name().lastName());
        testData.put("totalprice",faker.number().randomDouble(2, 50, 5000));
        testData.put("depositpaid",true);
        testData.put("additionalneeds",faker.options().option("Breakfast","Late checkout"));
        String checkin = sdf.format(faker.date().future(5, TimeUnit.DAYS));
        String checkout = sdf.format(faker.date().future(10, TimeUnit.DAYS));
        testData.put("checkin",checkin);
        testData.put("checkout",checkout);

        //Updated Data
        testData.put("updatedFirstname", faker.name().firstName());
        testData.put("updatedLastname",faker.name().lastName());
        testData.put("updatedTotalprice",faker.number().randomDouble(2, 50, 5000));
        testData.put("updatedDepositpaid",false);
        testData.put("updatedAdditionalneeds",faker.options().option("Early Checkin"));
        String updatedCheckin = sdf.format(faker.date().future(2, TimeUnit.DAYS));
        String updatedCheckout = sdf.format(faker.date().future(7, TimeUnit.DAYS));
        testData.put("updatedCheckin",updatedCheckin);
        testData.put("updatedCheckout",updatedCheckout);

        return testData;

    }
}
