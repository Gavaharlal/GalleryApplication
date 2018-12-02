package com.example.comp.imagelist.adapter;

import com.example.comp.imagelist.R;

import java.util.ArrayList;
import java.util.List;

public class ModelItem {
    private String description;
    private int imgId;

    private ModelItem(String description, int imgId) {
        this.description = description;
        this.imgId = imgId;
    }

    public static List<ModelItem> getItems() {
        ArrayList<ModelItem> itemList = new ArrayList<>();
        itemList.add(new ModelItem("Pic 1", R.mipmap.ic_launcher));
        itemList.add(new ModelItem("Pic 2", R.drawable.ic_launcher));
        itemList.add(new ModelItem("Pic 3", R.drawable.ic_launcher));
        itemList.add(new ModelItem("Pic 4", R.drawable.ic_launcher));
        itemList.add(new ModelItem("Pic 5", R.drawable.ic_launcher));
        itemList.add(new ModelItem("Pic 6", R.drawable.ic_launcher));
        itemList.add(new ModelItem("Pic 7", R.drawable.ic_launcher));
        itemList.add(new ModelItem("Pic 8", R.drawable.ic_launcher));
        return itemList;
    }

    public String getDescription() {
        return description;
    }

    int getImgId() {
        return imgId;
    }


}
