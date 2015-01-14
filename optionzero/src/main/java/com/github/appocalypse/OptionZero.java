package com.github.appocalypse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

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
import com.google.api.services.gmail.model.ListThreadsResponse;
import com.google.api.services.gmail.model.Thread;

public class OptionZero {
	
	// Check https://developers.google.com/gmail/api/auth/scopes for all
	// available scopes
	private static final String SCOPE = "https://www.googleapis.com/auth/gmail.readonly";
	private static final String APP_NAME = "Option Zero";
	// Email address of the user, or "me" can be used to represent the currently
	// authorized user.
	private static final String USER = "me";

	public static void main(String[] args) throws Exception {
		HttpTransport httpTransport = new NetHttpTransport();
		JsonFactory jsonFactory = new JacksonFactory();

		final String clientSecretPath = args[0];
		
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, 
				new FileReader(clientSecretPath));

		// Allow user to authorize via url.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				httpTransport, jsonFactory, clientSecrets, Arrays.asList(SCOPE))
				.setAccessType("online").setApprovalPrompt("auto").build();

		String url = flow.newAuthorizationUrl().setRedirectUri(GoogleOAuthConstants.OOB_REDIRECT_URI).build();
		
		System.out.println("Open your web browser and authorize " + url);
		System.out.println("Enter the code: ");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine();

		// Generate Credential using retrieved code.
		GoogleTokenResponse response = flow.newTokenRequest(code)
				.setRedirectUri(GoogleOAuthConstants.OOB_REDIRECT_URI)
				.execute();
		
		GoogleCredential credential = new GoogleCredential()
				.setFromTokenResponse(response);

		// Create a new authorized Gmail API client
		Gmail service = new Gmail.Builder(httpTransport, jsonFactory,
				credential).setApplicationName(APP_NAME).build();

		// Retrieve a page of Threads; max of 100 by default.
		ListThreadsResponse threadsResponse = service.users().threads()
				.list(USER).execute();
		List<Thread> threads = threadsResponse.getThreads();

		// Print ID of each Thread.
		for (Thread thread : threads) {
			System.out.println("Thread ID: " + thread.getId());
			System.out.println(thread.toPrettyString());
			System.out.println("================================");
		}
	}
}
