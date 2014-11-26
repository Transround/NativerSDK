
package com.transround.dev.localizationdemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class FruitActivity extends Activity {
    public static final String TAG = "FruitActivity";

    TextView mFruitTextView;

    Spinner mFruitSpinner;
    Button mTranslateButton;
    Button mLanguagePractiseButton;
    ListView mFruitDetailListView;

    List<Fruit> mFruitList = new ArrayList<Fruit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fruit_activity_main);

        initFruitList();

        initFruitSpinner();

        initFruitDetailList();
    }

    private void initFruitDetailList() {
        mFruitDetailListView = (ListView) findViewById(R.id.localization_demo_list_language_detail);
    }

    private void initFruitSpinner() {
        FruitSpinnerAdapter fruitSpinnerAdapter = new FruitSpinnerAdapter(this,
                mFruitList);

        mFruitTextView = (TextView) findViewById(R.id.localization_demo_label_language);
        mFruitTextView.setText(getString(R.string.localization_demo_label_fruit));

        mFruitSpinner = (Spinner) findViewById(R.id.localization_demo_spinner_language);
        mFruitSpinner.setAdapter(fruitSpinnerAdapter);
        mFruitSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                    int pos, long id) {
                setFruitDisplayByPosition(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initFruitList() {
        try {
            FiguresJsonParser<Fruit> jsonParser = new FruitJsonParser();

            mFruitList = jsonParser.parseJsonStream(getResources()
                    .openRawResource(
                            getResources().getIdentifier("raw/fruits", "raw",
                                    getPackageName())));
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_help:
                startHelpActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startHelpActivity() {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    private void setFruitDisplayByPosition(int pos) {
        Fruit fruit = mFruitList.get(pos);
        FruitDetailListAdapter fruitDetailAdapter = new FruitDetailListAdapter(
                this, fruit);

        mFruitDetailListView.setAdapter(fruitDetailAdapter);
    }

}
