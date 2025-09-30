package com.openclassrooms.tajmahal.ui;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Class that prepare images for the view
 */
public class ImageLoader {

    /**
     * Methode that prepare the profile picture of the users for the view.
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadProfilePicture(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .circleCrop()
                .into(imageView);
    }
}
