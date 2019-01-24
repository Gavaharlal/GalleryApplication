package com.example.comp.imagelist;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {

    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        imageView = (ImageView) inflater.inflate(R.layout.item_detail, container, false);

        String fullUrl = getArguments().getString("FULLURL");
        Context context = getContext();
        Intent intent = new Intent(context, ImageLoader.class);
        intent.putExtra("FULLURL", fullUrl);
        context.startService(intent);

        context.bindService(new Intent(context, ImageLoader.class), serviceConnection, 0);

        return imageView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        getContext().unbindService(serviceConnection);
        super.onDestroy();
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ImageLoader.MyBinder binder = (ImageLoader.MyBinder) service;
            binder.setImgBitmap(imageView);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };
}
