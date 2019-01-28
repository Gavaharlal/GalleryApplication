package com.example.comp.imagelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.comp.imagelist.adapter.Photo;
import com.example.comp.imagelist.adapter.RecyclerAdapter;
import com.example.comp.imagelist.favourites.FavoritesList;
import com.example.comp.imagelist.retrofit.ModelPhoto;
import com.example.comp.imagelist.retrofit.UnsplashClient;
import com.example.comp.imagelist.retrofit.UnsplashService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager verticalLinearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(verticalLinearLayoutManager);
        final RecyclerAdapter adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

        UnsplashService unsplashService = new UnsplashClient().createUnsplashService();
        unsplashService.getModelPhotos().enqueue(new Callback<List<ModelPhoto>>() {
            @Override
            public void onResponse(Call<List<ModelPhoto>>  call, Response<List<ModelPhoto>> response) {
                List<Photo> result = new ArrayList<>();
                assert response.body() != null;
                for (ModelPhoto modelPhoto : response.body()) {
                    result.add(new Photo(modelPhoto.getId(),
                            modelPhoto.getUrls().getSmallUrl(),
                            modelPhoto.getUrls().getFullUrl(),
                            modelPhoto.getDescription()));
                }
                adapter.setPhotos(result);
            }

            @Override
            public void onFailure(Call<List<ModelPhoto>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void startFavorite(View view) {
        startActivity(new Intent(this, FavoritesList.class));
    }
}
