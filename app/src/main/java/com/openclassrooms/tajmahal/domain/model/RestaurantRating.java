package com.openclassrooms.tajmahal.domain.model;

import java.util.Map;

/**
 * Represents a restaurant rating.
 * This class encapsulates all the détails of a review, including the average rating,
 * the number of reviews and the rating details such as the number of review per number
 * of star the user gave.
 */
public class RestaurantRating {

    /** The average rating of the restaurant. */
    private float averageRating;

    /** The total number of reviews for the restaurant. */
    private int numberOfReviews;

    /** A map containing the number of reviews per rating. Typically 1 to 5 stars. */
    private Map<Integer, Integer> ratingDetails;

    /**
     * Constructs a new RestaurantRating instance.
     *
     * @param averageRating the average rating of the restaurant
     * @param numberOfReviews the total number of reviews for the restaurant
     * @param ratingDetails a map containing the number of reviews per rating
     *
     */
    public RestaurantRating(float averageRating, int numberOfReviews, Map<Integer, Integer> ratingDetails) {
        this.averageRating = averageRating;
        this.numberOfReviews = numberOfReviews;
        this.ratingDetails = ratingDetails;
    }


    public float getAverageRating() {
        return averageRating;
    }


    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }


    public int getNumberOfReviews() {
        return numberOfReviews;
    }


    public void setNumberOfReviews(int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }


    public Map<Integer, Integer> getRatingDetails() {
        return ratingDetails;
    }


    public void setRatingDetails(Map<Integer, Integer> ratingDetails) {
        this.ratingDetails = ratingDetails;
    }
}
