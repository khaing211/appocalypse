package com.github.kn.appocalypse.emailscanner;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Inject;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class GmailServiceFactory {
    private static final Logger LOG = LoggerFactory.getLogger(GmailServiceFactory.class);

    private static final String APPLICATION_NAME = "EmailScanner";

    private static final List<String> SCOPES = Arrays.asList(GmailScopes.GMAIL_READONLY);

    private static final String CLIENT_SECRET_FILENAME = "client_secret.json";

    private static final String LOCAL_USER_ID = "scanner";

    private DataStoreFactory dataStoreFactory;

    private JsonFactory jsonFactory;

    private HttpTransport httpTransport;

    private File dataDir;

    private Credential authorize() throws IOException {
        final File clientSecret = new File(dataDir, CLIENT_SECRET_FILENAME);

        try (FileReader reader = new FileReader(clientSecret)) {
            final GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, reader);

            // Build flow and trigger user authorization request.
            final GoogleAuthorizationCodeFlow flow =
                    new GoogleAuthorizationCodeFlow.Builder(
                            httpTransport, jsonFactory, clientSecrets, SCOPES)
                            .setDataStoreFactory(dataStoreFactory)
                            .setAccessType("offline")
                            .build();

            final Credential credential = new AuthorizationCodeInstalledApp(
                    flow, new LocalServerReceiver())
                    .authorize(LOCAL_USER_ID);

            LOG.info("Google Authorization completed successfully", credential.getRefreshToken());

            return credential;
        }
    }

    public Gmail getService() throws IOException {
        final Credential credential = authorize();
        return new Gmail.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }


    @Inject
    public void setDataStoreFactory(DataStoreFactory dataStoreFactory) {
        this.dataStoreFactory = dataStoreFactory;
    }

    @Inject
    public void setJsonFactory(JsonFactory jsonFactory) {
        this.jsonFactory = jsonFactory;
    }

    @Inject
    public void setHttpTransport(HttpTransport httpTransport) {
        this.httpTransport = httpTransport;
    }

    @Value("${app.data.dir}")
    public void setDataDir(File dataDir) {
        this.dataDir = dataDir;
    }
}
