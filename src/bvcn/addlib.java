package bvcn;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import  static io.restassured.RestAssured.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;




public class addlib {
	
	
	@Test(dataProvider="addli")
	
	
	public void Addbook(String aisle,String isbn)
	{
	
	RestAssured.baseURI="http://216.10.245.166";
	
	String res=
    given().header("Content-Type","application/json").body(postpayload.addlibrary(aisle,isbn))
	.when().post("Library/Addbook.php")
	.then().assertThat().statusCode(200).extract().response().asString();
	
	JsonPath js1=reusable.rawToJson(res);
	String id=js1.get("ID");
	}
	
	
	@DataProvider(name="addli")
	public Object[][] addl()
	{
	return new Object[][] {{"abc","123"},{"def","456"},{"ghi","789"},{"jkl","000"}};
		
		
	}

}
