package cerebro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import javax.swing.*;
import java.awt.event.*;
import java.util.Collections;

public class App 
{
    public static void main( String[] args )
    {
    	JFrame frame = new JFrame();
    	JButton button = new JButton("login to my.hofstra.edu");
    	button.setBounds(300,150,200,40); //x,y,width,height
    	button.addActionListener(new ActionListener() {
    		
			public void actionPerformed(ActionEvent e) {	
    			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Jam\\eclipse\\chromedriver.exe");
    			ChromeOptions options = new ChromeOptions();
    			options.setExperimentalOption("useAutomationExtension", false);
    			options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));    
    			WebDriver driver = new ChromeDriver(options);
    	    	//WebDriver driver=new ChromeDriver();
    	    	driver.manage().window().maximize();
    	    	driver.get("https://my.hofstra.edu");
    	    	
    	    	WebElement username=driver.findElement(By.id("username"));
    	    	WebElement password=driver.findElement(By.id("password"));
    	    	WebElement login=driver.findElement(By.name("submit"));
    	    	
    	    	username.sendKeys("");
    	    	password.sendKeys(""); //real info here
    	    	login.click();
			}
    	});
    	frame.add(button);
    	frame.setSize(800,400);
    	frame.setLayout(null);
    	frame.setVisible(true);
    }
}
