package org.vikulin.knittizer;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_w);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // landscape
            setContentView(R.layout.activity_main_menu_h);
        } else {
            // portrait
            setContentView(R.layout.activity_main_menu_w);
        }
    }

    public void onOneSideCalculationActivity(View view) {
        Intent intent = new Intent(this, OneSideCalculationActivity.class);
        startActivity(intent);
    }

    public void onPartialKnittingCalculationActivity(View view) {
        Intent intent = new Intent(this, PartialKnittingCalculationActivity.class);
        startActivity(intent);
    }

    public void onSavedListActivity(View view) {
        Intent intent = new Intent(this, SavedListActivity.class);
        startActivity(intent);
    }

    public void onSampleCalculationActivity(View view) {
        Intent intent = new Intent(this, SampleCalculationActivity.class);
        startActivity(intent);
    }
}
