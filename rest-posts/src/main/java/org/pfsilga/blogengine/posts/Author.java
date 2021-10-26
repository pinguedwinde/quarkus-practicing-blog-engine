package org.pfsilga.blogengine.posts;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class Author {

    @NotEmpty
    private String firstname;

    @NotEmpty
    private String lastname;

    @NotEmpty
    private String picture;

    @NotEmpty
    private String username;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return firstname.equals(author.firstname) && lastname.equals(author.lastname) && picture.equals(author.picture) && username.equals(author.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, picture, username);
    }

    @Override
    public String toString() {
        return "Author{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", picture='" + picture + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
