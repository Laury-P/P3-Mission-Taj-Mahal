package com.openclassrooms.tajmahal.ui.restaurant;



import androidx.lifecycle.LiveData;

import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;


import com.openclassrooms.tajmahal.data.repository.RestaurantRepository;

import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.domain.model.User;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


/**
 * NewReviewViewModel is responsible for preparing and managing the data for the
 * {@link NewReviewFragment}. It communicates with the {@link RestaurantRepository} to fetch
 * restaurant details witch include it's name and the list of review it received.
 *
 * The ViewModel is integrated with Hilt for dependency injection.
 */
@HiltViewModel
public class NewReviewViewModel extends ViewModel {

    private final RestaurantRepository restaurantRepository;

    /**
     * Constructor that Hilt will use to create an instance of NewReviewViewModel
     *
     * @param restaurantRepository The repository which will provide restaurant data
     */
    @Inject
    public NewReviewViewModel(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Retrieves from repository and expose to the view the reviews of the restaurant.
     * @return LiveData holding the list of reviews
     */
    public LiveData<List<Review>> getReviews() {
        return restaurantRepository.getReviews();
    }

    /**
     * Retrieves from repository and expose to the view the name of the restaurant.
     * @return String holding the name of the restaurant
     */
    public LiveData<String> getRestaurantName() {
        LiveData<String> restaurantName = Transformations.map(restaurantRepository.getRestaurant(), restaurant -> restaurant.getName());
        return restaurantName;
    }

    /**
     * Retrieves from repository and expose to the view the user details.
     * @return object {@link User} holding the user details
     */
    public User getUser() {
        return restaurantRepository.getUser();
    }

    /**
     * Verify the new review and add it to the restaurantRepository if correct
     * and send a feed back to the view.
     *
     * @param review The review to be added
     */
    public boolean addNewReview(Review review){
        boolean success;

        if (review.getComment().isEmpty()) { // Check if the comment is empty
            success = false;
        } else {
            if (review.getRate() <= 5 && review.getRate() >0){
                if(verifyClonedReview(review)){ // Check if the exact same review is already in the list
                    success = false;
                } else {
                    restaurantRepository.addReview(review);
                    success = true;// Check if the rating is correct
                }
            }else{
                throw new IllegalArgumentException("Rating must be between 1 and 5"); // Throw an exception if the rating is outside range
            }
        }

        return success;
    }

    /**
     * Verify if the review is already in the list of reviews to avoid spam review from an user
     * @param review The review to be verified
     * @return true if the review is already in the list, false otherwise
     */
    private boolean verifyClonedReview(Review review) {
        List<Review> reviews = restaurantRepository.getReviews().getValue();
        for (Review r : reviews) {
            if (r.getUsername().equals(review.getUsername()) && r.getComment().equals(review.getComment()) && r.getRate() == review.getRate()) {
                return true;
            }
        }
        return false;
    }

}