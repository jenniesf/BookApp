package org.example.thirdpartyapi;

import java.util.ArrayList;

// Google API - object for JSON response from API

public class GoogleAPIResponse {
    private ArrayList<GoogleItemsDetails> items;
    private String kind;
    private int totalItems;

    public GoogleAPIResponse() {
    }

    public ArrayList<GoogleItemsDetails> getItems() {
        return items;
    }

    public void setItems(ArrayList<GoogleItemsDetails> items) {
        this.items = items;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "GoogleAPIResponse{" +
                "items=" + items +
                ", kind='" + kind + '\'' +
                ", totalItems=" + totalItems +
                '}';
    }
}
