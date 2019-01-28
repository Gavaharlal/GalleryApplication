package com.example.comp.imagelist.favourites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.comp.imagelist.MyApplication;
import com.example.comp.imagelist.R;
import com.example.comp.imagelist.adapter.Photo;
import com.example.comp.imagelist.adapter.RecyclerAdapter;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class FavoritesList extends AppCompatActivity {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView recyclerView = findViewById(R.id.favRecyclerList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);

        final Toast toast = Toast.makeText(this, "You haven't got any favorite photos yet", Toast.LENGTH_SHORT);

        compositeDisposable.add(
                MyApplication
                        .dataBaseHelper
                        .getAllPhotos()
                        .subscribe(
                                new Consumer<List<Photo>>() {
                                    @Override
                                    public void accept(List<Photo> photos) {
                                        if (photos.isEmpty()) {
                                            toast.show();
                                        } else {
                                            recyclerAdapter.setPhotos(photos);
                                        }
                                    }
                                }
                        )
        );
    }


    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
