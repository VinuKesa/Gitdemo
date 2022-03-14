package bvcn;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class browser{

public static void main(String[] args) throws InterruptedException {


	
	System.setProperty("webdriver.chrome.driver","C:\\Users\\213254\\Downloads\\chromedriver_win32\\chromedriver.exe");

	WebDriver driver=new ChromeDriver();
	
	driver.get("https://accounts.google.com/o/oauth2/v2/auth/identifier?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&flowName=GeneralOAuthFlow");

driver.findElement(By.cssSelector("input[type='email']")).sendKeys("srinath19830");
driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
Thread.sleep(4000);
driver.findElement(By.cssSelector("input[type='password']")).sendKeys("srinath19830");
driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
Thread.sleep(4000);
String url=driver.getCurrentUrl();
String partialcode=url.split("code=")[1];
String code=partialcode.split("&scope")[0];
}}





