package stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddOrderResponse;
import pojo.AddProductResponse;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.ViewOrdersResponse;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utility;

public class ecomstep extends Utility {
	
	
	RequestSpecification loginReqWithPayload;
	LoginResponse loginResponse;
	TestDataBuild testData = new TestDataBuild();
	APIResources resource;
	AddProductResponse productRes;
	RequestSpecification productReq;
	RequestSpecification orderReq;
	RequestSpecification viewOrderReq;
	AddOrderResponse orderRes;
	ViewOrdersResponse viewOrderRes;
	TestContext testContext;

    // Constructor Injection
    public ecomstep(TestContext testContext) {
        this.testContext = testContext;
    }
	
	@Given("user has {string} payload with {string} and {string}")
	public void user_has_payload_with_and(String api, String username, String password) throws IOException {
		
		resource = APIResources.valueOf(api);
		//path = resource.getResource();
	   
		loginReqWithPayload = given().spec(getRequestSpecification()).header("Content-type","application/json")
		.body(testData.getLoginRequestPayload(username,password));
		
	}
	@When("user calls login api request with POST http method")
	public void user_calls_login_api_request_with_post_http_method() {
		
		loginResponse = loginReqWithPayload.when().post(resource.getResource())
		.then().spec(getResponseSpecification())
		.extract().response().as(LoginResponse.class);
		testContext.setLoginResponse(loginResponse);
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		assertEquals(loginResponse.getMessage(),value);
	    
	}
	
	@Given("user has {string} payload with {string} {string} {string} {string}  {string} {string}  {string}")
	public void user_has_payload_with(String api, String productName, String productCategory, String productSubCategory, String productPrice, String productDescription, String productFor, String productImage) throws IOException {
		File image = new File(productImage);
		
		resource = APIResources.valueOf(api);
		
		productReq = given().spec(getRequestSpecification()).header("Content-type","multipart/form-data").header("Authorization",testContext.getLoginResponse().getToken())
		.multiPart("productName", productName)
		.multiPart("productAddedBy", testContext.getLoginResponse().getUserId())
		.multiPart("productCategory", productCategory)
	    .multiPart("productSubCategory", productSubCategory)
	    .multiPart("productPrice", productPrice)
	    .multiPart("productDescription", productDescription)
	    .multiPart("productFor", productFor)
	    .multiPart("productImage", image);
		
	    
	    
	}
	@When("user calls the api request with POST http method")
	public void user_calls_the_api_request_with_post_http_method() {
		

		productRes =productReq.when().post(resource.getResource())
	    .then().assertThat().statusCode(201)
	    .body("message",equalTo("Product Added Successfully")).extract().response().as(AddProductResponse.class);
	}
	
	@When("user has add order {string} payload with {string} and {string}")
	public void user_has_add_order_payload_with_and(String api, String country, String productId) throws IOException {
	    
		resource = APIResources.valueOf(api);
		orderReq = given().spec(getRequestSpecification()).header("Content-type","application/json").header("Authorization",testContext.getLoginResponse().getToken())
		.body(testData.createorderRequestPayload(country, productId));
	}
	@When("user calls ADDORDER api request with POST http method")
	public void user_calls_addorder_api_request_with_post_http_method() {
		orderRes = orderReq.when().post(resource.getResource())
		.then().extract().response().as(AddOrderResponse.class);
	}
	
	@When("user has view order {string} payload with {string}")
	public void user_has_view_order_payload_with(String api, String orders) throws IOException {
		resource = APIResources.valueOf(api);
		viewOrderReq = given().spec(getRequestSpecification()).header("Authorization",testContext.getLoginResponse().getToken())
		.queryParam("id", orders);
	    
	}
	@When("user calls VIEWORDER api request with GET http method")
	public void user_calls_vieworder_api_request_with_get_http_method() {
		viewOrderRes = viewOrderReq.when().get(resource.getResource())
		.then().extract().response().as(ViewOrdersResponse.class);
		assertEquals(viewOrderRes.getMessage(),"Orders fetched for customer Successfully");
	}


}
