package com.trello.api.steps;

import com.trello.ApiRequestHandler;
import com.trello.api.Context;
import com.trello.client.RequestManager;
import com.trello.utils.JsonPath;
import com.trello.utils.PropertiesInfo;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class BoardStepdefs {
    private Map<String, String> headers;
    private Map<String, String> queryParams;
    private ApiRequestHandler request;
    private Response response;
    private String boardID;
    private Context context;
    public BoardStepdefs(Context context){
        this.context = context;
    }

    @Given("I set apiRequestHandler with proper credential") //Background
    public void iSetApiRequestHandlerWithProperCredential() {
        headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/son");

        queryParams = new HashMap<String, String>();
        queryParams.put("key", PropertiesInfo.getInstance().getApiKey());
        queryParams.put("token", PropertiesInfo.getInstance().getApiToken());
        request = new ApiRequestHandler();
        request.setHeaders(headers);
        request.setQueryParam(queryParams);
    }
    @When("I create a board with name {string}")
    public void iCreateABoardWithName(String boardName) {
        request.setQueryParam("name", boardName);
        request.setEndpoint("/boards/");
        //Act

        response = RequestManager.post(request);
    }
    @Then("I should see field {string} with value {string}")
    public void iShouldSeeFieldWithValue(String field, String value) {
        String actualResult = JsonPath.getResult(response.getBody().asPrettyString(), String.format("$.%s", field));
        System.out.println(String.format("New board name: %s", actualResult));
        Assert.assertEquals(actualResult, value,String.format("board name does not match with expected value: %s", value));
    }

    @And("I validate createBoard response schema")
    public void  iValidateCreateBoardResponseSchema() {
        InputStream createBoardJsonSchema = getClass().getClassLoader()
                .getResourceAsStream("schemas/createBoardSchema.json");
        //Arrange
        response
                .then()
                .and()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(createBoardJsonSchema))
                .extract().response();

    }

}
