package cerebro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Collections;
import java.util.regex.*;
import cerebro.UserSession.websiteEntry;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.HashMap;
import java.io.IOException;
import java.util.Arrays;


public class App extends JFrame implements ActionListener
{
	/*
	static JFrame loginframe;
	static JLabel loginunamelabel;
	static JTextField loginunamefield;
	static JLabel loginpwdlabel;
	static JTextField loginpwdfield;
	static JButton loginsubmitbutton;
	static JButton loginregisterbutton;
	//static JLabel loginincorrectlabel;
	static int loginincorrectcount = 0;
	
	static JFrame homeframe;
	static JButton hofstrabutton;
	
	static JFrame regframe;
	static JLabel regunamelabel;
	static JTextField regunamefield;
	static JLabel regpwdlabel;
	static JTextField regpwdfield;
	static JLabel regconfirmpwdlabel;
	static JTextField regconfirmpwdfield;
	static JLabel regemaillabel;
	static JTextField regemailfield;
	static JButton regsubmitbutton;
	//static JLabel regerrorlabel;
	
	static String username;
	static String password;
	static String confirmpassword;
	static String email;
	static UserSession user = new UserSession();
	
	//static String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
	
    public static void main( String[] args )
    {
    	/*
    	try {
    		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
    	
    	App a = new App();
    	a.showGroupLayout();
    	loginsubmitbutton.addActionListener(a);
    	loginregisterbutton.addActionListener(a);
    	hofstrabutton.addActionListener(a);
    	regsubmitbutton.addActionListener(a);
    	*/
    	/*
    	user.registerUser("badstudent1234@gmail.com", "abc123", "Childhood pet?", "Cat");
    	System.out.println(user.response.get("message"));
    }
    
    public void showGroupLayout() {
    	loginframe = new JFrame();
    	loginframe.setSize(600,300);
    	loginframe.setLayout(new GridLayout(2,3));
    	homeframe = new JFrame();
    	homeframe.setSize(600,300);
    	regframe = new JFrame();
    	regframe.setSize(600,400);
    	regframe.setLayout(new GridLayout(2,4));
    	
    	
    	JPanel loginmainpanel = new JPanel();
    	loginframe.add(loginmainpanel);
    	loginframe.setVisible(true);
    	
    	JPanel logincontrolpanel = new JPanel();
    	GroupLayout loginlayout = new GroupLayout(logincontrolpanel);
    	loginlayout.setAutoCreateGaps(true);
    	loginlayout.setAutoCreateContainerGaps(true);
    	loginunamelabel = new JLabel("username");
    	loginunamefield = new JTextField(20);
    	loginpwdlabel = new JLabel("password");
    	loginpwdfield = new JPasswordField(20);
    	loginsubmitbutton = new JButton("login");
    	loginregisterbutton = new JButton("register");
    	loginsubmitbutton.addActionListener(this);
    	loginregisterbutton.addActionListener(this);
    	
 
    	loginlayout.setHorizontalGroup(loginlayout.createSequentialGroup()
    			.addGroup(loginlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(loginunamelabel)
    					.addComponent(loginpwdlabel)
    					.addComponent(loginsubmitbutton)
    			)
    			.addGroup(loginlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(loginunamefield)
    					.addComponent(loginpwdfield)
    					.addComponent(loginregisterbutton)
    			)
    	);
    	
    	//loginlayout.linkSize(SwingConstants.HORIZONTAL,loginsubmitbutton,loginregisterbutton);
    	
    	loginlayout.setVerticalGroup(loginlayout.createSequentialGroup()
    			.addGroup(loginlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(loginunamelabel)
    					.addComponent(loginunamefield)
    			)
    			.addGroup(loginlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(loginpwdlabel)
    					.addComponent(loginpwdfield)
    			)
    			.addGroup(loginlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(loginsubmitbutton)
    					.addComponent(loginregisterbutton)
    			)
    	);
    	
    	logincontrolpanel.setLayout(loginlayout);
    	loginmainpanel.add(logincontrolpanel);
    	loginframe.add(loginmainpanel);
    	loginframe.setVisible(true);
    	
    	
    	//FIX THIS WHOLE SECTION WITH GROUPLAYOUT
    	JPanel homemainpanel = new JPanel();
    	hofstrabutton = new JButton("open my.hofstra.edu");
    	homemainpanel.add(hofstrabutton);
    	homeframe.add(homemainpanel);
    	homeframe.setSize(800,400);
    	homeframe.setVisible(false);
    	
    	
    	JPanel regmainpanel = new JPanel();
    	regframe.add(regmainpanel);
    	regframe.setVisible(true);
    	
    	JPanel regcontrolpanel = new JPanel();
    	GroupLayout reglayout = new GroupLayout(regcontrolpanel);
    	reglayout.setAutoCreateGaps(true);
    	reglayout.setAutoCreateContainerGaps(true);
    	regunamelabel = new JLabel("username");
    	regunamefield = new JTextField(32);
    	regpwdlabel = new JLabel("password");
    	regpwdfield = new JPasswordField(32);
    	regconfirmpwdlabel = new JLabel("confirm password");
    	regconfirmpwdfield = new JPasswordField(32);
    	regemaillabel = new JLabel("email");
    	regemailfield = new JTextField(32);
    	regsubmitbutton = new JButton("submit");
    	regsubmitbutton.addActionListener(this);
    	reglayout.setHorizontalGroup(reglayout.createSequentialGroup()
    			.addGroup(reglayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(regunamelabel)
    					.addComponent(regpwdlabel)
    					.addComponent(regconfirmpwdlabel)
    					.addComponent(regemaillabel)
    			)
    			.addGroup(reglayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(regunamefield)
    					.addComponent(regpwdfield)
    					.addComponent(regconfirmpwdfield)
    					.addComponent(regemailfield)
    					.addComponent(regsubmitbutton)
    					
    			)
    	);
    	
    	reglayout.setVerticalGroup(reglayout.createSequentialGroup()
    			.addGroup(reglayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(regunamelabel)
    					.addComponent(regunamefield)
    			)
    			.addGroup(reglayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(regpwdlabel)
    					.addComponent(regpwdfield)
    			)
    			.addGroup(reglayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(regconfirmpwdlabel)
    					.addComponent(regconfirmpwdfield)
    			)
    			.addGroup(reglayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(regemaillabel)
    					.addComponent(regemailfield)
    			)
    			.addGroup(reglayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(regsubmitbutton)
    			)
    	);
    	regcontrolpanel.setLayout(reglayout);
    	regmainpanel.add(regcontrolpanel);
    	regframe.add(regmainpanel);
    	regframe.setVisible(false);
    	
    }
	*/
	public void actionPerformed(ActionEvent e) {
		/*
		String s = e.getActionCommand();
		if(s.equals("login")) {
			if(loginunamefield.getText().equals(username)&&loginpwdfield.getText().equals(password)) {
				loginframe.setVisible(false);
				homeframe.setVisible(true);
				regframe.setVisible(false);
			}
			else {
				if(loginincorrectcount<8) {
					//loginincorrectlabel.setText("Incorrect username or password");
					loginincorrectcount++;
				}
				else {
					//loginincorrectlabel.setText("5 Incorrect Login Attempts, please try again later");
					//need some way to lock someone out for 15 minutes
				}
			}
		}
		
		
		if(s.equals("register")) {
			loginframe.setVisible(false);
			homeframe.setVisible(false);
			regframe.setVisible(true);
		}
		
		if(s.equals("submit")) {
			username = regunamefield.getText();
			password = regpwdfield.getText();
			confirmpassword = regconfirmpwdfield.getText();
			email = regemailfield.getText();
			if(password.equals(confirmpassword)) {
				String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(password);
			
				if(password != null && m.matches()) {
					loginframe.setVisible(true);
					homeframe.setVisible(false);
					regframe.setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(regframe,"Please enter a password that contains at least 8 characters,\n"
							+ "at most 20 characters,\n"
							+ "at least one number,\n"
							+ "at least one upper case letter,\n"
							+ "at least one lower case letter,\n"
							+ "no whitespace,\n"
							+ "and at least one special character which includes !@#$%&*()-+=^ ");
				}
			}
			else {
				//regerrorlabel.setText("passwords do not match");
			}
		}
		
		
		if(s.equals("open my.hofstra.edu")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));    
			WebDriver driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.get("https://my.hofstra.edu");
    	
			WebElement webusername=driver.findElement(By.id("username"));
			WebElement webpassword=driver.findElement(By.id("password"));
			WebElement weblogin=driver.findElement(By.name("submit"));
    	
			//username.sendKeys(loginunamefield.getText());
			//password.sendKeys(loginpwdfield.getText()); 
			webusername.sendKeys("h702763848");
			webpassword.sendKeys("Ardwick1738!");
			weblogin.click();
		}
		*/
	}
	public static void main( String[] args )
    {
		//----------------------Demo--------------------------------------------
		UserSession session = new UserSession();
		
		//Register
		/*
		if(session.registerUser("badstudent23@gmail.com", "password", "Have you tested this?", "Obviously not!")) {
			System.out.println("Successful registration");
		}else {
			System.out.println("Registration failed");
			System.out.println(session.response.get("message"));
		}
		*/
	
		//Login
		/*
		if(session.loginUser("badstudent23@gmail.com", "password!")) {
			System.out.println("Login successful");
		}else {
			System.out.println("Login failed");
			System.out.println(session.response.get("message"));
		}
		*/
		
		//Adding website
		/*
		HashMap<String, String> creds = new HashMap<String, String>();
		creds.put("username", "badstudent");
		creds.put("password", "password1");
		
		if(session.createUserWebsite("1", creds)) {
			System.out.println("Success");
		}else {
			System.out.println("Failure");
		}
		*/
		
		//Retreiving Websites
		/*
		if(session.getUserWebsites()) {
			System.out.println("Sites retreived");
		}else {
			System.out.println("Failed miserably");
		}
		websiteEntry entry = session.userWebsites.get("Hofstra Portal");
		System.out.println(entry.websiteCredentials);
		*/
		
		/*
		//Updating Website
		entry.websiteCredentials.put("password", "password2");
		session.updateWebsiteCredentials(entry);
		
		if(session.getUserWebsites()) {
			System.out.println("Updated sites retreived");
		}else {
			System.out.println("Failed miserably 2");
		}
		
		entry = session.userWebsites.get("Hofstra Portal");
		System.out.println(entry.websiteCredentials);
		*/
		
		//Update Superpassword
		
		//session.updateSuperpassword("password!");
		
		
		//Delete website entry
		/*
		if(session.deleteWebsiteEntry(entry)) {
			System.out.println("Successfully deleted website");
		}else {
			System.out.println("Falied at the last function");
		}
		*/
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
