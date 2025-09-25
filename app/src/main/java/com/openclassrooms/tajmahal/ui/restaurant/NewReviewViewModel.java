package com.openclassrooms.tajmahal.ui.restaurant;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.openclassrooms.tajmahal.data.repository.RestaurantRepository;
import com.openclassrooms.tajmahal.domain.model.Review;

import java.util.List;

import javax.inject.Inject;

/**
 * NewReviewViewModel is responsible for preparing and managing the data for the
 * {@link NewReviewFragment}. It communicates with the {@link RestaurantRepository} to fetch
 * restaurant details witch include it's name and the list of review it received.
 *
 * The ViewModel is integrated with Hilt for dependency injection.
 */
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

   // TODO: Implement LiveData for name of restaurant if its not possible to pass it during the root

    public LiveData<List<Review>> getReviews() {
        return restaurantRepository.getReviews();
    }

}