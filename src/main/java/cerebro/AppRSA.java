package cerebro;



import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;

import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


public class AppRSA {
	private PrivateKey privateKey;
    private PublicKey publicKey;
    private PublicKey serverPublicKey;
    
    public AppRSA() {
    	try {
			privateKey = getPrivateKey();
		} catch (Exception e) {
			System.out.println("Unable to get private key");
			e.printStackTrace();
		}
    	
    	try {
			publicKey = getPublicKey();
		} catch (Exception e) {
			System.out.println("Unable to get public key");
			e.printStackTrace();
		}
    	
    }
    
    public String urlSignString(String str) {
    	try {
    		//initiate signature object
    		Signature signer = Signature.getInstance("SHA256WithRSA");
    		//load private key
    		signer.initSign(privateKey);
    		//add message to signature
    		signer.update(str.getBytes());
    		//sign
    		byte[] signature = signer.sign();
    		//base64 encode signature for transfer
    	    String signatureString = new String(Base64.getEncoder().encode(signature));
    	    //Encode string into url format
    	    return URLEncoder.encode(signatureString, "UTF-8");
    	} catch (Exception e) {
    		System.out.println("Unable to generate signature");
    	    e.printStackTrace();
    	}
    	return null;
    }
    
    public boolean verifyUrlSignature(String message, String SignatureStr) {
    	try {
    		//Decode the url string the binary signature
    		String b64str = new String(URLDecoder.decode(SignatureStr, "UTF-8"));
        	byte[] signature = Base64.getDecoder().decode(b64str);
        	//Initiate signature object
        	Signature sig = Signature.getInstance("SHA256WithRSA");
        	//Load public key to signature object
        	sig.initVerify(publicKey);
        	//add the message bytes
            sig.update(message.getBytes());
            //return verification result
            return sig.verify(signature);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    	
    }
    //function to read public key
    private PublicKey getPublicKey() throws Exception {
    	String key = Files.readString(Paths.get("public_key.txt"));
    	//get public key base64 string
        String publicKeyPEM = key
        		.replace("-----BEGIN PUBLIC KEY-----", "")
        		.replaceAll(System.lineSeparator(), "")
        	    .replace("-----END PUBLIC KEY-----", "");
        //decode the base64 public key bytes
        byte[] keyBytes = Base64.getDecoder().decode(publicKeyPEM);
        //retrieve public key object
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);	
    }
    //function to read private key
    private PrivateKey getPrivateKey() throws Exception {
    	String key = Files.readString(Paths.get("private_key.txt"));
    	//get private key base64 string
        String privateKeyPEM = key
        		.replace("-----BEGIN PRIVATE KEY-----", "")
        		.replaceAll(System.lineSeparator(), "")
        		.replace("-----END PRIVATE KEY-----", "");
        //decode the base64 private key bytes
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyPEM);
        //retrieve private key object
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);	
    }
    
    public void getServerPublicKey() {
    //TODO: change
    	serverPublicKey = publicKey;
    }
}
