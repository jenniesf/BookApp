package org.example.services;

import org.example.thirdpartyapi.GoogleBook;

import java.util.ArrayList;

public interface GoogleBookService {

    ArrayList<GoogleBook> searchBook(String userSearchRequest) throws Exception;

//    ArrayList<Book> searchBook(String userSearchRequest) throws Exception {
//        GoogleAPIGetRequest newRequest = new GoogleAPIGetRequest();
//
//        // will get back an ArrayList of max. 10 items from Google API
//        return newRequest.getRequest(userSearchRequest);
//    }
}
