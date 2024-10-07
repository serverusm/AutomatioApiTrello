package com.trello;






import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;


public class BoardTest {
    @Test
    public void testCreateBoard(){
        String endpoint = "https://api.trello.com/1/boards/";
        String apiKey = "";
        String apiToken = "";
        String boarName = "Sergio test Api4";
//        String endpoint = baseUrl+"?name="+boarName+"&key="+apiKey+"&token="+apiToken;

//        var response = RestAssured.given().log().all().when().post(endpoint);
        var headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/son");

        var queryParams = new HashMap<String, String>();
        queryParams.put("key", apiKey);
        queryParams.put("token", apiToken);
        queryParams.put("name", boarName);

        //Act
        var response = RestAssured.given().log().all().when()
                        .headers(headers)
                        .queryParams(queryParams)
                                .post(endpoint);
        //Assert
        Assert.assertEquals(response.statusCode(),200);
    }

}
