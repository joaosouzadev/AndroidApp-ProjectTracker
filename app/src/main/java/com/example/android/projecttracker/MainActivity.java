package com.example.android.projecttracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.projecttracker.data.ProjectContract;
import com.example.android.projecttracker.data.ProjectDbHelper;

public class MainActivity extends AppCompatActivity {

    private ProjectDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new ProjectDbHelper(this);
    }

    // atualiza a UI com o número certo de rows após sair do editoractivity
    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the projects database.
     */
    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        ProjectDbHelper mDbHelper = new ProjectDbHelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // retrieve data using cursor
        String[] projection = {
                ProjectContract.ProjectEntry._ID,
                ProjectContract.ProjectEntry.COLUMN_PROJECT_NAME,
                ProjectContract.ProjectEntry.COLUMN_PROJECT_TYPE,
                ProjectContract.ProjectEntry.COLUMN_PROJECT_CLIENT,
                ProjectContract.ProjectEntry.COLUMN_PROJECT_PRICE
        };

        Cursor cursor = db.query(
                ProjectContract.ProjectEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView displayView = (TextView) findViewById(R.id.text_view_projects);

        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).


            displayView.setText("The projects table contains  " + cursor.getCount() + " projects.\n\n");
            displayView.append(ProjectContract.ProjectEntry._ID + " - " +
                    ProjectContract.ProjectEntry.COLUMN_PROJECT_NAME + " - " +
                    ProjectContract.ProjectEntry.COLUMN_PROJECT_TYPE + " - " +
                    ProjectContract.ProjectEntry.COLUMN_PROJECT_CLIENT + " - " +
                    ProjectContract.ProjectEntry.COLUMN_PROJECT_PRICE + "\n");

            int idColumnIndex = cursor.getColumnIndex(ProjectContract.ProjectEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(ProjectContract.ProjectEntry.COLUMN_PROJECT_NAME);
            int typeColumnIndex = cursor.getColumnIndex(ProjectContract.ProjectEntry.COLUMN_PROJECT_TYPE);
            int clientColumnIndex = cursor.getColumnIndex(ProjectContract.ProjectEntry.COLUMN_PROJECT_CLIENT);
            int priceColumnIndex = cursor.getColumnIndex(ProjectContract.ProjectEntry.COLUMN_PROJECT_PRICE);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentType = cursor.getString(typeColumnIndex);
                String currentClient = cursor.getString(clientColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);

                displayView.append(("\n" + currentID + " - " + currentName + " - " + currentType + " - " + currentClient + " - " + currentPrice + "$"));
            }

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}
