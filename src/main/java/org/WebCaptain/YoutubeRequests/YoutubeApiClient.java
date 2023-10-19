package org.WebCaptain.YoutubeRequests;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

public class YoutubeApiClient {

    private static final String access_token = "";

    public String makeRequest(String url, OAuth2AuthenticationToken authentication) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet subscription_request = new HttpGet(url);
        HttpPost token_request = new HttpPost("https://oauth2.googleapis.com/token");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", "authorization_code"));
        params.add(new BasicNameValuePair("scope", "https://www.googleapis.com/auth/youtube.readonly"));
//        params.add(new BasicNameValuePair("redirect_uri", "http://localhost:8080/getUserSubscriptions"));
//        params.add(new BasicNameValuePair("client_id", ""));
//        params.add(new BasicNameValuePair("client_secret", ""));
        params.add(new BasicNameValuePair("auth_url", "https://accounts.google.com/o/oauth2/auth"));

        token_request.setEntity(new UrlEncodedFormEntity(params));

        HttpResponse response = httpClient.execute(token_request);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            return entity.toString();
        }

        return null;
    }

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests(authorizeRequests ->
                            authorizeRequests
                                    .antMatchers("/", "/error").permitAll()
                                    .anyRequest().authenticated()
                    )
                    .oauth2Login(withDefaults());
        }
    }

}
