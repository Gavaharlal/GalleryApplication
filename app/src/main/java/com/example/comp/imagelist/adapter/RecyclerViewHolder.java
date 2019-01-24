package com.example.comp.imagelist.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.comp.imagelist.ItemDetailActivity;
import com.example.comp.imagelist.ItemDetailFragment;
import com.example.comp.imagelist.ItemListActivity;
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
    void bind(final Photo photo, final ItemListActivity mParentActivity) {

        description.setText(photo.getDescription());
        image.setImageBitmap(photo.getSmallImageBitmap());
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ItemListActivity.isTwoPane()) {
                    Bundle arguments = new Bundle();
                    arguments.putString("FULLURL", photo.getSmallImgUrl());
                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    (mParentActivity).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra("FULLURL", photo.getSmallImgUrl());
                    context.startActivity(intent);
                }
            }
        });
    }
}




