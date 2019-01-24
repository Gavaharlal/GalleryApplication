package com.example.comp.imagelist;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
public class ImageLoader extends IntentService {

    private Bitmap imageBitmap;
    private ImageView imageView;

    @Override
    protected void onHandleIntent(Intent intent) {
        Bitmap imgBitmap = null;
        try {
            String fullImgUrl = intent.getStringExtra("FULLURL");
            String path = getCacheDir().getAbsolutePath() + "/" + fullImgUrl;
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
        final Bitmap finalImgBitmap = imgBitmap;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                imageBitmap = finalImgBitmap;
                if (imageView!=null){
                    imageView.setImageBitmap(imageBitmap);
                }
            }
        });
    }

    public class MyBinder extends Binder {

        void setImgBitmap(final ImageView myView) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    imageView = myView;
                    myView.setImageBitmap(imageBitmap);
                }
            });
        }

    }
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public boolean onUnbind(Intent intent) {
        imageView = null;
        return super.onUnbind(intent);
    }

    public ImageLoader() {
        super("ListLoader");
    }
}
