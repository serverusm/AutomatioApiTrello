package com.trello;






import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;


public class BoardTest {
    private static RequestSpecification requestSpec;
    @BeforeMethod
    public void setUp(){
        requestSpec = new RequestSpecBuilder().setBaseUri("https://api.trello.com/1").build();

    }
    @Test
    public void testCreateBoard(){
        String apiKey = "";
        String apiToken = "";
        String boarName = "Sergio test Api Resquest-1 ";

        var headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/son");

        var queryParams = new HashMap<String, String>();
        queryParams.put("key", apiKey);
        queryParams.put("token", apiToken);
        queryParams.put("name", boarName);

        //Act
        var response = RestAssured.given()
                .spec(requestSpec)
                .log().all().when()
                .headers(headers)
                .queryParams(queryParams)
                .post("/boards/");
        //Assert
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test
    public void testCreateBoardReqSpec(){
        //Arrange
        String apiKey = "";
        String apiToken = "";
        String boarName = "Sergio test Api Resquest-1-2 ";

        var headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/son");

        var queryParams = new HashMap<String, String>();
        queryParams.put("key", apiKey);
        queryParams.put("token", apiToken);
        queryParams.put("name", boarName);

        //Act
        var response = RestAssured.given()
                .spec(requestSpec)
                .log().all().when()
                .headers(headers)
                .queryParams(queryParams)
                .post("/boards/");

        System.out.println(response.getBody().asPrettyString());
        //Assert
        Assert.assertEquals(response.statusCode(),200);
    }
    
    @Test
    public void UpdateBoard(){
        //AAA
        //Arrange
        String apiKey = "";
        String apiToken = "";
        String boarName = "Sergio Update-Board IJ";

        var headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/son");

        var queryParams = new HashMap<String, String>();
        queryParams.put("key", apiKey);
        queryParams.put("token", apiToken);
        queryParams.put("name", boarName);

        //Act
        var response = RestAssured.given()
                .spec(requestSpec)
                .log().all().when()
                .headers(headers)
                .queryParams(queryParams)
                .put("/boards/{idBoard}");

        System.out.println(response.getBody().asPrettyString());
        //Assert
        Assert.assertEquals(response.statusCode(),200);
    }

}
