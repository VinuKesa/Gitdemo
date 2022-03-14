package bvcn;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import  static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class addlib2 {
	@Test
	public void Addbook() throws IOException
	{
	
	RestAssured.baseURI="https://rahulshettyacademy.com";
	
	String res=
    given().header("Content-Type","application/json").body(external("D:\\abc.json"))
	.when().post("Library/Addbook.php")
	.then().log().all().statusCode(200).extract().response().asString();
	
	JsonPath js1=reusable.rawToJson(res);
	String id=js1.get("ID");
	System.out.println(id);
	
	given().queryParam("ID", id)
	.when().get("Library/GetBook.php")
	.then().log().all().statusCode(200);
	
	given().header("Content-Type","application/json").body(postpayload.delbook(id))
	.when().post("Library/DeleteBook.php")
	.then().assertThat().log().all().statusCode(200).body("msg", equalTo("book is successfully deleted"));
	
	given().queryParam("ID", id)
	.when().get("Library/GetBook.php")
	.then().log().all().statusCode(404);
	}
	
	
	
	
	public  String external(String path) throws IOException 
	{
		return new String(Files.readAllBytes(Paths.get(path)));
		
	}

	


}


	
	
	
	
	

	

	
	

	
	
	
		





