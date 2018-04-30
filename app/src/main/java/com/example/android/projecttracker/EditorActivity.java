package com.example.android.projecttracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.android.projecttracker.data.ProjectContract;

/**
 * Created by JOAO on 30-Apr-18.
 */

public class EditorActivity extends AppCompatActivity {

    /**
     * EditText field to enter the project's name
     */
    private EditText mNameEditText;

    /**
     * EditText field to enter the project's client
     */
    private EditText mClientEditText;

    /**
     * EditText field to enter the project's price
     */
    private EditText mPriceEditText;

    /**
     * EditText field to enter the project's type
     */
    private Spinner mProjectSpinner;

    /**
     * Type of the Project
     */
    private String mProjectType = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we will need to read user input from
        mNameEditText = (EditText) findViewById(R.id.edit_project_name);
        mClientEditText = (EditText) findViewById(R.id.edit_project_client);
        mPriceEditText = (EditText) findViewById(R.id.edit_project_price);
        mProjectSpinner = (Spinner) findViewById(R.id.spinner_project_type);

        setupSpinner();
    }

    /**
     * Setup the dropdown spinner that allows the user to select the gender of the pet.
     */
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // The spinner will use the default layout
        ArrayAdapter projectSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_project_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        projectSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mProjectSpinner.setAdapter(projectSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mProjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.project_type_frontend))) {
                        mProjectType = ProjectContract.ProjectEntry.TYPE_FRONTEND; // Front-end
                    } else if (selection.equals(getString(R.string.project_type_backend))) {
                        mProjectType = ProjectContract.ProjectEntry.TYPE_BACKEND; // Back-end
                    } else if (selection.equals(getString(R.string.project_type_androidapp))) {
                        mProjectType = ProjectContract.ProjectEntry.TYPE_ANDROIDAPP; // Android App
                    } else {
                        mProjectType = "Tipo de Projeto não selecionado"; // nada foi selecionado
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mProjectType = "Tipo de Projeto não selecionado"; // Unknown
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                //inserProject();
                finish();
                return true;
            // Respond to a click on the "Clear" menu option
            case R.id.action_clear:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (MainActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
