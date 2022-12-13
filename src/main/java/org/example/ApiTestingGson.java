package org.example;

import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.URL;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiTestingGson {
    public static void main(String[] args) throws Exception {

        // create transcript object from POJO to json to pass into request
        Transcript transcript = new Transcript();
        transcript.setAudio_url("github.com");
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(transcript);  // item to convert to json from POJO

        // create a post request
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://www.googleapis.com/books/v1/volumes?q=harry+potter"))
                .header("Authorization", "APIKEY")
                .POST(BodyPublishers.ofString(jsonRequest))
                .build();

        // send post request and let them know its a string
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());

        // get body of response
        // System.out.println(postResponse.body());  // can print out if want
        // convert the json response into an object - takes in the response and class you to convert
            // and assign the object we have created above to this new json response
        transcript = gson.fromJson(postResponse.body(), Transcript.class );

        // get ID from the transcript class
        System.out.println(transcript.getId());


        // set up a get request to get something back from API using the ID from the transcript
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://www.googleapis.com/books/v1/volumes?q=harry+potter"))
                // .header("Authorization", "APIKEY")
                .build();

        // create while to keep sending get request until the status returned is 'completed'
        while(true) {
            // store response in var
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

            // can store the response back in the same transcript object
            transcript = gson.fromJson(getResponse.body(), Transcript.class);

                // response coming back from API will have a status and id and text - gson
                // will auto populate these fields in the object.

            // print that status in each loop
            System.out.println(transcript.getStatus());

            // check status - and break out of while loop
            if("completed".equals(transcript.getStatus()) || "error".equals(transcript.getStatus())){
                break;
            }
            // wait for a second before making another call
            Thread.sleep(1000);
        }

        // after loop is done, print done
        System.out.println("transaction completed");
        System.out.println(transcript.getText());
    }
}
