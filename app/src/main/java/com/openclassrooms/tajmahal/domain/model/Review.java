package com.openclassrooms.tajmahal.domain.model;

import java.util.Objects;


/**
 * Represents a user review.
 * This class encapsulates all the details of a review, including the username of the reviewer,
 * their profile picture, the comment they left, and the rating they gave.
 */
public class  Review {

    /** The name of the user who left the review. */
    private String username;

    /** The profile picture of the user who left the review. */
    private String picture;

    /** The comment or feedback given by the user. */
    private String comment;

    /** The rating provided by the user. Typically out of 5 or 10. */
    private int rate;

    /**
     * Constructs a new Review instance.
     *
     * @param username the name of the user leaving the review
     * @param picture  the profile picture URL or path of the user
     * @param comment  the feedback or comment from the user
     * @param rate     the rating given by the user
     */
    public Review(String username, String picture, String comment, int rate) {
        this.username = username;
        this.picture = picture;
        this.comment = comment;
        this.rate = rate;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return rate == review.rate && Objects.equals(username, review.username) && Objects.equals(picture, review.picture) && Objects.equals(comment, review.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, picture, comment, rate);
    }
}
