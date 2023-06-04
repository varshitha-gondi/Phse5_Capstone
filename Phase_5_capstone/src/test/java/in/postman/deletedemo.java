package in.postman;


import org.testng.annotations.Test;
import io.restassured.RestAssured;
public class deletedemo 
{
	@Test
	public void deleteResource() {
		RestAssured.baseURI = "https://gorest.co.in";
		   RestAssured.basePath = "/public/v2/users/1048566"; 
		   RestAssured
		   .given()
		   		.contentType("application/json")
		   		.header("Authorization", "Bearer 047ad3c2d098927779edc69c708805ea8df7e2d3f819953cecd2f41c186ee83e")
		   .when()
		   		.delete()
		    .then()
		    	.statusCode(204)
		    	.log().all();
	}
}
