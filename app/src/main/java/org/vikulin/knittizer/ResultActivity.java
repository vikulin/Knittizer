package org.vikulin.knittizer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.ExpandableListView;
import org.vikulin.knittizer.adapter.ResultExpandableListAdapter;
import org.vikulin.knittizer.model.TwoSidesResult;

import java.util.ArrayList;

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
            ResultExpandableListAdapter adapter = new ResultExpandableListAdapter(this, result, startFromRow, numberOfRowSeries);
            resultListView.setAdapter(adapter);
        } else {
            finish();
        }

    }

}
