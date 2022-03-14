package bvcn;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;


public class Extractcomplexjson {
	
	public static void main(String args[]) {
	
	JsonPath jp=new JsonPath(dummypayload.dmp());
	
	//n.o of courses
	int Count=jp.getInt("courses.size()");
	//puchase amount
	int amount=jp.getInt("dashboard.purchaseAmount");
	System.out.println(amount);
	//title of first course
	String course1=jp.getString("courses[0].title");
	System.out.println(course1);
	//print all coursetitle-price
	for(int i=0;i<Count;i++) 
	{
		String coursetitle=jp.get("courses["+i+"].title");
		String courseprice=jp.get("courses["+i+"].price").toString();
		System.out.println(coursetitle);
		System.out.println(courseprice);
	}
	//n.o of copies sold by RPA
	for(int i=0;i<Count;i++) 
	{
		String coursetitle=jp.get("courses["+i+"].title");
		int copies=jp.get("courses["+i+"].copies");
		
		if(coursetitle.equals("RPA"))
		{
			System.out.println(copies);
			break;
		
		}
			
		
	}
	int price1=0;
	//sum of allprice=purchase amount
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
