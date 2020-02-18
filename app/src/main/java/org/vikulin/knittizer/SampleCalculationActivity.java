package org.vikulin.knittizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SampleCalculationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_calculation);
        getSupportActionBar().setTitle(R.string.sample_calculate_menu);
    }

    public void calculate(View view) {
        EditText editW = findViewById(R.id.editW);
        EditText editH = findViewById(R.id.editH);
        EditText editU = findViewById(R.id.editU);
        EditText editR = findViewById(R.id.editR);
        if(editW.length()==0){
            editW.setError("Веедите число");
            return;
        }
        if(editH.length()==0){
            editH.setError("Веедите число");
            return;
        }
        if(editU.length()==0){
            editU.setError("Веедите число");
            return;
        }
        if(editR.length()==0){
            editR.setError("Веедите число");
            return;
        }
        int u = Integer.parseInt(editU.getText().toString());
        double w = Double.parseDouble(editW.getText().toString());
        int r = Integer.parseInt(editR.getText().toString());
        double h = Double.parseDouble(editH.getText().toString());
        if(u==0){
            editU.setError("Веедите число > 0");
            return;
        }
        if(w==0d){
            editW.setError("Веедите число > 0");
            return;
        }
        if(r==0){
            editR.setError("Веедите число > 0");
            return;
        }
        if(h==0d){
            editH.setError("Веедите число > 0");
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
}
