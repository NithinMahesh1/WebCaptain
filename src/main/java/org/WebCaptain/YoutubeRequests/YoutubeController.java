package org.WebCaptain.YoutubeRequests;
import java.io.IOException;


//package org.WebCaptain.YoutubeRequests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class YoutubeController {

    // Process the response data here and extract the desired information
    // For example, you can use JSON parsing libraries like Jackson or Gson

    // Assuming the response is in JSON format and contains an array of items
    // You can adjust this code based on the actual response structure
    private YoutubeApiClient youtubeApiClient = new YoutubeApiClient();

    @GetMapping("/getVideoDetails")
    public String getVideoDetails(String channelName) throws IOException {
        String url = "https://www.googleapis.com/youtube/v3/search?&q=" + channelName + "&type=video&part=snippet";
        String response = youtubeApiClient.makeRequest(url);

        // Return the responses with channel videos list and metadata
        return response;
    }

    public void getThumbnails() {}

    public void getchannelTitle() {}
}
