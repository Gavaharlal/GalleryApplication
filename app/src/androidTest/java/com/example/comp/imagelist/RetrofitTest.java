package com.example.comp.imagelist;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.comp.imagelist.retrofit.ModelPhoto;
import com.example.comp.imagelist.retrofit.UnsplashClient;
import com.example.comp.imagelist.retrofit.UnsplashService;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class RetrofitTest {

    private static final String REQUEST = "photos?per_page=30&client_id=588504af4732dedfff1f7b64f0849b7bacb3d7ebf20e351f8bea66d084ef977b";

    private static final String REQUEST_ONE_PHOTO = "photos?per_page=1&client_id=588504af4732dedfff1f7b64f0849b7bacb3d7ebf20e351f8bea66d084ef977b";
    private static final String SMALL_URL = "https://images.unsplash.com/photo-1543363950-c78545037afc?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjQ0Nzk0fQ";
    private static final String FULL_URL = "https://images.unsplash.com/photo-1543363950-c78545037afc?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjQ0Nzk0fQ";
    private static final String DESCRIPTION = "clear glass perfume bottle with box";

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Test
    public void testGettingModelFromRetrofit() {
        UnsplashService unsplashService = new UnsplashClient().createUnsplashService();

        compositeDisposable.add(
                unsplashService
                        .getModelPhotos(REQUEST)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Consumer<List<ModelPhoto>>() {
                                    @Override
                                    public void accept(List<ModelPhoto> modelPhotos) {
                                        assertNotNull(modelPhotos);
                                        Assert.assertEquals(30, modelPhotos.size());
                                        boolean eachNotNull = true;
                                        for (ModelPhoto modelPhoto : modelPhotos) {
                                            eachNotNull &= modelPhoto != null;
                                        }
                                        Assert.assertTrue(eachNotNull);
                                    }

                                }, new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) {
                                        Log.d("DEBUG", throwable.getMessage());
                                    }
                                }
                        )
        );
    }

    @Test
    public void testRequestOnePhoto() {

        UnsplashService unsplashService = new UnsplashClient().createUnsplashService();

        compositeDisposable.add(
                unsplashService
                        .getModelPhotos(REQUEST_ONE_PHOTO)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Consumer<List<ModelPhoto>>() {
                                    @Override
                                    public void accept(List<ModelPhoto> modelPhotos) {
                                        assertNotNull(modelPhotos);
                                        Assert.assertEquals(1, modelPhotos.size());
                                        ModelPhoto modelPhoto = modelPhotos.get(0);
                                        Assert.assertNotNull(modelPhoto);
                                        Assert.assertEquals(modelPhoto.getUrls().getSmallUrl(), SMALL_URL);
                                        Assert.assertEquals(modelPhoto.getUrls().getFullUrl(), FULL_URL);
                                        Assert.assertEquals(modelPhoto.getDescription(), DESCRIPTION);
                                    }

                                }, new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) {
                                        Log.d("DEBUG", throwable.getMessage());
                                    }
                                }
                        )
        );
    }

    @After
    public void tearDown() {
        compositeDisposable.clear();
    }
}
