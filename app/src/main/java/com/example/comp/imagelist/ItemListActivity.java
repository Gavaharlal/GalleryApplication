package com.example.comp.imagelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.comp.imagelist.adapter.RecyclerAdapter;
import com.example.comp.imagelist.favourites.FavoritesList;
import com.example.comp.imagelist.retrofit.Photo;
import com.example.comp.imagelist.retrofit.JsonParsable;
import com.example.comp.imagelist.retrofit.MyRetrofitCreator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        JsonParsable modelApi = MyRetrofitCreator.constructModelApi();

        modelApi.parse().enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                adapter.setPhotos(response.body());
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void startFavorite(View view) {
        startActivity(new Intent(this, FavoritesList.class));
    }
}
