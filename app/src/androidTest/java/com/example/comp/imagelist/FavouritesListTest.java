package com.example.comp.imagelist;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.example.comp.imagelist.favourites.FavoritesListActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;


@RunWith(AndroidJUnit4.class)
public class FavouritesListTest {

    @Rule
    public ActivityTestRule<FavoritesListActivity> activityTestRule = new ActivityTestRule<>(FavoritesListActivity.class);
    private FavoritesListActivity mActivity = null;

    @Before
    public void setUp() {
        mActivity = activityTestRule.getActivity();
    }

    @Test
    public void testRecyclerCreating() {
        View recycler = mActivity.findViewById(R.id.favRecyclerList);
        assertNotNull(recycler);
    }

    @After
    public void tearDown() {
        mActivity = null;
    }
}