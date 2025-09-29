package com.openclassrooms.tajmahal.ui.restaurant;

import android.content.Context;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.openclassrooms.tajmahal.data.repository.RestaurantRepository;
import com.openclassrooms.tajmahal.domain.model.Restaurant;
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

    public void addNewReview(String username, String profilPicture, String comment, int rating){
        Review review = new Review(username,profilPicture,comment,rating);
        restaurantRepository.addReview(review);
    }

}