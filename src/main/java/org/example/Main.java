package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws Exception {

        // run google book API
        // GoogleAPIGetRequest newRequest = new GoogleAPIGetRequest();
        // newRequest.getRequest("harry potter");

        // will get back an ArrayList of max. 10 items from Google API
        // System.out.println(newRequest.getRequest("harry potter"));

        SpringApplication.run(Main.class, args);
    }
}