package org.WebCaptain.YoutubeRequests;
import java.io.IOException;
public class YoutubeController {
    private YoutubeApiClient youtubeApiClient = new YoutubeApiClient();

    public String getVideoDetails(String channelName) throws IOException {
        String url = "https://www.googleapis.com/youtube/v3/search?&q=" + channelName + "&type=video&part=snippet";
        String response = youtubeApiClient.makeRequest(url);

        // Handle the response and extract the desired information
        return response;
    }
}
