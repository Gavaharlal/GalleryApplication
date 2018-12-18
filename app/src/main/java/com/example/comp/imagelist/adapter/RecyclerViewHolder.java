package com.example.comp.imagelist.adapter;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.comp.imagelist.R;


class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView description;
    private ImageView image;

    RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        description = itemView.findViewById(R.id.description);
        image = itemView.findViewById(R.id.img);
    }

    @SuppressLint("SetTextI18n")
    void bind(Photo modelItem) {
        description.setText(modelItem.getDescription() );
        image.setImageBitmap(modelItem.getImageBitmap());
    }
}




