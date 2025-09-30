package com.openclassrooms.tajmahal.ui.restaurant;

import static com.openclassrooms.tajmahal.ui.ImageLoader.loadProfilePicture;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;


import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.databinding.FragmentNewReviewBinding;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.domain.model.User;


import dagger.hilt.android.AndroidEntryPoint;

/**
 * NewReviewFragment is the view where client can leave review and see
 * other clients review.
 *
 * This class uses {@link NewReviewViewModel} to interact with data sources and manage UI-related data
 * and {@link FragmentNewReviewBinding} for data binding to its layout.
 */
@AndroidEntryPoint
public class NewReviewFragment extends Fragment {

    private NewReviewViewModel newReviewViewModel;
    private FragmentNewReviewBinding binding;
    private ReviewAdapter reviewAdapter;



    /**
     * This method is called when the fragment is first created.
     * It's used to perform one-time initialization.
     *
     * @param savedInstanceState A bundle containing previously saved instance state.
     * If the fragment is being re-created from a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * Creates and returns the view hierarchy associated with the fragment.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to.
     * The fragment should not add the view itself but return it.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @return Returns the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewReviewBinding.inflate(inflater, container, false); // Binds the layout using view binding.
        return binding.getRoot(); // Returns the root view.
    }


    /**
     * This method is called immediately after `onCreateView()`.
     * Use this method to perform final initialization once the fragment views have been inflated.
     *
     * @param view The View returned by `onCreateView()`.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUI(); // Sets up user interface components.
        setupViewModel(); // Prepares the ViewModel for the fragment.
        setupRecyclerView();// Sets up the RecyclerView for displaying reviews.
        binding.buttonSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitReview();
            } // Submits the new review when the button is clicked.
        });
        binding.topBand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });
    }

    /**
     * This methode fetch the necessary information to create a new review, send them to the viewmodel to be verified
     * and saved in the database.
     * It update the UI with the new list of reviews if the review is deemed correct.
     *
     */
    private void submitReview() {
        String name = newReviewViewModel.getUser().getName(); // Get the name of the user leaving the review.
        String profilPicture = newReviewViewModel.getUser().getProfilPicture(); // Get the profil picture of the user leaving the review.
        String comment = binding.userComment.getText().toString().trim();// The comment is trimed to assured that a comment with a " " isn't considered valid
        int rating = (int) binding.userRating.getRating(); // Get the rating of the user leaving the review.
        Review review = new Review(name, profilPicture, comment, rating); // Create a review holding all the information provided by the user


        if(newReviewViewModel.addNewReview(review)){ // Send the review to the viewmodel to be verified and saved in the database
            binding.userComment.setText(""); // Clear the comment field
            binding.userRating.setRating(5);  // Reset the rating field
            reviewAdapter.notifyDataSetChanged(); // Notify the recycler view that the data has changed
        } else if (review.getComment().isEmpty()) { // Verify if the validation as failed because of an empty comment
            binding.userComment.setError(getString(R.string.error_empty_comment)); // Set an error message for the empty comment field
        }

    }


    /**
     * Sets up the UI-specific properties, such as system UI flags and status bar color.
     */
    private void setupUI() {
        Window window = requireActivity().getWindow();
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        );
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    /**
     * Initializes the ViewModel for this activity.
     */
    private void setupViewModel() {
        newReviewViewModel = new ViewModelProvider(this).get(NewReviewViewModel.class);
        newReviewViewModel.getRestaurantName().observe(getViewLifecycleOwner(), restaurantName -> {
            binding.tvRestaurantName.setText(restaurantName); // Update the restaurant name in the UI.
        });
        setupUserProfil(); // Sets up the user's profile picture and name in the UI.
    }

    /**
     * Sets up the user's profile picture and name in the UI.
     */
    private void setupUserProfil(){
        User user = newReviewViewModel.getUser();
        binding.tvUserName.setText(user.getName());
        loadProfilePicture(requireContext(),user.getProfilPicture(),binding.userProfilePicture);
    }

    /**
     * Sets up the RecyclerView for displaying reviews.
     */
    private void setupRecyclerView() {
        reviewAdapter = new ReviewAdapter();
        binding.recyclerViewReview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewReview.setAdapter(reviewAdapter);
        newReviewViewModel.getReviews().observe(getViewLifecycleOwner(), reviews -> {
            reviewAdapter.submitList(reviews);
        });
    }


    public static NewReviewFragment newInstance() {
        return new NewReviewFragment();
    }

}