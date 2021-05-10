package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.ApiResource;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class StepDefinition extends Utils{
	RequestSpecification request;
	RequestSpecification res;
	Response response;
	
	TestDataBuild testData = new TestDataBuild();
	
	@Given("Invalid user payload with {string}")
	public void invalid_user_payload_with(String email) throws IOException {  
	   res = given().spec(requestSpecification()).body("{\r\n"
	   		+ "    \""+email+"\": \"sydney@fife\"\r\n"
	   		+ "}\r\n"
	   		+ "");       	    		    
	}
	
	@Given("Valid user payload with {string} and {string}")
	public void valid_user_payload_with_and(String name, String job) throws IOException {
		res = given().spec(requestSpecification()).body("{\r\n"
				+ "    \"name\": \""+name+"\",\r\n"
				+ "    \"job\": \""+job+"\"\r\n"
				+ "}");       	    		    
		}

	@When("User calls {string} endpoint with {string} http request")
	public void user_calls_endpoint_with_http_request(String resource, String httpMethod) {
		ApiResource apiResource = ApiResource.valueOf(resource);
		if(httpMethod.equalsIgnoreCase("post")) {
			response = res.when().post(apiResource.getResource());
		}
		else if(httpMethod.equalsIgnoreCase("get")){
			response = res.when().get(apiResource.getResource());
		}
	}

	@Then("Api call returns status code {int}")
	public void api_call_returns_status_code(Integer expectedStatus) {
		Integer actualStatus = Integer.valueOf(response.getStatusCode());	
		assertThat(actualStatus).isEqualTo(expectedStatus);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		assertThat(getJsonPath(response, key)).as(value);	    
	}
	
	@Then("verify respone contains user first_name as {string} and last_name as {string}")
	public void verify_respone_contains_user_first_name_as_and_last_name_as(String fname, String lname) {
		String resp = response.asString();
		JsonPath jsonObj = new JsonPath(resp);
		int count = jsonObj.getInt("data.size()");
		boolean check = false;
		for(int i=0; i<count;i++) {
			String first_Name = jsonObj.get("data["+i+"].first_name");
			String last_Name = jsonObj.get("data["+i+"].last_name");
			if(first_Name.equals(fname) && last_Name.equals(lname)) {
				check = true;
				break;
			}
		}
		assertThat(check).isEqualTo(true);
	}

}
