package org.WebCaptain.YoutubeRequests;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;


import java.io.IOException;
import java.util.*;

public class YoutubeApiClient {
    public String makeRequest(String url, String access_token) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // Need to add a check for if we are sending a httpGet or httpPost
        HttpGet requestObject = new HttpGet(url);

        requestObject.addHeader("Authorization", "Bearer " +access_token);
        HttpResponse response = httpClient.execute(requestObject);

        if (response.getEntity() != null) {
            return EntityUtils.toString(response.getEntity());
        }

        return null;
    }

    public String retrieveUserPicture(OAuth2AuthenticationToken authentication)  {
        Map<String, Object> authentication_attributes = Collections.EMPTY_MAP;
        authentication_attributes = authentication.getPrincipal().getAttributes();

        return authentication_attributes.get("picture").toString();
    }

}
