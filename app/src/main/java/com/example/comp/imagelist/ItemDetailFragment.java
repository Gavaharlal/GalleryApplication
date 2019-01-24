package com.example.comp.imagelist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {

    private ImageView imageView;

    private class LoaderAsyncTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap imgBitmap = null;
            String fullImgUrl = strings[0];
            try {
                String path = getContext().getCacheDir().getAbsolutePath() + "/" + fullImgUrl;
                File file = new File(path);
                if (!file.exists()) {
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    InputStream downloadStream = (new URL(fullImgUrl)).openStream();
                    OutputStream out = new BufferedOutputStream(new FileOutputStream(path));
                    byte[] buffer = new byte[1024];
                    int count;
                    while ((count = downloadStream.read(buffer, 0, 1024)) != -1) {
                        out.write(buffer, 0, count);
                    }
                    downloadStream.close();
                    out.close();
                }
                imgBitmap = BitmapFactory.decodeFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return imgBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        imageView = (ImageView) inflater.inflate(R.layout.item_detail, container, false);

        LoaderAsyncTask loaderAsyncTask = new LoaderAsyncTask();
        loaderAsyncTask.execute(getArguments().getString("FULLURL"));

        return imageView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageView = getActivity().findViewById(R.id.item_detail);
    }

}
