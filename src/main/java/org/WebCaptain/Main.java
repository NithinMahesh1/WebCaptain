package org.WebCaptain;

import org.WebCaptain.YoutubeRequests.YoutubeController;
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

        // Call the method to make the web request
        try {
            String videoDetails = youtubeResponseController.getVideoDetails("Story%20Recapped");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}