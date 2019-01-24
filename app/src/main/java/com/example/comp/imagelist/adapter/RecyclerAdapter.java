package com.example.comp.imagelist.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.comp.imagelist.ItemListActivity;
import com.example.comp.imagelist.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private ArrayList<Photo> photos = new ArrayList<>();
    private final ItemListActivity mParentActivity;

    public RecyclerAdapter(ItemListActivity mParentActivity) {
        this.mParentActivity = mParentActivity;
    }


    public void setPhotos(ArrayList<Photo> arg){
        photos = arg;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myitem, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int position) {
        recyclerViewHolder.bind(photos.get(position), mParentActivity);
    }


    @Override
    public int getItemCount() {
        return photos.size();
    }
}
