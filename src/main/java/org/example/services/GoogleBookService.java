package org.example.services;

import org.example.Book;
import org.example.GoogleAPIGetRequest;

import java.util.ArrayList;
import java.util.List;

public interface GoogleBookService {

    ArrayList<Book> searchBook(String userSearchRequest) throws Exception;

//    ArrayList<Book> searchBook(String userSearchRequest) throws Exception {
//        GoogleAPIGetRequest newRequest = new GoogleAPIGetRequest();
//
//        // will get back an ArrayList of max. 10 items from Google API
//        return newRequest.getRequest(userSearchRequest);
//    }
}
