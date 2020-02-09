package org.vikulin.knittizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void onTwoSideCalculationActivity(View view) {
        Intent intent = new Intent(this, TwoSideCalculationActivity.class);
        startActivity(intent);
    }

    public void onOneSideCalculationActivity(View view) {
        Intent intent = new Intent(this, OneSideCalculationActivity.class);
        startActivity(intent);
    }

    public void onDoubleFasonCalculationActivity(View view) {
        Intent intent = new Intent(this, DoubleFasonCalculationActivity.class);
        startActivity(intent);
    }

    public void onPartialKnittingCalculationActivity(View view) {
        Intent intent = new Intent(this, PartialKnittingCalculationActivity.class);
        startActivity(intent);
    }
}
