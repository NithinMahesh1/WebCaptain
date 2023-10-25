package org.WebCaptain.YoutubeRequests;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class YoutubeController {  

    // Process the response data here and extract the desired information
    // For example, you can use JSON parsing libraries like Jackson or Gson

    // Assuming the response is in JSON format and contains an array of items
    // You can adjust this code based on the actual response structure
    private YoutubeApiClient youtubeApiClient = new YoutubeApiClient();

    @GetMapping("/getChannelDetails")
    public String getChannelDetails(@RequestParam String channelName, OAuth2AuthenticationToken authentication) throws IOException {
        String url = "https://www.googleapis.com/youtube/v3/search?&q=" + channelName + "&type=video&part=snippet";

        String registrationID = authentication.getAuthorizedClientRegistrationId();
        String username = authentication.getName();

        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(registrationID, username);
        String access_token = client.getAccessToken().getTokenValue();

        String response = youtubeApiClient.makeRequest(url, access_token);

        // Return the responses with channel videos list and metadata
        return response;
    }

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;
    @GetMapping("/getUserSubscriptions")
    public String getUserSubscriptions(OAuth2AuthenticationToken authentication) throws IOException {
        String url = "https://www.googleapis.com/youtube/v3/subscriptions?part=snippet&mine=true";

        // Get the registrationId (client) from the authentication
        String registrationId = authentication.getAuthorizedClientRegistrationId();
        // Get the name of the user
        String userName = authentication.getName();

        // Load the authorized client based on registrationId and user
        OAuth2AuthorizedClient authorizedClient =
                authorizedClientService.loadAuthorizedClient(registrationId, userName);

        // Extract the access token
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        String tokenValue = accessToken.getTokenValue();

        String response = youtubeApiClient.makeRequest(url, tokenValue);

        // Return users subscriptions to channels
        return response;
    }

    @GetMapping("/getUserPicture")
    public String getUserPicture(OAuth2AuthenticationToken authentication) throws IOException {
        String response = "";

        try {
            response = youtubeApiClient.retrieveUserPicture(authentication);
        }
        catch(Exception err) {
            response = err.getMessage();
        }

        return response;
    }

}
