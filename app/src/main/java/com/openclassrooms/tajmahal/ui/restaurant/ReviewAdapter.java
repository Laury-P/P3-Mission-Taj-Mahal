package com.openclassrooms.tajmahal.ui.restaurant;




import static com.openclassrooms.tajmahal.ui.ImageLoader.loadProfilePicture;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.domain.model.Review;



/**
 * Adapter for recyclerView that display the list of reviews
 */
public class ReviewAdapter extends ListAdapter<Review, ReviewAdapter.ViewHolder> {


    /**
     * Constructor
     */
    public ReviewAdapter() { super(new ItemCallBack());}

    /**
     * Create a new ViewHolder
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ViewHolder(itemView);
    }

    /**
     * Bind the data to the view holder
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    /**
     * Bind the item_review to the correct data
     *
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView profilPicture;
        private final TextView name, comment;
        private final RatingBar rate;

        /**
         * Constructor that linked the data to the correspondent component of the view
         *
         * @param itemView The view of the item
         */
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            profilPicture = itemView.findViewById(R.id.profilePicture);
            name = itemView.findViewById(R.id.tvName);
            comment = itemView.findViewById(R.id.tvComment);
            rate = itemView.findViewById(R.id.Rating);
        }

        /**
         * Bind the data to the view holder
         *
         * @param review The review to bind
         */
        public void bind(Review review){
            loadProfilePicture(itemView.getContext(), review.getPicture(), profilPicture);
            name.setText(review.getUsername());
            comment.setText(review.getComment());
            rate.setRating(review.getRate());
        }
    }

    /**
     * DiffCallback for the list of reviews that make sure to not display the same review twice
     */
    private static class ItemCallBack extends DiffUtil.ItemCallback<Review>{
        @Override
        public boolean areItemsTheSame(@NonNull Review oldItem, @NonNull Review newItem) {
            return oldItem.getUsername().equals(newItem.getUsername()) && oldItem.getPicture().equals(newItem.getPicture());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Review oldItem, @NonNull Review newItem) {
            return oldItem.getComment().equals(newItem.getComment())
                    && oldItem.getRate() == newItem.getRate();
        }

    }
}
