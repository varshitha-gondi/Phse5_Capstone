package in.postman;

import org.testng.annotations.Test;
import io.restassured.RestAssured;

public class getdemo 
{
	@Test
	public void getresourceinfo()
	{
		 RestAssured
 	  	.when()
 	  		.get("https://gorest.co.in/public/v2/users")
 	  	.then()	
 	  		.assertThat()
 	  		.statusCode(200)
 	  		.log().all();
}
}
