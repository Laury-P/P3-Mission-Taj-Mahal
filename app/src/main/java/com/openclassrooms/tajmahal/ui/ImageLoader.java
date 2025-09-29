package com.openclassrooms.tajmahal.ui;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Class that prepare the profil picture of the users for the view
 */
public class ImageLoader {

    /**
     * Methode that prepare the profil picture of the users for the view
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadProfilPicture(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .circleCrop()
                .into(imageView);
    }
}
