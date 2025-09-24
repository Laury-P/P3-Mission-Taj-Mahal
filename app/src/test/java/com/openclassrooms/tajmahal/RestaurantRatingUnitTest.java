package com.openclassrooms.tajmahal;

import org.junit.Rule;
import org.junit.Test;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import static org.junit.Assert.*;

import com.openclassrooms.tajmahal.data.repository.RestaurantRepository;
import com.openclassrooms.tajmahal.data.service.RestaurantFakeApi;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.ui.restaurant.DetailsViewModel;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RestaurantRatingUnitTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    private RestaurantFakeApi fakeApi = new RestaurantFakeApi();
    private RestaurantRepository repository = new RestaurantRepository(fakeApi);
    private DetailsViewModel viewModel = new DetailsViewModel(repository);


    @Test
    public void fakeApi_getReviews_returnsReviews() {
        List<Review> reviews = fakeApi.getReviews();
        assertEquals(5, reviews.size());
    }

    @Test
    public void repository_getReviews_returnsReviews() throws InterruptedException {
        List<Review> reviews = getOrAwaitValue(repository.getReviews());

        assertEquals(5, reviews.size());
        assertEquals(5, reviews.get(0).getRate());
    }

    private <T> T getOrAwaitValue(LiveData<T> liveData) throws InterruptedException {
        final Object[] data = new Object[1];
        final CountDownLatch latch = new CountDownLatch(1);

        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(T o) {
                data[0] = o;
                latch.countDown();
                liveData.removeObserver(this);
            }
        };

        liveData.observeForever(observer);

        latch.await(2, TimeUnit.SECONDS);

        return (T) data[0];
    }
}