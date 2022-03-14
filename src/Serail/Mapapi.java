package Serail;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import bvcn.postpayload;
import io.restassured.RestAssured;
import java.util.ArrayList;
import java.util.List;

public class Mapapi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
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
		
		
		
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(m)
				.when().post("maps/api/place/add/json")
				.then().assertThat().statusCode(200).extract().response().asString();
	
		
	}
	
	

}

