package cerebro;

import okhttp3.*;
//import okhttp3.FormBody.Builder;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppRequests {
	
	private String serverUrl = "https://cerebropasswordmanager.xyz:443/";
	
	private final OkHttpClient httpClient = new OkHttpClient.Builder()
			  												.connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS))
			  												.build();
	
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	
	public HashMap<String, String> sendRequest(String path, HashMap<String, String> params) throws IOException{
		//Initiate rsa class
		AppRSA token_generator = new AppRSA();
		//generate authentication token
		String auth_token = token_generator.urlSignString(buildTokenKey(params));
		//add authentication token to the request params
		params.put("auth_token", auth_token);
		//Convert the params to a json string
		String json = new JSONObject(params).toString();
		//Create the request body
		RequestBody body = RequestBody.create(json, JSON);

		//Create the request object
		Request request = new Request.Builder()
                					 .url(serverUrl+path)
                					 .addHeader("User-Agent", "OkHttp Bot")
                					 .post(body)
                					 .build();
		//Attempt to read response
		try (Response response = httpClient.newCall(request).execute()) {
            // Get response body
			String jsonResponse = response.body().string();
			//System.out.println("JSON response: " + jsonResponse);
			//Read json response
			ObjectMapper mapper = new ObjectMapper();
			HashMap<String, String> responseMap = mapper.readValue(jsonResponse, new TypeReference<HashMap<String, String>>() {});
			//Read the response authentication token
			String response_tokenKey = readTokenKey(responseMap);
			//System.out.println("Java token: " + response_tokenKey);
			String receivedToken = responseMap.get("auth_token");
			if(receivedToken == null)
				throw new Exception("No authentication token passed");
			//verify the response token
			if(!token_generator.verifyUrlSignature(response_tokenKey, receivedToken))
				throw new Exception("Invalid response authentication token");
			//add the response code to the hashmap
			responseMap.put("code", Integer.toString(response.code()));
			return responseMap;
        }catch (Exception e) {
        	System.out.println("Unable to get response");
        	e.printStackTrace();
        }
		return null;
	}
	
	private String buildTokenKey(HashMap<String, String> params) {
		String tokenKey = "";
		
		// TreeMap to store values of HashMap
        TreeMap<String, String> sorted = new TreeMap<>(params);
 
        // generate token key
        for (HashMap.Entry<String, String> entry : sorted.entrySet())
            tokenKey += entry.getValue();
        return tokenKey;
	}
	
	private String readTokenKey(HashMap<String, String> response) {
		String tokenKey = "";
		TreeMap<String, String> sorted = new TreeMap<>(response);
		
		// generate token key
        for (HashMap.Entry<String, String> entry : sorted.entrySet()) {
        	if(entry.getKey() != "auth_token") {
        		//System.out.println(entry.getValue().toString());
        		tokenKey += entry.getValue().toString();
        	}
        }
        //System.out.println("Token key: "+ tokenKey);
		return tokenKey;
	}
	
}
