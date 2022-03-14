package bvcn;

public class postpayload {
	
	String aisle;
	String isbn;
	
	public static String addapi()
	{
		return 
			
				"{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}";
}
	public static String addlibrary(String aisle,String isbn)
	{
		
		return "{\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"author\":\"John foe\"\r\n"
				+ "}\r\n"
				+ "";
				
		
		}
			
	public static String delbook(String id)
	{
		return "{ \r\n"
				+ "\"ID\" : \""+id+"\"\r\n"
				+ " } ";
	}
	

}
