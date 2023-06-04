package in.postman;

import java.util.HashMap;
import java.util.UUID;
import static org.hamcrest.Matchers.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class verify_post_get_put_delete 
{
	HashMap<String, String> map = new HashMap<>();
	int id;
	Logger logger;
	UUID uuid = UUID.randomUUID();
	
	@BeforeTest
	public void createpayload() {
		logger = Logger.getLogger("LogDemo");
		PropertyConfigurator.configure("log4j.properties");
		map.put("name", "superman");
		map.put("email", uuid + "@gmail.com");
		map.put("gender", "female");
		map.put("status", "active");
		logger.info("payload created");
		
		RestAssured.baseURI = "https://gorest.co.in";
		RestAssured.basePath = "public/v2/users";
	}
	@Test(priority = 0)
	public void createresource() {
		//Extract the response
		Response response = RestAssured
	    		   .given()
				   	   .contentType("application/json")
				   	   .body(map)
				   	   .header("Authorization", "Bearer 047ad3c2d098927779edc69c708805ea8df7e2d3f819953cecd2f41c186ee83e")
			       .when()
			   	   		.post()
			       .then()
				    	.log().all()
				   	    .contentType(ContentType.JSON).extract().response(); 
				  
			   	    
			   	JsonPath jsonpath = response.jsonPath();
			   	id = jsonpath.getInt("id");
			   	logger.info("Resource created");
			   	//System.out.println("Id of the resource = "+id);
		   }
	   
	   //Verify resource has been created
	   @Test(priority =1)
	   public void getResourceInfo() {
		   RestAssured.baseURI = "https://gorest.co.in";
		   RestAssured.basePath = "/public/v2/users/" + id; 
		   
		   RestAssured
				   .given()
				   		.contentType("application/json")
				   		.header("Authorization", "Bearer 047ad3c2d098927779edc69c708805ea8df7e2d3f819953cecd2f41c186ee83e")
				   .when()
				   		.get()
				    .then()
				    	.statusCode(200)
				    	.body("name", is("superman"));
		                logger.info("Resource created verified");
				   	}
	   
	   @Test(priority = 2)
	   public void updateResource() {
		   
		   map.put("name", "Superman");
		   map.put("email", uuid + "@gmail.com");
		   map.put("gender", "male");
		   map.put("status", "active");
		   RestAssured.baseURI = "https://gorest.co.in";
		   RestAssured.basePath = "/public/v2/users/" + id; 
		   
		   RestAssured
		   .given()
		   		.contentType("application/json")
		   		.body(map)
		   		.header("Authorization", "Bearer 047ad3c2d098927779edc69c708805ea8df7e2d3f819953cecd2f41c186ee83e")
		   .when()
		   		.put()
		    .then()
		    	.statusCode(200)
		    	.log().all();
		   	    logger.info("Resource Updated");
	   }
	   
	   @Test(priority = 3)
	   public void verifyResourceUpdated() {
		   RestAssured.baseURI = "https://gorest.co.in";
		   RestAssured.basePath = "/public/v2/users/" + id; 
		   
		   RestAssured
				   .given()
				   		.contentType("application/json")
				   		.header("Authorization", "Bearer 047ad3c2d098927779edc69c708805ea8df7e2d3f819953cecd2f41c186ee83e")
				   .when()
				   		.get()
				    .then()
				    	.statusCode(200)
				    	.body("name", is("Superman"));
		                logger.info("Resource updated verified");
				   	}
	    
	   @Test(priority = 4)
	   public void deleteResource() 
	   {
		   RestAssured.baseURI = "https://gorest.co.in";
		   RestAssured.basePath = "/public/v2/users/" + id; 
		   
		   RestAssured
				   .given()
				   		.contentType("application/json")
				   		.header("Authorization", "Bearer 047ad3c2d098927779edc69c708805ea8df7e2d3f819953cecd2f41c186ee83e")
				   .when()
				   		.delete()
				    .then()
				    	.statusCode(204);
		                 logger.info("Resource deleted");
	   }
	   
	   }
	   
	   
