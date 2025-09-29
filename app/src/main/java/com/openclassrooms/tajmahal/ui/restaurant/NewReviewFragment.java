package com.openclassrooms.tajmahal.ui.restaurant;

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


import com.openclassrooms.tajmahal.databinding.FragmentNewReviewBinding;
import com.openclassrooms.tajmahal.domain.model.Review;

import java.util.List;


import dagger.hilt.android.AndroidEntryPoint;

/**
 * NewReviewFragment is te view where client can leave review and see
 * other clients review.
 * This class uses {@link NewReviewViewModel} to interact with data sources and manage UI-related data
 * and {@link FragmentNewReviewBinding} for data binding to its layout.
 */
@AndroidEntryPoint
public class NewReviewFragment extends Fragment {

    private NewReviewViewModel newReviewViewModel;
    private FragmentNewReviewBinding binding;
    private ReviewAdaptateur reviewAdapter;


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

    }

    /**
     * Sets up the UI-specific properties, such as system UI flags and status bar color.
     */
    private void setupUI() {
        Window window = requireActivity().getWindow();
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    /**
     * Initializes the ViewModel for this activity.
     */
    private void setupViewModel() {
        newReviewViewModel = new ViewModelProvider(this).get(NewReviewViewModel.class);
    }

    private void setupRecyclerView() {
        reviewAdapter = new ReviewAdaptateur();
        binding.recyclerViewReview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewReview.setAdapter(reviewAdapter);
        newReviewViewModel.getReviews().observe(getViewLifecycleOwner(), reviews -> {
            reviewAdapter.submitList(reviews);
        });
    }



    //TODO: récupération du nouvel avis

    public static NewReviewFragment newInstance() {
        return new NewReviewFragment();
    }

}