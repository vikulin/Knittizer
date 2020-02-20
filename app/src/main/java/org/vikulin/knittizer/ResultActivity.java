package org.vikulin.knittizer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import org.vikulin.knittizer.adapter.ResultExpandableListAdapter;
import org.vikulin.knittizer.model.TwoSidesResult;

import java.util.ArrayList;

import static org.vikulin.knittizer.SavingActivity.ACTIVITY;

public class ResultActivity extends AppCompatActivity {

    public static final String RES = "result";
    public static final String START_FROM_ROW = "start_from_row";
    public static final String NUMBER_OF_ROW_SERIES = "number_of_row_series";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ExpandableListView resultListView = findViewById(R.id.resultList);
            ArrayList<TwoSidesResult> result = (ArrayList<TwoSidesResult>) extras.getSerializable(RES);

            ArrayList<String> stringResults = new ArrayList<>();
            for(TwoSidesResult r:result){
                stringResults.add(r.toString());
            }
            int startFromRow = extras.getInt(START_FROM_ROW,0);
            int numberOfRowSeries = extras.getInt(NUMBER_OF_ROW_SERIES,1);
            int activity = extras.getInt(ACTIVITY);
            ResultExpandableListAdapter adapter = new ResultExpandableListAdapter(this, result, startFromRow, numberOfRowSeries, activity);
            resultListView.setAdapter(adapter);
            if(adapter.getGroupCount()==0){
                resultListView.setVisibility(View.GONE);
                findViewById(R.id.emptyListMessage).setVisibility(View.VISIBLE87);
            }
        } else {
            finish();
        }
    }

    @Override
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
