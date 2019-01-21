package com.example.comp.imagelist;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.comp.imagelist.adapter.Photo;
import com.example.comp.imagelist.adapter.RecyclerAdapter;
import com.example.comp.imagelist.dummy.DummyContent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {

    private ImageView imageView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    private class LoaderAsyncTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;
            try {
                Log.d("228SASHA", strings[0]);
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
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("228SASHA", "ololo");
        imageView = getActivity().findViewById(R.id.imageViewDetail);

        if (getArguments() != null && getArguments().containsKey("FullURL")) {
            Log.d("228SASHA", "ololo2");

            LoaderAsyncTask loaderAsyncTask = new LoaderAsyncTask();
            loaderAsyncTask.execute(getArguments().getString("FullURL"));
        }
    }

}
