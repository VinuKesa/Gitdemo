package bvcn;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;


public class Test1 {

	public static void main(String[] args) {
		
	RestAssured.baseURI="https://rahulshettyacademy.com";
		
	String response=given().queryParam("key", "qaclick123").header("Content-Type","application/json").body(postpayload.addapi())
	.when().post("maps/api/place/add/json")
	.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
	.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
	
	//-----------------------------------------------------------------------------------
	
	//JsonPath js=new JsonPath(response);
	JsonPath js3=reusable.rawToJson(response);
	String placeId=js3.getString("place_id");
	String newAddress = "Summer Walk, Africa";
	

	
	given().queryParam("key", "qaclick123").header("Content-Type","application/json")
	.body("{\r\n" + 
			"\"place_id\":\""+placeId+"\",\r\n" + 
			"\"address\":\""+newAddress+"\",\r\n" + 
			"\"key\":\"qaclick123\"\r\n" + 
			"}").
	when().put("maps/api/place/update/json")
	.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
	
	//-------------------------------------------------------------------------------------
	
	
	String getresponse=given().queryParam("key", "qaclick123").queryParam("place_id",placeId)
	.when().get("maps/api/place/get/json")
    .then().assertThat().statusCode(200).extract().response().asString();
	
	JsonPath js2=reusable.rawToJson(getresponse);
	String actualAddress =js2.getString("address");
	Assert.assertEquals(actualAddress,newAddress);
	
	

	}

}
