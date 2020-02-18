package org.vikulin.knittizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.vikulin.knittizer.model.PartialKnittingResult;

public class PartialKnittingCalculationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partial_knitting_calculation);
        getSupportActionBar().setTitle(R.string.partial_knitting);
    }

    public void calculate(View view) {
        EditText rowsEdit = findViewById(R.id.editRows);
        EditText uEdit = findViewById(R.id.editW);
        EditText uNEdit = findViewById(R.id.editH);
        if(rowsEdit.length()==0){
            rowsEdit.setError("Веедите число");
            return;
        }
        if(uEdit.length()==0){
            uEdit.setError("Веедите число");
            return;
        }
        int rows = Integer.parseInt(rowsEdit.getText().toString());
        if(rows==0){
            rowsEdit.setError("Веедите число > 0");
            return;
        }
        int u = Integer.parseInt(uEdit.getText().toString());
        if(u==0){
            uEdit.setError("Веедите число > 0");
            return;
        }
        int phases = rows/2-((rows+1)%2);
        Double fullNumber = new Double(u)/new Double(phases);
        int base = fullNumber.intValue();
        double fractional = fullNumber - base;
        double d = phases*fractional;
        int fractionalPhase = (int)Math.round(d);

        PartialKnittingResult result = new PartialKnittingResult();
        result.setBase(base);
        result.setPhases(phases);
        result.setFractionalPhases(fractionalPhase);

        Intent intent = new Intent(this, PartialKnittingResultActivity.class);
        intent.putExtra(PartialKnittingResultActivity.RES, result);
        intent.putExtra(PartialKnittingResultActivity.U, u);
        if(uNEdit.length()>0) {
            int un = Integer.parseInt(uNEdit.getText().toString());
            intent.putExtra(PartialKnittingResultActivity.UN, un);
        }
        startActivity(intent);
    }
}
