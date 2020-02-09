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
    }

    public void calculate(View view) {
        EditText rowsEdit = findViewById(R.id.editRows);
        EditText uNEdit = findViewById(R.id.editUN);
        EditText uKEdit = findViewById(R.id.editUK);
        if(rowsEdit.length()==0){
            rowsEdit.setError("Веедите число");
            return;
        }
        if(uNEdit.length()==0){
            uNEdit.setError("Веедите число");
            return;
        }
        int rows = Integer.parseInt(rowsEdit.getText().toString());
        int u = Integer.parseInt(uNEdit.getText().toString());
        int phases = (int)rows/2;
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
        if(uKEdit.length()>0) {
            int uk = Integer.parseInt(uKEdit.getText().toString());
            intent.putExtra(PartialKnittingResultActivity.UK, uk);
        }
        startActivity(intent);
    }
}
