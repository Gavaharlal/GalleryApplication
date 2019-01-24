package com.example.comp.imagelist;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import com.example.comp.imagelist.adapter.Photo;
import com.example.comp.imagelist.adapter.RecyclerAdapter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
public class ListLoader extends IntentService {

    private ArrayList<Photo> photoArrayList = new ArrayList<>();

    private RecyclerAdapter recyclerAdapter;

    public ListLoader() {
        super("ListLoader");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String url = ItemListActivity.requestUrl;
        final ArrayList<Photo> ans = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            for (JsonNode curImg : objectMapper.readTree(new URL(url))) {
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
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                photoArrayList = ans;
                if (recyclerAdapter!=null){
                    recyclerAdapter.setPhotos(photoArrayList);
                }
            }
        });
    }

    public class MyBinder extends Binder {

        void setAdapter(final RecyclerAdapter adapter) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    recyclerAdapter = adapter;
                    adapter.setPhotos(photoArrayList);
            }
            });
        }
    }

    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public boolean onUnbind(Intent intent) {
        recyclerAdapter = null;
        return super.onUnbind(intent);
    }

}
