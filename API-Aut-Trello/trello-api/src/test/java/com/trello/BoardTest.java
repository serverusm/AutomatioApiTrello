package com.trello;

import com.trello.utils.PropertiesInfo;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class BoardTest {
    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;
    private String apiKey;
    private String apiToken;
    private Map<String, String> headers;
    private Map<String, String> queryParams;
    private String boardID;
    @BeforeClass
    public void setUp() {
        apiKey = PropertiesInfo.getInstance().getApiKey();
        apiToken = PropertiesInfo.getInstance().getApiToken();
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(String.format("%s/%s", PropertiesInfo.getInstance().getBaseApi(),
                        PropertiesInfo.getInstance().getApiVersion())).build();
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/son");

        queryParams = new HashMap<String, String>();
        queryParams.put("key", apiKey);
        queryParams.put("token", apiToken);
    }

    @Test(priority = 1)
    public void testCreateBoardReqSpec() {
        //Arrange
        String boarName = "API Refacty with ID";
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
        Assert.assertEquals(response.statusCode(), 200);

        boardID = response.getBody().path("id");
        System.out.println(String.format("boardID: %s", boardID));
    }

    @Test(priority = 3)
    public void UpdateBoard() {
        //AAA
        //Arrange
        String boarName = "API refactory Update";

        queryParams.put("name", boarName);

        //Act
        var response = RestAssured.given()
                .spec(requestSpec)
                .log().all().when()
                .headers(headers)
                .queryParams(queryParams)
                .put(String.format("/boards/%s", boardID));

        System.out.println(response.getBody().asPrettyString());
        //Assert
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 2)
    public void getBoardTest() {

        var response = RestAssured.given()
                .spec(requestSpec)
                .log().all().when()
                .headers(headers)
                .queryParams(queryParams)
                .get(String.format("/boards/%s", boardID)).then()
                .spec(responseSpec).extract().response();

        String name = response.path("name");
        Assert.assertEquals(name, "API Refacty with ID");
    }

    @Test(priority = 4)
    public void deleteBoardTest() {

        var response = RestAssured.given()
                .spec(requestSpec)
                .log().all().when()
                .headers(headers)
                .queryParams(queryParams)
                .delete(String.format("/boards/%s", boardID)).then()
                .spec(responseSpec).extract().response();

        System.out.println("Board Delete");
        System.out.println(response.getBody().asPrettyString());
    }

}
