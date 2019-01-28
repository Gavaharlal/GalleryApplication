package com.example.comp.imagelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.comp.imagelist.adapter.Photo;
import com.example.comp.imagelist.adapter.RecyclerAdapter;
import com.example.comp.imagelist.favourites.FavoritesList;
import com.example.comp.imagelist.retrofit.ModelPhoto;
import com.example.comp.imagelist.retrofit.UnsplashClient;
import com.example.comp.imagelist.retrofit.UnsplashService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private RecyclerAdapter recyclerAdapter;
    private static final String REQUEST = "photos?per_page=30&client_id=588504af4732dedfff1f7b64f0849b7bacb3d7ebf20e351f8bea66d084ef977b";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager verticalLinearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(verticalLinearLayoutManager);
        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);
        setModelPhotos(REQUEST);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    private void setModelPhotos(String request) {
        UnsplashService unsplashService = new UnsplashClient().createUnsplashService();
        compositeDisposable.add(
                unsplashService
                        .getModelPhotos(request)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Consumer<List<ModelPhoto>>() {
                                    @Override
                                    public void accept(List<ModelPhoto> modelPhotos) {
                                        List<Photo> photos = new ArrayList<>();
                                        for (ModelPhoto modelPhoto : modelPhotos) {
                                            photos.add(new Photo(
                                                    modelPhoto.getId(),
                                                    modelPhoto.getUrls().getSmallUrl(),
                                                    modelPhoto.getUrls().getFullUrl(),
                                                    modelPhoto.getDescription()
                                            ));
                                        }
                                        recyclerAdapter.setPhotos(photos);
                                    }

                                }, new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) {
                                        Log.d("DEBUG", throwable.getMessage());
                                    }
                                }
                        )
        );
    }

    public void startFavorite(View view) {
        startActivity(new Intent(this, FavoritesList.class));
    }
}
