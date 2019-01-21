package com.example.comp.imagelist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.comp.imagelist.adapter.RecyclerAdapter;
import com.example.comp.imagelist.dummy.DummyContent;

import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private static boolean mTwoPane;

    static public boolean isTwoPane() {
        return mTwoPane;
    }

    public final static String requestUrl = "https://api.unsplash.com/photos?per_page=30&client_id=588504af4732dedfff1f7b64f0849b7bacb3d7ebf20e351f8bea66d084ef977b";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager verticalLinearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(verticalLinearLayoutManager);

        RecyclerAdapter adapter = new RecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setData();
    }
}