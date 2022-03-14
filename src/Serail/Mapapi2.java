package Serail;

import static io.restassured.RestAssured.given;

//specbuilder



import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

public class Mapapi2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	RequestSpecification reqspec=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).addQueryParam("key", "qaclick123").build();
		
	ResponseSpecification resspec=new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
		
		List<String> mylist=new ArrayList<String>();
		mylist.add("abc");
		mylist.add("def");
		mylist.add("123");
		Request m=new Request();
		location l=new location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		m.setAccuracy(50);
		m.setAddress("29, side layout, cohen 09");
		m.setLanguage("French");
		m.setLocation(l);
		m.setName("Frontline house");
		m.setPhone_number("(+91) 983 893 3937");
		m.setTypes(mylist);
		m.setWebsite("http://google.com");
		
		
		
		RequestSpecification request=given().spec(reqspec).body(m);
				
		String response= request.when().post("maps/api/place/add/json")
				.then().assertThat().spec(resspec).extract().response().asString();
		
		
		System.out.println(response);
	
		
	}
	
	

}

