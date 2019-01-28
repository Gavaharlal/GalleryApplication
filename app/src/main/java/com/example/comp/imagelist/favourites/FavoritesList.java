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

public class FavoritesList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.favRecyclerList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        RecyclerAdapter adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);


        List<Photo> photos = MyApplication.dataBaseHelper.getAllPhotos();
        if (photos.isEmpty()) {
            Toast.makeText(this, "You haven't got any favorite photos yet", Toast.LENGTH_LONG).show();
        } else {
            adapter.setPhotos(photos);
        }
    }

}
