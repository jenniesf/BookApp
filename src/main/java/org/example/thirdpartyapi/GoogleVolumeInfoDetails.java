package org.example.thirdpartyapi;

import org.example.thirdpartyapi.GoogleImageLinksDetails;

import java.util.ArrayList;

// Google API - volumeInfo object details
public class GoogleVolumeInfoDetails {

    private String title;
    private ArrayList<String> authors;
    private String publishedDate;
    private String description;
    private GoogleImageLinksDetails imageLinks;
    private String infoLink;

    public GoogleVolumeInfoDetails() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GoogleImageLinksDetails getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(GoogleImageLinksDetails imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    @Override
    public String toString() {
        return "GoogleVolumeInfoDetails{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", publishedDate='" + publishedDate + '\'' +
                ", description='" + description + '\'' +
                ", imageLinks=" + imageLinks +
                ", infoLink='" + infoLink + '\'' +
                '}';
    }
}
