package bvcn;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class Sumvalidation {
	
	@Test
	public void sumofcourses()

	{
		int price1=0;
		JsonPath jp=new JsonPath(dummypayload.dmp());
		int Count=jp.getInt("courses.size()");
		int amount=jp.getInt("dashboard.purchaseAmount");
		
		for(int i=0;i<Count;i++) 
		{
			
			int price=jp.get("courses["+i+"].price");
			int copies=jp.get("courses["+i+"].copies");
			int tp=price * copies;
			 price1=tp+price1;
		
			}
		Assert.assertEquals(price1,amount);
	}
	
	

}
