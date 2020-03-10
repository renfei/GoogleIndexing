package net.renfei.googleindexing;

import com.alibaba.fastjson.JSON;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.*;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.indexing.v3.model.UrlNotificationMetadata;
import net.renfei.googleindexing.entity.UrlNotification;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;

/**
 * Google Indexing API.
 * Prerequisites for the Indexing API:
 * https://developers.google.com/search/apis/indexing-api/v3/prereqs
 *
 * @author RenFei
 */
public class GoogleIndexing {
    private static final String SCOPES = "https://www.googleapis.com/auth/indexing";
    private static final String PUBLISH_POINT = "https://indexing.googleapis.com/v3/urlNotifications:publish";
    /**
     * HTTP transport.
     */
    private HttpTransport httpTransport;
    /**
     * Google Credential.
     */
    private GoogleCredential googleCredential;

    /**
     * @param jsonPrivateKeyPath Json Private Key Path
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public GoogleIndexing(String jsonPrivateKeyPath) throws GeneralSecurityException, IOException {
        this.httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        this.googleCredential = GoogleCredential.fromStream(new FileInputStream(jsonPrivateKeyPath), this.httpTransport, jsonFactory).createScoped(Collections.singleton(SCOPES));
    }

    /**
     * Notifies that a URL has been updated or deleted.
     *
     * @param urlNotification Request body
     * @return
     * @throws IOException
     */
    public UrlNotificationMetadata publish(UrlNotification urlNotification) throws IOException {
        GenericUrl genericUrl = new GenericUrl(PUBLISH_POINT);
        String content = JSON.toJSONString(urlNotification);
        HttpRequestFactory requestFactory = this.httpTransport.createRequestFactory();
        HttpRequest request =
                requestFactory.buildPostRequest(genericUrl, ByteArrayContent.fromString("application/json", content));
        googleCredential.initialize(request);
        return parse(request.execute());
    }

    private UrlNotificationMetadata parse(HttpResponse response) throws IOException {
        if (response.isSuccessStatusCode()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getContent()));
            StringBuilder sb = new StringBuilder();
            String sTempOneLine;
            while ((sTempOneLine = bufferedReader.readLine()) != null) {
                sb.append(sTempOneLine);
            }
            System.out.println("+++++");
            System.out.println(sb.toString());
            return JSON.parseObject(sb.toString(), UrlNotificationMetadata.class);
        } else {
            return null;
        }
    }
}
