package in.postman;


import java.util.HashMap;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;


public class postdemo 
{
	HashMap<String, String> map = new HashMap<>();
	@BeforeTest
	public void createpayload() {
		map.put("name", "durga");
		map.put("email","durga14@gmail.com");
		map.put("gender", "female");
		map.put("status", "active");
		RestAssured.baseURI = "https://gorest.co.in/";
		RestAssured.basePath="/public/v2/users";
	}
	@Test
	public void createresource() {
		RestAssured
		    .given()
		           .contentType("application/json")
		           .body(map)
		           .header("Authorization", "Bearer 047ad3c2d098927779edc69c708805ea8df7e2d3f819953cecd2f41c186ee83e")
		     .when()
		           .post()
		     .then()
		           .statusCode(201)
		           .log().all();
	}
}
