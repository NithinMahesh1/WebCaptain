package org.WebCaptain;
import org.WebCaptain.YoutubeRequests.YoutubeApiClient;
import org.WebCaptain.YoutubeRequests.YoutubeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(YoutubeApiClient.class, args);

        // Instantiate your controller or service class
        YoutubeController myController = new YoutubeController();

        // Call the method to make the web request
        try {
            String videoDetails = myController.getVideoDetails("ARAKO%20TV");
            System.out.println(videoDetails);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Hello world!");
    }
}