package org.example.thirdpartyapi;

// Google API - Items object details

public class GoogleItemsDetails {
    private String id;
    private GoogleVolumeInfoDetails volumeInfo;

    public GoogleItemsDetails() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public GoogleVolumeInfoDetails getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(GoogleVolumeInfoDetails volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    @Override
    public String toString() {
        return "GoogleBookDetail{" +
                "id='" + id + '\'' +
                ", volumeInfo=" + volumeInfo +
                '}';
    }
}
