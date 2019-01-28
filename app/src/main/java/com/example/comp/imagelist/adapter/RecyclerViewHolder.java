package com.example.comp.imagelist.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.comp.imagelist.ItemDetailActivity;
import com.example.comp.imagelist.R;
import com.example.comp.imagelist.utils.StringUtility;
import com.squareup.picasso.Picasso;


class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView description;
    private ImageView image;

    RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        description = itemView.findViewById(R.id.description);
        image = itemView.findViewById(R.id.img);
    }

    @SuppressLint("SetTextI18n")
    void bind(final Photo photo) {

        description.setText(photo.getDescription());
        Picasso.get().load(photo.getSmallUrl()).into(image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ItemDetailActivity.class);
                intent.putExtra(StringUtility.PHOTO, photo);
                context.startActivity(intent);
            }
        });
    }
}




