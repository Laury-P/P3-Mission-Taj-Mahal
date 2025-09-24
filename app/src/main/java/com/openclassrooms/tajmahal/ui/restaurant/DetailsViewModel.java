package com.openclassrooms.tajmahal.ui.restaurant;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.data.repository.RestaurantRepository;
import com.openclassrooms.tajmahal.domain.model.Restaurant;
import com.openclassrooms.tajmahal.domain.model.RestaurantRating;
import com.openclassrooms.tajmahal.domain.model.Review;

import javax.inject.Inject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * MainViewModel is responsible for preparing and managing the data for the {@link DetailsFragment}.
 * It communicates with the {@link RestaurantRepository} to fetch restaurant details and provides
 * utility methods related to the restaurant UI.
 *
 * This ViewModel is integrated with Hilt for dependency injection.
 */
@HiltViewModel
public class DetailsViewModel extends ViewModel {

    private final RestaurantRepository restaurantRepository;

    private MutableLiveData<RestaurantRating> restaurantRating = new MutableLiveData<>();


    /**
     * Constructor that Hilt will use to create an instance of MainViewModel.
     *
     * @param restaurantRepository The repository which will provide restaurant data.
     */
    @Inject
    public DetailsViewModel(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Fetches the details of the Taj Mahal restaurant.
     *
     * @return LiveData object containing the details of the Taj Mahal restaurant.
     */
    public LiveData<Restaurant> getTajMahalRestaurant() {
        return restaurantRepository.getRestaurant();
    }

    /**
     * Fetches the rating details of the Taj Mahal restaurant.
     *
     *
     * @return LiveData object containing the rating details of the Taj Mahal restaurant.
     */
    public LiveData<RestaurantRating> getTajMahalRating() {
        return restaurantRating;
    }

    /**
     * Fetches the reviews for the Taj Mahal restaurant and calculates the average rating,
     * the number of review and the rating details.
     */
    public void updateRestaurantRating() {
        restaurantRepository.getReviews().observeForever(reviews -> {
            if (reviews != null && !reviews.isEmpty()) {
                int sumRating = 0;
                int numberOfReviews = reviews.size();
                Map<Integer, Integer> ratingDetails = new HashMap<>();

                // initialisation de la Map
                for (int i = 1; i <= 5; i++) {
                    ratingDetails.put(i, 0);
                }

                for (Review review : reviews) {
                    int rate = review.getRate();
                    sumRating += rate;
                    ratingDetails.put(rate, ratingDetails.get(rate) + 1);
                }

                float averageRating = sumRating / numberOfReviews;

                // Calcul des pourcentages des notes par catégories
                for (int i = 1; i <= 5; i++) {
                    ratingDetails.put(i, ratingDetails.get(i) * 100 / numberOfReviews);
                }

                restaurantRating.setValue(new RestaurantRating(averageRating, numberOfReviews, ratingDetails));
            } else {
                // Handle the case when there are no reviews
                restaurantRating.setValue(new RestaurantRating(0f,0, new HashMap<>()));
            }
        });
    }


    /**
     * Retrieves the current day of the week in French.
     *
     * @return A string representing the current day of the week in French.
     */
    public String getCurrentDay(Context context) {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String dayString;

        switch (dayOfWeek) {
            case Calendar.MONDAY:
                dayString = context.getString(R.string.monday);
                break;
            case Calendar.TUESDAY:
                dayString = context.getString(R.string.tuesday);
                break;
            case Calendar.WEDNESDAY:
                dayString = context.getString(R.string.wednesday);
                break;
            case Calendar.THURSDAY:
                dayString = context.getString(R.string.thursday);
                break;
            case Calendar.FRIDAY:
                dayString = context.getString(R.string.friday);
                break;
            case Calendar.SATURDAY:
                dayString = context.getString(R.string.saturday);
                break;
            case Calendar.SUNDAY:
                dayString = context.getString(R.string.sunday);
                break;
            default:
                dayString = "";
        }
        return dayString;
    }

}
