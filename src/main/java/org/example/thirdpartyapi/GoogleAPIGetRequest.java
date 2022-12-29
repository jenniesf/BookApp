package org.example.thirdpartyapi;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


// GET request to Google book API
public class GoogleAPIGetRequest {

//    public static void main(String[] args) throws Exception {
//        String searchParam = "Harry Potter";
//        getRequest(searchParam);
//    }

    public GoogleAPIGetRequest() {}

    public ArrayList<GoogleBook> getRequest(String requestParam) throws Exception  {

        // validate parameters, remove " " characters from front end
//        ArrayList<String> requestParamModified = new ArrayList<>(List.of(requestParam.split("")));
//        if(requestParamModified.get(0).equals("\"") && requestParamModified.get(requestParamModified.size()-1).equals("\"")) {
//            requestParamModified.remove(0);
//            requestParamModified.remove(requestParamModified.size()-1);
//        }
//        requestParam = String.join("", requestParamModified);
//
//        System.out.println("user requested string modified: " + requestParam);

        requestParam = validateParam(requestParam);

        // refactor requestParam data coming in; replace spaces with +
//        ArrayList<String> requestFromUser = new ArrayList<>(List.of(requestParam.split(" ")));
//
//        System.out.println("user requested string: " + requestFromUser);
//
//        String requestFromUserString = (String.join("+" , requestFromUser));

        String requestFromUserString = refactorParam(requestParam);

        // create transcript object from POJO to json to pass into request
        GoogleAPIResponse transcript = new GoogleAPIResponse();

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(transcript);  // item to convert to json from POJO

        // send post request and let them know its a string
        HttpClient httpClient = HttpClient.newHttpClient();

        // set up a get request to get something back from API using the ID from the transcript
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://www.googleapis.com/books/v1/volumes?q=" + requestFromUserString ))
                // .header("Authorization", "APIKEY")
                .build();

        // store response in var
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        // can store the response back in the same transcript object
        transcript = gson.fromJson(getResponse.body(), GoogleAPIResponse.class);

        // response coming back from API will have a status and id and text - gson
            // will autopopulate these fields in the object.

        // print data
//        System.out.println(transcript.getKind());
//        System.out.println(transcript.getTotalItems());
//        System.out.println(transcript.getItems().size());  // get array size
        System.out.println(transcript);

        ArrayList<GoogleBook> bookSearchArray = new ArrayList<>();

        // print each element from items array. array coming back will be max of 10 elements
        for (int i = 0; i < transcript.getItems().size(); i++) {
//            System.out.println(transcript.getItems().get(i).getVolumeInfo().getTitle());
//            System.out.println(transcript.getItems().get(i).getVolumeInfo());

            String title = transcript.getItems().get(i).getVolumeInfo().getTitle();
            ArrayList<String> authors = transcript.getItems().get(i).getVolumeInfo().getAuthors();
            String publishedDate = transcript.getItems().get(i).getVolumeInfo().getPublishedDate();
            String description = transcript.getItems().get(i).getVolumeInfo().getDescription();
            String infoLink = transcript.getItems().get(i).getVolumeInfo().getInfoLink();

//            String smallThumbnail = String.valueOf(transcript.getItems().get(i).getVolumeInfo().getImageLinks().getSmallThumbnail());
//            String thumbnail = String.valueOf(transcript.getItems().get(i).getVolumeInfo().getImageLinks().getThumbnail());

            // book cover images/thumbnails
            String smallThumbnail = null;
            String thumbnail = null;

            if (String.valueOf(transcript.getItems().get(i).getVolumeInfo().getImageLinks()).equals("null") ){
                smallThumbnail = "";
                thumbnail = "";
            } else {
                smallThumbnail = String.valueOf(transcript.getItems().get(i).getVolumeInfo().getImageLinks().getSmallThumbnail());
                thumbnail = String.valueOf(transcript.getItems().get(i).getVolumeInfo().getImageLinks().getThumbnail());
            }

            // create GoogleBook with data back
            GoogleBook book = new GoogleBook(title, authors, publishedDate, description, smallThumbnail, thumbnail, infoLink);

            // push data into BooksArray
            bookSearchArray.add(book);
        }

//        System.out.println(transcript.getItems().get(0));  // get first item in array
//        System.out.println(transcript.getItems().get(0).getVolumeInfo().getTitle());  // get title
//        System.out.println(transcript.getItems().get(0).getVolumeInfo().getAuthors());  // get authors

        System.out.println(bookSearchArray);
        // System.out.println(bookSearchArray.get(9).getTitle());
        System.out.println("TRANSACTION COMPLETED");
        return bookSearchArray;
    }

    // HELPER FUNCTIONS
    public String validateParam(String requestParam) {
        // validate parameters, remove " " characters from frontend if exist
        ArrayList<String> requestParamModified = new ArrayList<>(List.of(requestParam.split("")));
        if(requestParamModified.get(0).equals("\"") && requestParamModified.get(requestParamModified.size()-1).equals("\"")) {
            requestParamModified.remove(0);
            requestParamModified.remove(requestParamModified.size()-1);
        }
        requestParam = String.join("", requestParamModified);

        System.out.println("user requested string modified: " + requestParam);
        return requestParam;
    }
    public String refactorParam(String requestParam) {
        // refactor requestParam data coming in client user; replace 'spaces' with '+'
        ArrayList<String> requestFromUser = new ArrayList<>(List.of(requestParam.split(" ")));
        System.out.println("user requested string: " + requestFromUser);
        String requestFromUserString = (String.join("+" , requestFromUser));
        return requestFromUserString;
    }


}
