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

        //calculate density
        double densityW = Integer.parseInt(editU.getText().toString())/Double.parseDouble(editW.getText().toString());
        double densityH = Integer.parseInt(editH.getText().toString())/Double.parseDouble(editR.getText().toString());

        Intent intent = new Intent(this, SampleResultActivity.class);
        intent.putExtra(SampleResultActivity.DW, densityW);
        intent.putExtra(SampleResultActivity.DH, densityH);
        startActivity(intent);
    }
}
