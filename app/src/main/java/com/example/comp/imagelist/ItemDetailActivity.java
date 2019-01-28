package com.example.comp.imagelist;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.comp.imagelist.adapter.Photo;
import com.example.comp.imagelist.utils.StringUtility;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ItemListActivity}.
 */
public class ItemDetailActivity extends AppCompatActivity {

    private Disposable disposable;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check);

        final Button addButton = findViewById(R.id.addButton);

        Photo photo = getIntent().getParcelableExtra(StringUtility.PHOTO);

        disposable = MyApplication
                .dataBaseHelper
                .containsPhoto(photo.getId())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {
                        if (aBoolean) {
                            addButton.setText(StringUtility.SAVED);
                        }
                    }
                });


        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(StringUtility.FULL_URL,
                    photo.getSmallUrl());
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    protected void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDestroy();
    }

    @SuppressLint("SetTextI18n")
    public void addPhoto(View view) {
        Button button = (Button) view;
        if (!button.getText().equals(StringUtility.SAVED)) {
            Photo photo = getIntent().getParcelableExtra(StringUtility.PHOTO);
            MyApplication.dataBaseHelper.insertPhoto(photo);
            button.setText(StringUtility.SAVED);
        }
    }
}
