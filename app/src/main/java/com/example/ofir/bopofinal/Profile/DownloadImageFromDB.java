package com.example.ofir.bopofinal.Profile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import java.io.InputStream;

/**
 * Created by Alonka on 13-Jan-17.
 */


    public class DownloadImageFromDB extends AsyncTask<String,Void,Bitmap> {

        ImageView profilePicImageView;

        public DownloadImageFromDB(ImageView profilePicImageView) {
            this.profilePicImageView = profilePicImageView;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {

            String pathToFile = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream in = new java.net.URL(pathToFile).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                profilePicImageView.setImageBitmap(bitmap);
            }
        }
}
