package com.example.comp.imagelist.adapter;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.comp.imagelist.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView description;
    private ImageView image;

    RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        description = itemView.findViewById(R.id.description);
        image = itemView.findViewById(R.id.img);
    }

    void bind(Photo modelItem) {
        PictureAsyncTask pictureAsyncTask = new PictureAsyncTask();
        pictureAsyncTask.execute(modelItem.getSmallImgUrl());
        description.setText(modelItem.getDescription());
    }

    private class PictureAsyncTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {

            Bitmap bitmap = null;
            try {
                InputStream downloadStream = (new URL(strings[0])).openStream();
                bitmap = BitmapFactory.decodeStream(downloadStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            image.setImageBitmap(bitmap);
        }
    }

}




