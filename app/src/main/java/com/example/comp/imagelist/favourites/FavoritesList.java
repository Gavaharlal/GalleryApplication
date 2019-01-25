package com.example.comp.imagelist.favourites;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.comp.imagelist.DBHelper;
import com.example.comp.imagelist.R;
import com.example.comp.imagelist.adapter.RecyclerAdapter;
import com.example.comp.imagelist.retrofit.Photo;
import com.example.comp.imagelist.retrofit.Urls;

import java.util.ArrayList;
import java.util.List;

public class FavoritesList extends AppCompatActivity {

    private List<Photo> photos = new ArrayList<>();

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

        Cursor cursor = new DBHelper(this).getCursor();
        if (cursor.moveToFirst()) {
            do {
                String url = cursor.getString(cursor.getColumnIndex("url"));
                photos.add(new Photo("", new Urls(url)));
            } while (cursor.moveToNext());
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "You haven't got any favorite photos yet", Toast.LENGTH_SHORT);
            toast.show();
        }

        adapter.setPhotos(photos);
    }

}
