package com.example.comp.imagelist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ItemListActivity}.
 */
public class ItemDetailActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check);

        Button addButton = findViewById(R.id.addButton);
        dbHelper = new DBHelper(this);

        Cursor cursor = dbHelper.getCursorOnUrl(getIntent().getStringExtra("FULLURL"));

        if (cursor.moveToFirst()) {
            addButton.setText("Saved");
        }

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString("FULLURL",
                    getIntent().getStringExtra("FULLURL"));
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    public void addPhoto(View view) {
        Button button = (Button) view;
        if (!button.getText().equals("Saved")) {
            button.setText("Saved");
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("url", getIntent().getStringExtra("FULLURL"));
            db.insert(DBHelper.DB_NAME, null, cv);
        }
    }
}
