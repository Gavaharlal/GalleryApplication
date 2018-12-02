package com.example.comp.imagelist.adapter;


import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.comp.imagelist.R;


class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView description;
    private ImageView image;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        description = itemView.findViewById(R.id.description);
        image = itemView.findViewById(R.id.img);
    }

    void bind(ModelItem modelItem) {
        image.setImageBitmap(BitmapFactory.decodeResource(itemView.getResources(), modelItem.getImgId()));
        description.setText(modelItem.getDescription());
    }
}
