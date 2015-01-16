package com.github.appocalypse.optionzero;

import java.io.FileReader;
import java.util.Arrays;

import com.github.appocalypse.optionzero.authorization.FirefoxAutomateAuthorizationFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;

public class GoogleService {
	// Check https://developers.google.com/gmail/api/auth/scopes for all
	// available scopes
	private static final String SCOPE = "https://www.googleapis.com/auth/gmail.readonly";
	private static final String APP_NAME = "Option Zero";
	
	final private HttpTransport httpTransport = new NetHttpTransport();
	final private JsonFactory jsonFactory = new JacksonFactory();
	final private AutomateAuthorizationFlow automateAuthorizationFlow = new FirefoxAutomateAuthorizationFlow();
	
	public Gmail connectToGmail(String clientSecretPath, String user, String pass) throws Exception {
		
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, 
				new FileReader(clientSecretPath));

		// Allow user to authorize via url.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				httpTransport, jsonFactory, clientSecrets, Arrays.asList(SCOPE))
				.setAccessType("online").setApprovalPrompt("auto").build();

		final String url = flow.newAuthorizationUrl().setRedirectUri(GoogleOAuthConstants.OOB_REDIRECT_URI).build();
		
		final String code = automateAuthorizationFlow.execute(url, user, pass);
		
		// Generate Credential using retrieved code.
		GoogleTokenResponse response = flow.newTokenRequest(code)
				.setRedirectUri(GoogleOAuthConstants.OOB_REDIRECT_URI)
				.execute();
		
		GoogleCredential credential = new GoogleCredential()
				.setFromTokenResponse(response);

		// Create a new authorized Gmail API client
		return new Gmail.Builder(httpTransport, jsonFactory, credential)
				.setApplicationName(APP_NAME)
				.build();
	}
}
