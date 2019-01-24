package com.example.comp.imagelist.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.comp.imagelist.ItemListActivity;
import com.example.comp.imagelist.R;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private ArrayList<Photo> photos = new ArrayList<>();
    private final ItemListActivity mParentActivity;

    public RecyclerAdapter(ItemListActivity mParentActivity) {
        this.mParentActivity = mParentActivity;
    }

    public void setData() {
        JsonAsyncTask jsonAsyncTask = new JsonAsyncTask();
        jsonAsyncTask.execute(ItemListActivity.requestUrl);
    }

    private class JsonAsyncTask extends AsyncTask<String, Void, ArrayList<Photo>> {

        @Override
        protected ArrayList<Photo> doInBackground(String... strings) {
            ArrayList<Photo> ans = new ArrayList<>();
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                for (JsonNode curImg : objectMapper.readTree(new URL(strings[0]))) {
                    String description = curImg.path("description").asText();
                    String smallUrl = curImg.path("urls").path("small").asText();

                    Bitmap bitmap;
                    InputStream downloadStream = (new URL(smallUrl)).openStream();
                    bitmap = BitmapFactory.decodeStream(downloadStream);

                    String fullUrl = curImg.path("urls").path("full").asText();

                    ans.add(new Photo(description, fullUrl, bitmap));

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ans;
        }

        @Override
        protected void onPostExecute(ArrayList<Photo> photosList) {
            super.onPostExecute(photosList);

            for (Photo photo : photosList) {
                photos.add(photo);
                notifyItemRangeInserted(photos.size() - 1, 1);
            }
        }
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myitem, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int position) {
        recyclerViewHolder.bind(photos.get(position), mParentActivity);
    }


    @Override
    public int getItemCount() {
        return photos.size();
    }
}
