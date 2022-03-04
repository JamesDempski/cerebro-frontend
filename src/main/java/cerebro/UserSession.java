package cerebro;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bouncycastle.util.encoders.Hex;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserSession {
	
	class websiteEntry{
		public String credential_id;
		private String website_id;
		public HashMap<String, String> websiteCredentials;
		public String websiteName;
		public String websiteUrl;
		public String htmlObjectNames;
		public String loginType;
		
		public websiteEntry() {
			websiteCredentials = new HashMap<String, String>();
		}
	}
	
	private String user_id;
	public HashMap<String, String> response;
	private String userPassword;
	public HashMap<String, websiteEntry> userWebsites;
	public String username;
	
	public UserSession() {
		user_id = null;
		userPassword = null;
		userWebsites = new HashMap<String, websiteEntry>();
		response = new HashMap<String, String>();
		username = null;
	}
	
	public boolean loginUser(String username, String password) {
		HashMap<String, String> params = new HashMap<String, String>();
		AppRequests request = new AppRequests();
    	
    	
    	MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			System.out.println("Unable to hash password");
			e1.printStackTrace();
		}
    	byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
    	String sha256hex = new String(Hex.encode(hash));
    	
    	params.put("username", username);
    	params.put("password_hash", sha256hex);
    	try {
			response = request.sendRequest("users/verifyUser.php", params);
		} catch (IOException e) {
			System.out.println("Error logging in user");
			e.printStackTrace();
		}
    	
    	if(response.get("code").equals("200")) {
    		user_id = response.get("user_id");
    		this.username = username;
    		userPassword = password;
    		return true;
    	}else {
    		//response.get("message") will hold the error message
    		return false;
    	}
	}
	
	public boolean registerUser(String username, String password, String security_question, String security_answer) {
		HashMap<String, String> params = new HashMap<String, String>();
		AppRequests request = new AppRequests();
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e1) {
			System.out.println("Unable to hash password");
			e1.printStackTrace();
		}
		
		byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
    	String sha256hex = new String(Hex.encode(hash));
    	
    	params.put("username", username);
    	params.put("public_key", sha256hex);
    	params.put("security_question", security_question);
    	params.put("security_answer", security_answer);
    	
    	try {
			response = request.sendRequest("users/createUser.php", params);
		} catch (IOException e) {
			System.out.println("Error logging in user");
			e.printStackTrace();
		}
    	
    	if(response.get("code").equals("200")) {
    		return true;
    	}else {
    		//response.get("message") will hold the error message
    		return false;
    	}
	}
	
	public boolean createUserWebsite(String website_id, HashMap<String, String> credentials) {
		HashMap<String, String> params = new HashMap<String, String>();
		AppRequests request = new AppRequests();
		AppAES encryptor = new AppAES(userPassword);
		
		String credentialsJson = (new JSONObject(credentials)).toString();
		
		//System.out.println(credentialsJson);
		
		String encryptedCreds = encryptor.encrypt(credentialsJson);
		
		//System.out.println(encryptedCreds);
		
		params.put("user_id", user_id);
		params.put("website_id", website_id);
		params.put("user_website_credentials", encryptedCreds);
    	
    	try {
			response = request.sendRequest("user_websites/addUserWebsite.php", params);
		} catch (IOException e) {
			System.out.println("Error getting user websites");
			e.printStackTrace();
		}
    	
    	if(response.get("code").equals("200")) {
    		return true;
    	}
		
		return false;
	}
	
	public boolean getUserWebsites() {
		HashMap<String, String> params = new HashMap<String, String>();
		AppRequests request = new AppRequests();
    	AppAES decryptor = new AppAES(userPassword);
    	List<websiteEntry> entriesList = new ArrayList<websiteEntry>();
    	
    	params.put("user_id", user_id);
    	
    	try {
			response = request.sendRequest("user_websites/getUserWebsites.php", params);
		} catch (IOException e) {
			System.out.println("Error getting user websites");
			e.printStackTrace();
		}
    	
    	if(!response.get("code").equals("200")) {
    		return false;
    	}
    	
    	ObjectMapper mapper = new ObjectMapper();
    	String[] entries = null;
    	try {
			entries = mapper.readValue(response.get("user_websites"), new TypeReference<String[]>() {});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("JSON parsing error for user websites");
			e.printStackTrace();
		}
    	
    	for(String entry:entries) {
    		HashMap<String, String> entryMap = null;
    		try {
				entryMap = mapper.readValue(entry, new TypeReference<HashMap<String, String>>() {});
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				System.out.println("JSON parsing error for user websites");
				e.printStackTrace();
				return false;
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				System.out.println("JSON mapping error for user websites");
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("JSON parsing IO exception error for user websites");
				e.printStackTrace();
				return false;
			}
    		
    		websiteEntry entryObj = new websiteEntry();
    		entryObj.credential_id = entryMap.get("credential_id");
    		entryObj.website_id = entryMap.get("website_id");
    		entryObj.websiteName = entryMap.get("website_name");
    		entryObj.websiteUrl = entryMap.get("url");
    		entryObj.htmlObjectNames = entryMap.get("html_object_names");
    		entryObj.loginType = entryMap.get("login_type");
    		String jsonCreds = decryptor.decrypt(entryMap.get("user_website_credentials"));
    		
    		try {
				entryObj.websiteCredentials = mapper.readValue(jsonCreds, new TypeReference<HashMap<String, String>>() {});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to read json creds");
				e.printStackTrace();
				return false;
			}
    		userWebsites.put(entryObj.websiteName, entryObj);
    	}
    	
		return true;
	}
	
	public boolean updateWebsiteCredentials(websiteEntry updatedEntry) {
		HashMap<String, String> params = new HashMap<String, String>();
		AppRequests request = new AppRequests();
		AppAES encryptor = new AppAES(userPassword);
		
		String newCredentialsJson = (new JSONObject(updatedEntry.websiteCredentials)).toString();
		//System.out.println(newCredentialsJson);
		String newEncryptedCreds = encryptor.encrypt(newCredentialsJson);
		//System.out.println(newEncryptedCreds);
		params.put("credential_id", updatedEntry.credential_id);
		params.put("user_website_credentials", newEncryptedCreds);
    	
    	try {
			response = request.sendRequest("user_websites/updateUserWebsite.php", params);
		} catch (IOException e) {
			System.out.println("Error updating user website");
			e.printStackTrace();
			return false;
		}
    	//System.out.println(response);
    	if(response.get("code").equals("200")) {
    		return true;
    	}
    	
		return false;
	}
	
	public boolean deleteWebsiteEntry(websiteEntry entryToDelete) {
		HashMap<String, String> params = new HashMap<String, String>();
		AppRequests request = new AppRequests();
		
		params.put("credential_id", entryToDelete.credential_id);
		
		try {
			response = request.sendRequest("user_websites/deleteUserWebsite.php", params);
		} catch (IOException e) {
			System.out.println("Error deleting user website");
			e.printStackTrace();
			return false;
		}
		
		if(response.get("code").equals("200")) {
    		return true;
    	}
		
		return false;
	}
	
	public boolean updateSuperpassword(String newPassword) {
		HashMap<String, String> params = new HashMap<String, String>();
		AppRequests request = new AppRequests();
		AppAES newEncryptor = new AppAES(newPassword);
		
		if(!getUserWebsites()) {
			System.out.println("Unable to retreive websites");
			return false;
		}
		
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e1) {
			System.out.println("Unable to hash password");
			e1.printStackTrace();
			return false;
		}
		
		byte[] newHash = digest.digest(newPassword.getBytes(StandardCharsets.UTF_8));
    	String sha256hex = new String(Hex.encode(newHash));
    	
		params.put("user_id", user_id);
		params.put("password_hash", sha256hex);
		
		try {
			response = request.sendRequest("users/updateUserPassword.php", params);
		} catch (IOException e) {
			System.out.println("Error updating user password");
			e.printStackTrace();
			return false;
		}
		
		//System.out.println("Response: "+ response);
		
		if(response.get("code").equals("200")) {
			
			for(websiteEntry entry : userWebsites.values()) {
				params.clear();
				params.put("credential_id", entry.credential_id);
				
				String newCredentialsJson = (new JSONObject(entry.websiteCredentials)).toString();
				//System.out.println("New credentials: " + newCredentialsJson);
				String newEncryptedCreds = newEncryptor.encrypt(newCredentialsJson);
				
				params.put("user_website_credentials", newEncryptedCreds);
		    	//System.out.println(params);
		    	try {
					response = request.sendRequest("user_websites/updateUserWebsite.php", params);
				} catch (IOException e) {
					System.out.println("Error updating user website during password change");
					e.printStackTrace();
					return false;
				}
		    	//System.out.println("Response for " + entry.websiteName + " is: " + response);
			}
			userPassword = newPassword;
			return true;
		}
		
		return false;
	}
}
