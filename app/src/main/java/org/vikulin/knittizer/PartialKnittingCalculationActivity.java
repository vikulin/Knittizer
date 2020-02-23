package org.vikulin.knittizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.vikulin.knittizer.model.PartialKnittingResult;

public class PartialKnittingCalculationActivity extends AlertActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partial_knitting_calculation);
        getSupportActionBar().setTitle(R.string.partial_knitting);
    }

    public void calculate(View view) {
        EditText rowsEdit = findViewById(R.id.editRows);
        EditText wEdit = findViewById(R.id.editW);
        EditText rEdit = findViewById(R.id.editH);
        if(wEdit.length()==0){
            wEdit.setError(getResources().getString(R.string.empty_value_error));
            return;
        }
        if(rowsEdit.length()==0){
            rowsEdit.setError(getResources().getString(R.string.empty_value_error));
            return;
        }
        int u = Integer.parseInt(wEdit.getText().toString());
        if(u==0){
            wEdit.setError("Веедите число > 0");
            return;
        }
        int rows = Integer.parseInt(rowsEdit.getText().toString());
        if(rows==0){
            rowsEdit.setError(getResources().getString(R.string.zero_value_error));
            return;
        }
        int phases = rows/2-((rows+1)%2);
        if(phases==0){
            showAlertDialog(getResources().getString(R.string.error),getResources().getString(R.string.division_by_zero_error));
            return;
        }
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
        intent.putExtra(PartialKnittingResultActivity.ROWS, rows);
        if(rEdit.length()>0) {
            int un = Integer.parseInt(rEdit.getText().toString());
            intent.putExtra(PartialKnittingResultActivity.UN, un);
        }
        startActivity(intent);
    }

    public void help(View view) {
        Intent intent = new Intent(this, HelpActivity.class);
        intent.putExtra(SavingActivity.ACTIVITY, SavingActivity.PARTIAL_KNITTING);
        startActivity(intent);
    }
}
