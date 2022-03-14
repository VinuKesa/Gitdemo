package bvcn;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import  static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class addlib3 {
	
@Test(dataProvider="addli")
	
	public void Addbook(String aisle,String isbn)
	{
	
	RestAssured.baseURI="http://216.10.245.166";
	
	String res=
    given().header("Content-Type","application/json").body(postpayload.addlibrary(aisle,isbn))
	.when().post("Library/Addbook.php")
	.then().log().all().statusCode(200).extract().response().asString();
	
	JsonPath js1=reusable.rawToJson(res);
	String id=js1.get("ID");
	
	given().queryParam("ID", id)
	.when().get("Library/GetBook.php")
	.then().log().all().statusCode(200);
	
//	given().header("Content-Type","application/json").body(postpayload.delbook(id))
//	.when().post("Library/DeleteBook.php")
//	.then().assertThat().log().all().statusCode(200).body("msg", equalTo("book is successfully deleted"));
	
//	given().queryParam("ID", id)
//	.when().get("Library/GetBook.php")
//	.then().log().all().statusCode(404);
	}
	
	
	@DataProvider(name="addli")
	public Object[][] addl()
	{
	return new Object[][] {{"abc0","0000"},{"def1","4561"},{"ghi1","7891"},{"jkl1","0001"}};
		
		
	}

}
