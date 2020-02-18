package org.vikulin.knittizer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import org.vikulin.knittizer.adapter.PartialKnittingExpandableListAdapter;
import org.vikulin.knittizer.model.PartialKnittingResult;

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
            resultString.add("Горизонтальная:"+String.format("%.2f", dw));
            resultString.add("Вертикальная:"+String.format("%.2f", dh));
            PartialKnittingExpandableListAdapter adapter = new PartialKnittingExpandableListAdapter(this, resultString, "Плотность образца", SAVE);
            resultListView.setAdapter(adapter);
            resultListView.expandGroup(0);
        } else {
            finish();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Toast.makeText(this, "Сохранено", Toast.LENGTH_SHORT).show();
        }
        if (resultCode == RESULT_CANCELED) {
            //nothing
        }
    }
}
