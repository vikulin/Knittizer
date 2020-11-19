package org.vikulin.knittizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SampleCalculationActivity extends AlertActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_calculation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.sample_calculate_menu);
    }

    public void calculate(View view) {
        EditText editW = findViewById(R.id.editW);
        EditText editH = findViewById(R.id.editH);
        EditText editU = findViewById(R.id.editU);
        EditText editR = findViewById(R.id.editR);
        if(editW.length()==0){
            editW.setError(getResources().getString(R.string.empty_value_error));
            return;
        }
        if(editH.length()==0){
            editH.setError(getResources().getString(R.string.empty_value_error));
            return;
        }
        if(editU.length()==0){
            editU.setError(getResources().getString(R.string.empty_value_error));
            return;
        }
        if(editR.length()==0){
            editR.setError(getResources().getString(R.string.empty_value_error));
            return;
        }
        int u = Integer.parseInt(editU.getText().toString());
        double w = Double.parseDouble(editW.getText().toString());
        int r = Integer.parseInt(editR.getText().toString());
        double h = Double.parseDouble(editH.getText().toString());
        if(u==0){
            editU.setError(getResources().getString(R.string.zero_value_error));
            return;
        }
        if(w==0d){
            editW.setError(getResources().getString(R.string.zero_value_error));
            return;
        }
        if(r==0){
            editR.setError(getResources().getString(R.string.zero_value_error));
            return;
        }
        if(h==0d){
            editH.setError(getResources().getString(R.string.zero_value_error));
            return;
        }
        //calculate density
        double densityW = u/w;
        double densityH = r/h;

        Intent intent = new Intent(this, SampleResultActivity.class);
        intent.putExtra(SampleResultActivity.DW, densityW);
        intent.putExtra(SampleResultActivity.DH, densityH);
        startActivity(intent);
    }

    public void help(View view) {
        Intent intent = new Intent(this, HelpActivity.class);
        intent.putExtra(SavingActivity.ACTIVITY, SavingActivity.SAMPLE_KNITTING);
        startActivity(intent);
    }
}
