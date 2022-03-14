package bvcn;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


import java.io.File;

import org.testng.Assert;


public class JIRA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//login 
		
		
		RestAssured.baseURI="http://localhost:8080";
		
		SessionFilter session=new SessionFilter();	
		

		
	     String	resp=given().log().all().header("Content-Type","application/json").body("{\r\n"
				+ "\"username\":\"vinuthnakesani\",\r\n"
				+ "\"password\":\"Vinu12345\"\r\n"
				+ "}\r\n"
				+ "").filter(session)
		.when().post("/rest/auth/1/session")
		.then().log().all().extract().response().toString();
	
	//add comment
	     
	     String expcom="Comment added auto";
	     
	   String cresp=  given().pathParam("id","10001").header("Content-Type","application/json").body("{\r\n"
	     		+ "    \"body\":\""+expcom+"\",\r\n"
	     		+ "    \"visibility\":{\r\n"
	     		+ "        \"type\":\"role\",\r\n"
	     		+ "        \"value\":\"Administrators\"\r\n"
	     		+ "    }\r\n"
	     		+ "}").filter(session)
	     .when().post("/rest/api/2/issue/{id}/comment")
	     .then().log().all().assertThat().statusCode(201).extract().response().asString();
	   
	   JsonPath js=new JsonPath(cresp);
	 String id=js.get("id");
	     
	     //add attachment
	     
	     given().pathParam("id","10001").header("X-Atlassian-Token","no-check").filter(session)
	     .header("Content-Type","multipart/form-data").multiPart("file",new File("abc.txt"))
	     .when().post("/rest/api/2/issue/{id}/attachments")
	     .then().statusCode(200);
	     
	     //get comment,attachment
	     
	 String resp1= given().log().all().pathParam("id","10001").filter(session).queryParam("fields", "comment")
	     .when().get("/rest/api/2/issue/{id}")
	     .then().log().all().assertThat().statusCode(200).extract().response().asString();
	 
	  JsonPath js1=new JsonPath(resp1);
		 int idcount=js1.getInt("fields.comment.comments.size()");
		 
		 for (int i=0;i<idcount;i++) {
			 
			String com=js1.get("fields.comment.comments["+i+"].id").toString(); 
			
			if(com.equalsIgnoreCase(id))
			{
				String msg=js1.get("fields.comment.comments["+i+"].body").toString(); 
				
				Assert.assertEquals(msg,expcom);
				
					System.out.println("comment added succssfully");
				
			
			}
		 }
		 }

	}


