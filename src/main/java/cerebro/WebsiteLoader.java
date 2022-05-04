package cerebro;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

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
			case "3": loadTwitchWebsite(website.websiteCredentials.get("username"), website.websiteCredentials.get("password"));
			  		  break;
			case "4": loadInstagramWebsite(website.websiteCredentials.get("username"), website.websiteCredentials.get("password"));
	  		  		  break;
			case "5": loadFacebookWebsite(website.websiteCredentials.get("username"), website.websiteCredentials.get("password"));
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
	
	public void loadTwitchWebsite(String username, String password) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));    
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		
		driver.get("https://www.twitch.tv/");
		
		//enter username
		WebElement loginbutton=driver.findElement(By.cssSelector("button[data-a-target='login-button']"));
		loginbutton.click();
		
		WebElement passinput=driver.findElement(By.id("password-input"));
		WebElement userinput=driver.findElement(By.id("login-username"));
		
		WebElement button=driver.findElement(By.cssSelector("button[data-a-target='passport-login-button']"));
		
		userinput.sendKeys(username);
		passinput.sendKeys(password);
		button.click();
	}
	
	public void loadInstagramWebsite(String username, String password) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));    
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		driver.get("https://www.instagram.com/");
		
		
		WebElement userinput=driver.findElement(By.name("username"));
		WebElement passinput=driver.findElement(By.name("password"));
		
		WebElement button=driver.findElement(By.cssSelector("button[type='submit']"));
		
		userinput.sendKeys(username);
		passinput.sendKeys(password);
		button.click();
	}
	
	public void loadFacebookWebsite(String username, String password) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));    
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		driver.get("https://www.facebook.com");
		
		//time.sleep(200);
		
		WebElement userinput=driver.findElement(By.id("email"));
		WebElement passinput=driver.findElement(By.id("pass"));
		WebElement button=driver.findElement(By.cssSelector("button[name='login']"));
		
		
		
		userinput.sendKeys(username);
		passinput.sendKeys(password);
		button.click();
		
	}
}
