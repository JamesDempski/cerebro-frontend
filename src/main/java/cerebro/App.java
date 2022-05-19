package cerebro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import cerebro.UserSession.websiteEntry;
import okhttp3.Response;
import org.openqa.selenium.WebElement;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.*;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class App extends JFrame implements ActionListener, ItemListener
{
	static JFrame loginframe;
	static JLabel loginemaillabel;
	static JTextField loginemailfield;
	static JLabel loginpwdlabel;
	static JTextField loginpwdfield;
	static JButton loginsubmitbutton;
	static JButton loginregisterbutton;
	static JButton loginpasswordbutton;
	static int loginincorrectcount = 0;
	
	
	static JFrame homeframe;
	static JButton genpwdbutton;
	static JButton addnewsitesbutton;
	static JButton logoutbutton;
	static String websiteslistarr[] = {};
	static JComboBox websiteslist;
	static JButton visitbutton;
	static JButton editbutton;
	static JButton hofstrabutton;
	static JLabel homespacinglabel;
	
	
	static JFrame genframe;
	static JLabel genlenlabel;
	static JTextField genlenfield;
	static JLabel genupperlabel;
	static JTextField genupperfield;
	static JLabel genlowerlabel;
	static JTextField genlowerfield;
	static JLabel genspeciallabel;
	static JTextField genspecialfield;
	static JButton generatebutton;
	static JTextField genpwdfield;
	
	
	static JFrame webframe;
	static JLabel websitelabel;
	static JComboBox websuppwebsiteslist;
	static String websuppwebsitesarr[] = UserSession.supportedWebsites.keySet().toArray(new String[UserSession.supportedWebsites.size()]);
	static JLabel webemaillabel;
	static JTextField webemailfield;
	static JLabel webpwdlabel;
	static JTextField webpwdfield;
	static JButton websavebutton;
	static boolean newcred = true;
	
	
	static JFrame regframe;
	static JLabel regemaillabel;
	static JTextField regemailfield;
	static JLabel regpwdlabel;
	static JTextField regpwdfield;
	static JLabel regconfirmpwdlabel;
	static JTextField regconfirmpwdfield;
	static JComboBox regquestionlist;
	static JTextField reganswerfield;
	static JButton regsubmitbutton;
	
	
	static JFrame recovframe;
	static JLabel recovquestionlabel;
	static JTextField recovquestionfield;
	static JButton recovsubmitbutton;
	static JLabel recovpasswordlabel;
	
	
	static JFrame resetframe;
	static JLabel resetpwdlabel;
	static JTextField resetpwdfield;
	static JLabel resetconfirmpwdlabel;
	static JTextField resetconfirmpwdfield;
	static JButton resetpwdbutton;
	
	
	
	static String question;
	static String homewebsite;
	static String webwebsite;
	static String recovemail;
	
	static UserSession user = new UserSession();
	
	
    public static void main( String[] args )
    {
    	
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
    	loginpasswordbutton.addActionListener(a);
    	regsubmitbutton.addActionListener(a);
    	genpwdbutton.addActionListener(a);
    	addnewsitesbutton.addActionListener(a);
    	logoutbutton.addActionListener(a);
    	visitbutton.addActionListener(a);
    	editbutton.addActionListener(a);
    	generatebutton.addActionListener(a);
    	websavebutton.addActionListener(a);
    	recovsubmitbutton.addActionListener(a);
    	resetpwdbutton.addActionListener(a);
    	
    	regquestionlist.addItemListener(a);
    	websiteslist.addItemListener(a);
    	websuppwebsiteslist.addItemListener(a);
    	
    	
    	
    	
  
    }
    
    public void showGroupLayout() {
    	loginframe = new JFrame();
    	loginframe.setSize(600,300);
    	loginframe.setLayout(new GridLayout(2,3));
    	homeframe = new JFrame();
    	homeframe.setSize(600,300);
    	homeframe.setLayout(new GridLayout(3,3));
    	regframe = new JFrame();
    	regframe.setSize(600,400);
    	regframe.setLayout(new GridLayout(2,4));
    	genframe = new JFrame();
    	genframe.setSize(550,300);
    	genframe.setLayout(new GridLayout(2,3));
    	webframe = new JFrame();
    	webframe.setSize(600,400);
    	webframe.setLayout(new GridLayout(2,3));
    	recovframe = new JFrame();
    	recovframe.setSize(550,300);
    	recovframe.setLayout(new GridLayout(2,2));
    	resetframe = new JFrame();
    	resetframe.setSize(550,300);
    	resetframe.setLayout(new GridLayout(2,2));
    	
    	
    	
    	
    	//login frame starts here
    	JPanel loginmainpanel = new JPanel();
    	loginframe.add(loginmainpanel);
    	JPanel logincontrolpanel = new JPanel();
    	GroupLayout loginlayout = new GroupLayout(logincontrolpanel);
    	loginlayout.setAutoCreateGaps(true);
    	loginlayout.setAutoCreateContainerGaps(true);
    	loginemaillabel = new JLabel("Email");
    	loginemailfield = new JTextField(20);
    	loginpwdlabel = new JLabel("Password");
    	loginpwdfield = new JPasswordField(20);
    	loginsubmitbutton = new JButton("Login");
    	loginregisterbutton = new JButton("Register");
    	loginpasswordbutton = new JButton("Forgot My Password");
 
    	loginlayout.setHorizontalGroup(loginlayout.createSequentialGroup()
    			.addGroup(loginlayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
    					.addComponent(loginemaillabel)
    					.addComponent(loginpwdlabel)
    					.addComponent(loginsubmitbutton)
    			)
   
    			.addGroup(loginlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(loginemailfield)
    					.addComponent(loginpwdfield)
    					.addComponent(loginregisterbutton)
    					.addComponent(loginpasswordbutton)
    			)
    			
    	);
    	
    	loginlayout.setVerticalGroup(loginlayout.createSequentialGroup()
    			.addGroup(loginlayout.createParallelGroup(GroupLayout.Alignment.CENTER)
    					.addComponent(loginemaillabel)
    					.addComponent(loginemailfield)
    			)
    			.addGroup(loginlayout.createParallelGroup(GroupLayout.Alignment.CENTER)
    					.addComponent(loginpwdlabel)
    					.addComponent(loginpwdfield)
    			)
    			.addGroup(loginlayout.createParallelGroup(GroupLayout.Alignment.CENTER)
    					.addComponent(loginsubmitbutton)
    					.addComponent(loginregisterbutton)
    			)
    			.addComponent(loginpasswordbutton)
    	);
    	
    	logincontrolpanel.setLayout(loginlayout);
    	loginmainpanel.add(logincontrolpanel);
    	loginframe.add(loginmainpanel);
    	loginframe.setVisible(true);
    	
    	
    	
    	
    	JPanel homemainpanel = new JPanel();
    	homeframe.add(homemainpanel);
    	JPanel homecontrolpanel = new JPanel();
    	GroupLayout homelayout = new GroupLayout(homecontrolpanel);
    	homelayout.setAutoCreateGaps(true);
    	homelayout.setAutoCreateContainerGaps(true);
    	
    	
    	genpwdbutton = new JButton("Generate Password");
    	addnewsitesbutton = new JButton("Add New Websites");
    	logoutbutton = new JButton("Logout");
    	homespacinglabel = new JLabel("");
    	websiteslist = new JComboBox(websiteslistarr);
    	visitbutton = new JButton("Visit");
    	editbutton = new JButton("Edit");
    	
    	homelayout.setHorizontalGroup(homelayout.createSequentialGroup()
    			.addGroup(homelayout.createParallelGroup(GroupLayout.Alignment.CENTER)
    					.addComponent(genpwdbutton)
    					.addComponent(homespacinglabel)
    					.addComponent(websiteslist)
    			)
   
    			.addGroup(homelayout.createParallelGroup(GroupLayout.Alignment.CENTER)
    					.addComponent(addnewsitesbutton)
    					.addComponent(homespacinglabel)
    					.addComponent(visitbutton)
    			)
    			
    			.addGroup(homelayout.createParallelGroup(GroupLayout.Alignment.CENTER)
    					.addComponent(logoutbutton)
    					.addComponent(homespacinglabel)
    					.addComponent(editbutton)
    			)
    			
    	);
    	
    	homelayout.setVerticalGroup(homelayout.createSequentialGroup()
    			.addGroup(homelayout.createParallelGroup(GroupLayout.Alignment.CENTER)
    					.addComponent(genpwdbutton)
    					.addComponent(addnewsitesbutton)
    					.addComponent(logoutbutton)
    			)
    			.addGroup(homelayout.createParallelGroup(GroupLayout.Alignment.CENTER)
    					.addComponent(homespacinglabel)
    					.addComponent(homespacinglabel)
    					.addComponent(homespacinglabel)
    			)
    			.addGroup(homelayout.createParallelGroup(GroupLayout.Alignment.CENTER)
    					.addComponent(websiteslist)
    					.addComponent(visitbutton)
    					.addComponent(editbutton)
    			)
    	);
    	
    	homecontrolpanel.setLayout(homelayout);
    	homemainpanel.add(homecontrolpanel);
    	homeframe.add(homemainpanel);
    	homeframe.setVisible(false);
    	
    	
    	
    	
    	//genframe stuff starts here
    	JPanel genmainpanel = new JPanel();
    	genframe.add(genmainpanel);
    	JPanel gencontrolpanel = new JPanel();
    	GroupLayout genlayout = new GroupLayout(gencontrolpanel);
    	genlayout.setAutoCreateGaps(true);
    	genlayout.setAutoCreateContainerGaps(true);
    	genlenlabel = new JLabel("Password Length");
    	genlenfield = new JTextField(32);
    	genspeciallabel = new JLabel("<html>List of Permitted Special Characters<br/> Ex. !@#$%^&*()<br/> Leave this field blank to generate password <br/> with default special characters</html>");
    	genspecialfield = new JTextField(32);
    	generatebutton = new JButton("Generate");
    	genpwdfield = new JTextField(32);
    	
    	
    	genlayout.setHorizontalGroup(genlayout.createSequentialGroup()
    			.addGroup(genlayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
    					.addComponent(genlenlabel)
    					.addComponent(genspeciallabel)
    					.addComponent(generatebutton)
    			)
    			.addGroup(genlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(genlenfield)
    					.addComponent(genspecialfield)
    					.addComponent(genpwdfield)
    			)
    	);
    	
    	genlayout.setVerticalGroup(genlayout.createSequentialGroup()
    			.addGroup(genlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(genlenlabel)
    					.addComponent(genlenfield)
    			)
    			.addGroup(genlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(genspeciallabel)
    					.addComponent(genspecialfield)
    			)
    			.addGroup(genlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(generatebutton)
    					.addComponent(genpwdfield)
    			)
    	);
    	gencontrolpanel.setLayout(genlayout);
    	genmainpanel.add(gencontrolpanel);
    	genframe.add(genmainpanel);
    	genframe.setVisible(false);
    	//gen frame ends here
    	
    	
    	
    	//webframe starts here
    	JPanel webmainpanel = new JPanel();
    	webframe.add(webmainpanel);
    	JPanel webcontrolpanel = new JPanel();
    	GroupLayout weblayout = new GroupLayout(webcontrolpanel);
    	weblayout.setAutoCreateGaps(true);
    	weblayout.setAutoCreateContainerGaps(true);
    	websitelabel = new JLabel("Website Name");
    	websuppwebsiteslist = new JComboBox(websuppwebsitesarr);
    	webemaillabel = new JLabel("Username/Email");
    	webemailfield = new JTextField(32);
    	webpwdlabel = new JLabel("Password");
    	webpwdfield = new JPasswordField(20);
    	websavebutton = new JButton("Save");
    	
    	weblayout.setHorizontalGroup(weblayout.createSequentialGroup()
    			.addGroup(weblayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
    					.addComponent(websitelabel)
    					.addComponent(webemaillabel)
    					.addComponent(webpwdlabel)
    			)
    			.addGroup(weblayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(websuppwebsiteslist)
    					.addComponent(webemailfield)
    					.addComponent(webpwdfield)
    					.addComponent(websavebutton)	
    			)
    	);
    	
    	weblayout.setVerticalGroup(weblayout.createSequentialGroup()
    			.addGroup(weblayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(websitelabel)
    					.addComponent(websuppwebsiteslist)
    			)
    			.addGroup(weblayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(webemaillabel)
    					.addComponent(webemailfield)
    			)
    			.addGroup(weblayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(webpwdlabel)
    					.addComponent(webpwdfield)
    			)
    			.addGroup(weblayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(websavebutton)
    			)
    	);
    	webcontrolpanel.setLayout(weblayout);
    	webmainpanel.add(webcontrolpanel);
    	webframe.add(webmainpanel);
    	webframe.setVisible(false); 
    	//webframe ends here
    	
    	
    	//recovframe starts here
    	JPanel recovmainpanel = new JPanel();
    	recovframe.add(recovmainpanel);
    	JPanel recovcontrolpanel = new JPanel();
    	GroupLayout recovlayout = new GroupLayout(recovcontrolpanel);
    	recovlayout.setAutoCreateGaps(true);
    	recovlayout.setAutoCreateContainerGaps(true);
    	
    	
    	
		recovquestionlabel = new JLabel(""); 
		recovquestionfield = new JTextField(32);;
		recovsubmitbutton = new JButton("Submit");
		//recovpasswordlabel = new JLabel("");
    	
    	recovlayout.setHorizontalGroup(recovlayout.createSequentialGroup()
    			.addGroup(recovlayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
    					.addComponent(recovquestionlabel)
    					//.addComponent(recovsubmitbutton)
    			)
    			
    			.addGroup(recovlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(recovquestionfield)
    					//.addComponent(recovpasswordlabel)	
    					.addComponent(recovsubmitbutton)
    			)
    	);
    	
    	recovlayout.setVerticalGroup(recovlayout.createSequentialGroup()
    			.addGroup(recovlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(recovquestionlabel)
    					.addComponent(recovquestionfield)
    			)
    			.addGroup(recovlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(recovsubmitbutton)
    					//.addComponent(recovpasswordlabel)
    			)
    	); 
    	recovcontrolpanel.setLayout(recovlayout);
    	recovmainpanel.add(recovcontrolpanel);
    	recovframe.add(recovmainpanel);
    	recovframe.setVisible(false);  
    	//recovframe ends here
    	
    	
    	
    	//resetframe starts here
    	JPanel resetmainpanel = new JPanel();
    	resetframe.add(resetmainpanel);
    	JPanel resetcontrolpanel = new JPanel();
    	GroupLayout resetlayout = new GroupLayout(resetcontrolpanel);
    	resetlayout.setAutoCreateGaps(true);
    	resetlayout.setAutoCreateContainerGaps(true);
    	
    	
    	
		resetpwdlabel = new JLabel("Password"); 
		resetpwdfield = new JPasswordField(20);
		resetconfirmpwdlabel = new JLabel("Confirm Password");
		resetconfirmpwdfield = new JPasswordField(20);
		resetpwdbutton = new JButton("Update Password");
		
    	
    	resetlayout.setHorizontalGroup(resetlayout.createSequentialGroup()
    			.addGroup(resetlayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
    					.addComponent(resetpwdlabel)
    					.addComponent(resetconfirmpwdlabel)
    					
    			)
    			.addGroup(resetlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(resetpwdfield)
    					.addComponent(resetconfirmpwdfield)	
    					.addComponent(resetpwdbutton)
    			)
    	);
    	
    	resetlayout.setVerticalGroup(resetlayout.createSequentialGroup()
    			.addGroup(resetlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(resetpwdlabel)
    					.addComponent(resetpwdfield)
    			)
    			.addGroup(resetlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(resetconfirmpwdlabel)
    					.addComponent(resetconfirmpwdfield)
    			)
    			.addGroup(resetlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(resetpwdbutton)
    			)
    	); 
    	resetcontrolpanel.setLayout(resetlayout);
    	resetmainpanel.add(resetcontrolpanel);
    	resetframe.add(resetmainpanel);
    	resetframe.setVisible(false);  
    	//resetframe ends here
    	
    	
    	//registerframe starts here
    	JPanel regmainpanel = new JPanel();
    	regframe.add(regmainpanel);
    	JPanel regcontrolpanel = new JPanel();
    	GroupLayout reglayout = new GroupLayout(regcontrolpanel);
    	reglayout.setAutoCreateGaps(true);
    	reglayout.setAutoCreateContainerGaps(true);
    	regemaillabel = new JLabel("Email");
    	regemailfield = new JTextField(32);
    	regpwdlabel = new JLabel("Password");
    	regpwdfield = new JPasswordField(32);
    	regconfirmpwdlabel = new JLabel("Confirm Password");
    	regconfirmpwdfield = new JPasswordField(32);
    	String questionlist[] = {"Name of your first pet?","Name of street you grew up on?"};
    	regquestionlist = new JComboBox(questionlist);
    	reganswerfield = new JTextField(32);
    	regsubmitbutton = new JButton("Sign Up");
    	
    	reglayout.setHorizontalGroup(reglayout.createSequentialGroup()
    			.addGroup(reglayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
    					.addComponent(regemaillabel)
    					.addComponent(regpwdlabel)
    					.addComponent(regconfirmpwdlabel)
    					.addComponent(regquestionlist)
    			)
    			.addGroup(reglayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(regemailfield)
    					.addComponent(regpwdfield)
    					.addComponent(regconfirmpwdfield)
    					.addComponent(reganswerfield)
    					.addComponent(regsubmitbutton)	
    			)
    	);
    	
    	reglayout.setVerticalGroup(reglayout.createSequentialGroup()
    			.addGroup(reglayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(regemaillabel)
    					.addComponent(regemailfield)
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
    					.addComponent(regquestionlist)
    					.addComponent(reganswerfield)
    			)
    			.addGroup(reglayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(regsubmitbutton)
    			)
    	);
    	regcontrolpanel.setLayout(reglayout);
    	regmainpanel.add(regcontrolpanel);
    	regframe.add(regmainpanel);
    	regframe.setVisible(false);  
    	//regframe ends here
    }
    
    public void returnHome() {
    	if(user.getUserWebsites()) {
    		websiteslist.removeAllItems();
			for(String str:user.userWebsites.keySet()) {
				websiteslist.addItem(str);
			}
			homeframe.setVisible(true);
		}
    }
    
    public void returnLogin() {
		loginframe.setVisible(true);
		loginemailfield.setText("");
		loginpwdfield.setText("");
    }


	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		
		if(s.equals("Add New Websites")) {
			newcred = true;
			if(user.getUserWebsites()) {
	    		websuppwebsiteslist.removeAllItems();
				for(String str:websuppwebsitesarr) {
					websuppwebsiteslist.addItem(str);
				}
			}
			homeframe.setVisible(true);
			webframe.setVisible(true);
		}
		
		
		if(s.equals("Edit")) {
			newcred = false;
			websuppwebsiteslist.removeAllItems();
			websuppwebsiteslist.addItem(homewebsite);
			webemailfield.setText(user.userWebsites.get(homewebsite).websiteCredentials.get("username"));
			webpwdfield.setText(user.userWebsites.get(homewebsite).websiteCredentials.get("password"));
			webframe.setVisible(true);
		}
		
		
		if(s.equals("Forgot My Password")) {
			if(!recovquestionfield.getText().equals("")) {
				recovquestionfield.setText("");
			}
			
			if(loginemailfield.getText().equals("")) {
				JOptionPane.showMessageDialog(loginframe,"Please fill out email field to reset password");
			}
			else {
				recovemail = loginemailfield.getText();
				recovquestionlabel.setText(user.getSecurityQuestion(loginemailfield.getText()));
				recovframe.setVisible(true);
			}
		}
		
		
		if(s.equals("Generate")) {
			if(!(genlenfield.getText().equals(""))) {
				String regex = "[0-9]+";
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(genlenfield.getText());
				if(m.matches()) {
					String regex2 = "[$&+,:;=\\\\\\\\?@#|/'<>.^*()%!-]+";
					Pattern p2 = Pattern.compile(regex2);
					Matcher m2 = p2.matcher(genspecialfield.getText());
					if(m2.matches() || genspecialfield.getText().equals("")) {
						int i=Integer.parseInt(genlenfield.getText());  
						if(i>=8 && i<=20) {
							genpwdfield.setText(user.generatePassword(i, genspecialfield.getText()));
							StringSelection stringSelection = new StringSelection(genpwdfield.getText());
							Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
							clipboard.setContents(stringSelection, null);
							JOptionPane.showMessageDialog(genframe,"The generated password has been copied to the keyboard");
						}
						else {
							JOptionPane.showMessageDialog(genframe,"Password Length can only contain a number between 8 and 20, inclusive");
						}
					}
					else {
						JOptionPane.showMessageDialog(genframe,"List of Special Characters can only contain special characters");
					}
				}
				else {
					JOptionPane.showMessageDialog(genframe,"Password Length field must only contain numbers"); 
				}
			}
			else {
				JOptionPane.showMessageDialog(genframe,"Password Length field must be non-empty before submitting");
			}
		}
		
		
		if(s.equals("Generate Password")) {
			returnHome();
			genframe.setVisible(true);
		}
		
		
		if(s.equals("Login")) {
			if(user.loginUser(loginemailfield.getText(),loginpwdfield.getText())) {
				loginframe.setVisible(false);
				returnHome();
			}
			else {
				if(loginincorrectcount<4) {
					JOptionPane.showMessageDialog(loginframe,"Incorrect email or password");
					loginincorrectcount++;
				}
				else {
					JOptionPane.showMessageDialog(loginframe,"5 Incorrect Login Attempts, please try again later");
					//need some way to lock someone out for 15 minutes
				}
			}
		}
		
		
		if(s.equals("Logout")) {
			user.logoutUser();
			homeframe.setVisible(false);
			returnLogin();
		}
		
		
		if(s.equals("Register")) {
			loginframe.setVisible(false);
			regframe.setVisible(true);
		}
		
		
		if(s.equals("Save")) {
			if(user.userWebsites.keySet().contains(webwebsite)) {
				newcred = false;
			}

			if(!(webemailfield.getText().equals("") || webpwdfield.getText().equals(""))) {
				String webid = UserSession.supportedWebsites.get(webwebsite);
			
				if(newcred) {
					//create website entry
					HashMap<String,String> creds = new HashMap<String,String>();
			    	creds.put("username", webemailfield.getText());
			    	creds.put("password", webpwdfield.getText());
			    	user.createUserWebsite(webid,creds);
				}
				else {
					//update website entry
					websiteEntry entry = user.userWebsites.get(webwebsite);
					HashMap<String,String> creds = new HashMap<String,String>();
			    	creds.put("username", webemailfield.getText());
			    	creds.put("password", webpwdfield.getText());
					entry.websiteCredentials = creds;
					user.updateWebsiteCredentials(entry);
				}
				webframe.setVisible(false);
				returnHome();
				webemailfield.setText("");
				webpwdfield.setText("");
			}
			else {
				JOptionPane.showMessageDialog(webframe,"All fields must be non-empty before submitting");
			}
			
		}
		
		
		if(s.equals("Sign Up")) {
			String password = regpwdfield.getText();
			String confirmpassword = regconfirmpwdfield.getText();
			if(password.equals(confirmpassword)) {
				String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[!@#$%^&+=])" + "(?=\\S+$).{8,20}$";
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(password);
			
				if(!(regemailfield.getText().equals("") || password.equals("") && reganswerfield.getText().equals("") || reganswerfield.getText().equals(""))) {
					
					if(m.matches()) {
						if(user.registerUser(regemailfield.getText(), password, (String) regquestionlist.getSelectedItem(), reganswerfield.getText())) {
							regframe.setVisible(false);
							returnLogin();
						}
						else {
							JOptionPane.showMessageDialog(regframe,"Registration Failed:" + user.response.get("message"));
						}
					}
					else {
						JOptionPane.showMessageDialog(regframe,"Please enter a password that adheres to the following requirements:\n"
							+ "-at least 8 characters and at most 20 characters\n"
							+ "-at least one number\n"
							+ "-at least one upper case letter\n"
							+ "-at least one lower case letter\n"
							+ "-no whitespace\n"
							+ "-and at least one special character which includes !@#$%&*()-+=^ ");
					}
				}
				else {
					JOptionPane.showMessageDialog(regframe,"All fields must be non-empty before submitting");
				}
			}
			else {
				JOptionPane.showMessageDialog(regframe,"Passwords do not match");
			}
		}
		
		
		if(s.equals("Submit")) {
			
			//add some way of locking out users after certain amount of failed attempts
			if(user.verifySecurityAnswer(recovemail, recovquestionfield.getText())) {
				recovframe.setVisible(false);
				resetframe.setVisible(true);
				if(!resetpwdfield.equals("")||!resetconfirmpwdfield.equals("")) {
					resetpwdfield.setText("");
					resetconfirmpwdfield.setText("");
				}
			}
			else {
				JOptionPane.showMessageDialog(recovframe,"Incorrect Answer");
			}
		}
		
		
		if(s.equals("Update Password")) {
			String password = resetpwdfield.getText();
			String confirmpassword = resetconfirmpwdfield.getText();
			if(password.equals("") || confirmpassword.equals("")) {
				JOptionPane.showMessageDialog(resetframe,"Both fields must be filled out before password can be reset");
				
			}
			else {
				if(password.equals(confirmpassword)) {
					String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[!@#$%^&+=])" + "(?=\\S+$).{8,20}$";
					Pattern p = Pattern.compile(regex);
					Matcher m = p.matcher(password);
					
					if(m.matches()) {
						if(user.updateSuperpassword(confirmpassword)) {
							JOptionPane.showMessageDialog(resetframe,"Password has been reset successfully");
							resetframe.setVisible(false);
							returnLogin();
						}
						else {
							JOptionPane.showMessageDialog(resetframe,"Password Update Failed:" + user.response.get("message"));
						}
					}
					else {
						JOptionPane.showMessageDialog(resetframe,"Please enter a password that adheres to the following requirements:\n"
							+ "-at least 8 characters and at most 20 characters\n"
							+ "-at least one number\n"
							+ "-at least one upper case letter\n"
							+ "-at least one lower case letter\n"
							+ "-no whitespace\n"
							+ "-and at least one special character which includes !@#$%&*()-+=^ ");
					}
					
				}
				else {
					JOptionPane.showMessageDialog(resetframe,"Passwords do not match");
				}
			}
		}
		
		
		if(s.equals("Visit")) {
			WebsiteLoader web = new WebsiteLoader();
			web.loadWebsite(user.userWebsites.get(homewebsite)); 
		}	
	}

	public void itemStateChanged(ItemEvent e) {
		question = (String) regquestionlist.getSelectedItem();
		homewebsite = (String) websiteslist.getSelectedItem();
		webwebsite = (String) websuppwebsiteslist.getSelectedItem();
	}
}
