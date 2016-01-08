package mx.com.odraudek99.notificaciones;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@Component
public class TwitterInegration {


	@Value("${consumerKey}")
	String consumerKey;
	
	@Value("${consumerSecret}")
	String consumerSecret;
	
	
	@Value("${token}")
	String token;
	
	@Value("${tokenSecret}")
	String tokenSecret;
	
	@Value("${useId}")
	long useId;
	
	@Autowired
	MailService mailService;

	public void init()  {
		try {

		Twitter twitter = TwitterFactory.getSingleton();
//		twitter.setOAuthConsumer("[consumer key]", "[consumer secret]");
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
//		RequestToken requestToken = twitter.getOAuthRequestToken();
		AccessToken accessToken =null;//= new AccessToken(token, tokenSecret,useId);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		accessToken = new AccessToken(token, tokenSecret);
				
		
//		while (null == accessToken) {
//			System.out.println("Open the following URL and grant access to your account:");
//			System.out.println(requestToken.getAuthorizationURL());
//			System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
//			String pin = br.readLine();
//			try {
//				if (pin.length() > 0) {
//					accessToken = twitter.getOAuthAccessToken(requestToken, pin);
//				} else {
//					accessToken = twitter.getOAuthAccessToken();
//				}
//			} catch (TwitterException te) {
//				if (401 == te.getStatusCode()) {
//					System.out.println("Unable to get the access token.");
//				} else {
//					te.printStackTrace();
//				}
//			}
//		}
		
		
		// persist to the accessToken for future reference.
		//storeAccessToken(twitter.verifyCredentials().getId(), accessToken);
		twitter.setOAuthAccessToken(accessToken);
		twitter.verifyCredentials();
		
		System.out.println(twitter.getScreenName());
		
		 Query query = new Query("(linea12) OR (linea dorada) OR (lineaDorada)");
		 query.setCount(40);
		    QueryResult result = twitter.search(query);
		    
		    System.out.println("result.getCount(): "+result.getCount());
		    
		    for (Status status : result.getTweets()) {
		        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText()+ " |"+status.getCreatedAt());
		    }
		    
		    mailService.enviarMensaje(result);
		    
		    
	} catch (TwitterException e) {
		e.printStackTrace();
	}


	}

	private void imprimirDatosToken(long useId, AccessToken accessToken) {
		System.out.println("useId: "+useId);
		System.out.println("Token: "+accessToken.getToken());
		System.out.println("TokenSecret: "+accessToken.getTokenSecret());
		System.out.println("accessToken: "+accessToken.getUserId());
		// store accessToken.getToken()
		// store accessToken.getTokenSecret()
	}
}
