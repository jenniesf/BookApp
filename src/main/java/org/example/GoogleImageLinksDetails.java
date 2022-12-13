package org.example;

import java.util.Optional;

//     Optional<User> userOptional = userRepository.findById( userId );  // find user id
//        Note note = new Note(noteDto); // create new note
//        userOptional.ifPresent( note::setUser); // if user found, set note to user
//        noteRepository.saveAndFlush(note);      // save; to persist the action

public class GoogleImageLinksDetails {

    private String smallThumbnail = null;
    private String thumbnail = null;

    public GoogleImageLinksDetails() {
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

    @Override
    public String toString() {
        return "GoogleImageLinksDetails{" +
                "smallThumbnail=" + smallThumbnail +
                ", thumbnail=" + thumbnail +
                '}';
    }
}
