package cerebro;

import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import cerebro.UserSession.websiteEntry;

public class WebsiteLoader {
	public void loadWebsite(websiteEntry website) {
		switch(website.website_id) {
			case "1": loadHofstraWebsite(website.websiteCredentials.get("username"), website.websiteCredentials.get("password"));
					  break;
			case "2": loadAmazonWebsite(website.websiteCredentials.get("username"), website.websiteCredentials.get("password"));
					  break;
				
		}
	}
	public void loadHofstraWebsite(String username, String password) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));    
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://my.hofstra.edu");
	
		WebElement webusername=driver.findElement(By.id("username"));
		WebElement webpassword=driver.findElement(By.id("password"));
		WebElement weblogin=driver.findElement(By.name("submit"));
	
		webusername.sendKeys(username);
		webpassword.sendKeys(password);
		weblogin.click();
	}
	
	public void loadAmazonWebsite(String username, String password) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));    
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.amazon.com/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2F%3Fref_%3Dnav_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=usflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&");
		
		//enter username
		WebElement webinputs=driver.findElement(By.id("ap_email"));
		WebElement webbuttons=driver.findElement(By.id("continue"));
	
		webinputs.sendKeys(username);
		webbuttons.click();
		
		webinputs=driver.findElement(By.id("ap_password"));
		webbuttons=driver.findElement(By.id("signInSubmit"));
		
		webinputs.sendKeys(password);
		webbuttons.click();
	}
}
