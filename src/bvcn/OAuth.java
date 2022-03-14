package bvcn;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import POJO.getcourses;
import POJO.webAutomation;

public class OAuth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String[] coursetit= {"Selenium Webdriver Java","Cypress","Protractor"};
		   
		
String url="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWhhEugNBwOwy4sNCC6RAWqjPQxu7NRThsXPXoZfTz5LqlFSQ0IZBrQ-kaDuYmf0IQ&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
String partialcode=url.split("code=")[1];

String code=partialcode.split("&scope")[0];

System.out.println(code);
//get access token
String res= given().urlEncodingEnabled(false).queryParams("code",code).
queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
.queryParams("grant_type","authorization_code")
.queryParams("scope","https://www.googleapis.com/auth/userinfo.email")
.queryParams("response_type","code")
.queryParams("authuser","1").
queryParams("prompt","consent").
headers("Content-Type","application/json")
.when().post("https://www.googleapis.com/oauth2/v4/token").asString();

JsonPath js=new JsonPath(res);
String access_token=js.get("access_token");
System.out.println(res);
//get course
getcourses gc=given().queryParams("access_token",access_token).expect().defaultParser(Parser.JSON).
when().get("https://rahulshettyacademy.com/getCourse.php").as(getcourses.class);

System.out.println(gc.getLinkedIn());
System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());

ArrayList<String> a= new ArrayList<String>();

for(int i=0;i<gc.getCourses().getApi().size();i++)
{
	if(gc.getCourses().getApi().get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
	{
		System.out.println(gc.getCourses().getApi().get(i).getPrice());
	}
}

  List<webAutomation> w=gc.getCourses().getWebAutomation();
for(int i=0;i<gc.getCourses().getWebAutomation().size();i++)
{
	a.add(w.get(i).getCourseTitle());
	}
 List<String> expcoursetit=Arrays.asList(coursetit);
 boolean condition=a.equals(expcoursetit);
Assert.assertTrue(condition);

}
}
