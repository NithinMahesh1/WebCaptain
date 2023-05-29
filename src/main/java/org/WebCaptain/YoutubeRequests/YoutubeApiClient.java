package org.WebCaptain.YoutubeRequests;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
public class YoutubeApiClient {
    private static final String API_KEY = "AIzaSyB_TPu9H_fuu84TJ6otEV_hgEsMnvoQHWk";

    public String makeRequest(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url + "&key=" + API_KEY);

        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            return EntityUtils.toString(entity);
        }

        return null;
    }
}
