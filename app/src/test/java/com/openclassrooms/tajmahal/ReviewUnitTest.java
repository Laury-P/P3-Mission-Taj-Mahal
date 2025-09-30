package com.openclassrooms.tajmahal;

import com.openclassrooms.tajmahal.data.repository.RestaurantRepository;
import com.openclassrooms.tajmahal.data.service.RestaurantApi;
import com.openclassrooms.tajmahal.data.service.RestaurantFakeApi;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.ui.restaurant.NewReviewViewModel;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for the {@link NewReviewViewModel} class.
 */

public class ReviewUnitTest {
    private RestaurantApi fakeApi;
    RestaurantRepository repository;
    NewReviewViewModel viewModel;


    @Before
    public void setUp() {
        fakeApi = new RestaurantFakeApi();
        repository = new RestaurantRepository(fakeApi);
        viewModel = new NewReviewViewModel(repository);
    }


    /**
     * Test of the addNewReview() method of the ViewModel with a correct review.
     *
     * Scénario tested:
     * - Add a correct review (author, profilePictureUrl, comment, rating)
     * - The list of reviews should increase by 1
     * - The list should contain the new review at the beginning of the list
     *
     */
    @Test
    public void testAddCorrectReview(){
        Review correctReview = new Review("Manon Garcia", "URLProfilePicture","Très bon restaurant", 4);

        int listReviewSize = fakeApi.getReviews().size();

        viewModel.addNewReview(correctReview);

        assert(fakeApi.getReviews().size() == listReviewSize + 1);
        assert(fakeApi.getReviews().get(0) == correctReview);
    }

    /**
     * Test of the addNewReview() method of the ViewModel with an incorrect review with an empty comment.
     *
     * Scénario tested:
     *  - Add a review with an empty comment
     *  - The list of reviews should not increase
     *
     */
    @Test
    public void testAddReviewWithEmptyComment(){
        Review reviewWithEmptyComment = new Review("Manon Garcia", "URLProfilePicture","", 4);

        int listReviewSize = fakeApi.getReviews().size();

        viewModel.addNewReview(reviewWithEmptyComment);

        assert(fakeApi.getReviews().size() == listReviewSize);
    }

    /**
     * Test of the addNewReview() method of the ViewModel with a review with an invalid rating.
     *
     * Scenario tested:
     * - Add a review with an invalid rating : rating outside range or equal to 0.
     * - The list of reviews should not increase
     * - An exception should be raised
     *
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAddReviewWithInvalidRating(){
        Review reviewWithInvalidRating = new Review("Manon Garcia", "URLProfilePicture","Très bon restaurant", 11);
        Review reviewWithEmptyRating = new Review("Manon Garcia", "URLProfilePicture","Très bon restaurant", 0);

        int listReviewSize = fakeApi.getReviews().size();

        viewModel.addNewReview(reviewWithInvalidRating);
        viewModel.addNewReview(reviewWithEmptyRating);

        assert(fakeApi.getReviews().size() == listReviewSize);
    }

}
