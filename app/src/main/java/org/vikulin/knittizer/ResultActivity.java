package org.vikulin.knittizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.vikulin.knittizer.adapter.TwoPartsResultExpandableListAdapter;
import org.vikulin.knittizer.model.TwoPartsResult;

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
        getSupportActionBar().setTitle(R.string.title_activity_result);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ExpandableListView resultListView = findViewById(R.id.resultList);
            ArrayList<TwoPartsResult> result = (ArrayList<TwoPartsResult>) extras.getSerializable(RES);

            ArrayList<String> stringResults = new ArrayList<>();
            for(TwoPartsResult r:result){
                stringResults.add(r.toString());
            }
            int startFromRow = extras.getInt(START_FROM_ROW,0);
            int numberOfRowSeries = extras.getInt(NUMBER_OF_ROW_SERIES,1);
            int activity = extras.getInt(ACTIVITY);
            TwoPartsResultExpandableListAdapter adapter = new TwoPartsResultExpandableListAdapter(this, result, startFromRow, numberOfRowSeries, activity);
            resultListView.setAdapter(adapter);
            if(adapter.getGroupCount()==0){
                resultListView.setVisibility(View.GONE);
                findViewById(R.id.emptyListMessage).setVisibility(View.VISIBLE);
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
