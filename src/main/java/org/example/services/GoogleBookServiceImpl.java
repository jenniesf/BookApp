package org.example.services;

import org.example.GoogleBook;
import org.example.GoogleAPIGetRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GoogleBookServiceImpl implements GoogleBookService {
    // Override
    @Override
    public ArrayList<GoogleBook> searchBook(String userSearchRequest) throws Exception {

        GoogleAPIGetRequest newRequest = new GoogleAPIGetRequest();

        // will get back an ArrayList of max. 10 items from Google API
        return newRequest.getRequest(userSearchRequest);
    }
}
