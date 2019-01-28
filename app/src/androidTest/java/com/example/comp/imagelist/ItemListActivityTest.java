package com.example.comp.imagelist;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;


@RunWith(AndroidJUnit4.class)
public class ItemListActivityTest {

    @Rule
    public ActivityTestRule<ItemListActivity> activityTestRule = new ActivityTestRule<>(ItemListActivity.class);
    private ItemListActivity mActivity = null;

    @Before
    public void setUp() {
        mActivity = activityTestRule.getActivity();
    }

    @Test
    public void testRecyclerCreating() {
        View recycler = mActivity.findViewById(R.id.recycler);
        assertNotNull(recycler);
    }

    @After
    public void tearDown() {
        mActivity = null;
    }
}