package org.example.thirdpartyapi;

// book object to hold book from Google search to put into database

import java.util.ArrayList;

public class GoogleBook {
    private String title;
    private ArrayList<String> authors;
    private String publishedDate;
    private String description;
    private String smallThumbnail;
    private String thumbnail;
    private String infoLink;

    public GoogleBook(String title, ArrayList<String> authors, String publishedDate, String description, String smallThumbnail, String thumbnail, String infoLink) {
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.description = description;
        this.smallThumbnail = smallThumbnail;
        this.thumbnail = thumbnail;
        this.infoLink = infoLink;
    }

    public GoogleBook() {
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

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    @Override
    public String toString() {
        return "GoogleBook{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", publishedDate='" + publishedDate + '\'' +
                ", description='" + description + '\'' +
                ", smallThumbnail='" + smallThumbnail + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", infoLink='" + infoLink + '\'' +
                '}';
    }
}
