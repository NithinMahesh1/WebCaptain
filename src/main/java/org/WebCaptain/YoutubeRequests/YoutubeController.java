package org.WebCaptain.YoutubeRequests;
import java.io.IOException;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
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
        System.out.println("This is the output from REST call: " +url);

        String response = youtubeApiClient.makeRequest(url, authentication);

        // Return the responses with channel videos list and metadata
        return response;
    }

    @GetMapping("/getUserSubscriptions")
    public String getUserSubscriptions(OAuth2AuthenticationToken authentication) throws IOException {
        String url = "https://www.googleapis.com/youtube/v3/subscriptions?part=snippet&mine=true";
        String response = youtubeApiClient.makeRequest(url, authentication);

        // Return the responses with channel videos list and metadata
        return response;
    }

}
