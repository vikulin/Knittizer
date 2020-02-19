package org.vikulin.knittizer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import org.vikulin.knittizer.adapter.StringResultExpandableListAdapter;

import java.util.ArrayList;

public class SampleResultActivity extends AppCompatActivity {

    public static final String DW = "dw";
    public static final String DH = "dh";
    public static final int SAVE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_result);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ExpandableListView resultListView = findViewById(R.id.resultList);
            double dw = extras.getDouble(DW);
            double dh = extras.getDouble(DH);

            ArrayList<String> resultString = new ArrayList<>();
            resultString.add(String.format("%.2f", dw)+getResources().getString(R.string.w_density));
            resultString.add(String.format("%.2f", dh)+getResources().getString(R.string.h_density));
            StringResultExpandableListAdapter adapter = new StringResultExpandableListAdapter(this, resultString, getResources().getString(R.string.sample_density), SAVE);
            resultListView.setAdapter(adapter);
            resultListView.expandGroup(0);
        } else {
            finish();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Toast.makeText(this, getResources().getString(R.string.save_done), Toast.LENGTH_SHORT).show();
        }
        if (resultCode == RESULT_CANCELED) {
            //nothing
        }
    }
}
