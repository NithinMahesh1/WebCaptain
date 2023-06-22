package org.WebCaptain;
import org.WebCaptain.YoutubeRequests.YoutubeApiClient;
import org.WebCaptain.YoutubeRequests.YoutubeController;
import org.WebCaptain.YoutubeRequests.ChannelResController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

        // Instantiate controllers
        YoutubeController youtubeResponseController = new YoutubeController();
        ChannelResController youtubeRESTController = new ChannelResController();

        // Call the method to make the web request
        try {
            String videoDetails = youtubeResponseController.getVideoDetails("ARAKO%20TV");
            List<String> titles = youtubeRESTController.getTitles(videoDetails);
//            System.out.println(videoDetails);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}