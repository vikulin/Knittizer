package org.vikulin.knittizer;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import org.vikulin.knittizer.model.PartialKnittingBaseData;

public class PartialKnittingCalculationActivity extends AlertActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partial_knitting_calculation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.partial_knitting);
        final EditText rowNEdit = findViewById(R.id.editRowN);
        final EditText rowKEdit = findViewById(R.id.editRowK);
        final EditText rowsEdit = findViewById(R.id.editRows);
        rowNEdit.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(final Editable editable) {
                if(editable.length()>0){
                    if(rowKEdit.length()>0){
                        int r1 = Integer.parseInt(rowKEdit.getText().toString());
                        int r2 = Integer.parseInt(editable.toString());
                        int rows = Math.abs(r1-r2);
                        rowsEdit.setText(rows+"");
                    }
                }
            }
        });
        rowKEdit.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(final Editable editable) {
                if(editable.length()>0){
                    if(rowNEdit.length()>0){
                        int r1 = Integer.parseInt(rowNEdit.getText().toString());
                        int r2 = Integer.parseInt(editable.toString());
                        int rows = Math.abs(r1-r2);
                        rowsEdit.setText(rows+"");
                    }
                }
            }
        });
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
        EditText rowNEdit = findViewById(R.id.editRowN);
        EditText rowKEdit = findViewById(R.id.editRowK);
        if(!(rowNEdit.length()>0 && rowKEdit.length()>0) && rowsEdit.length()==0){
            if(rowNEdit.length()==0){
                rowNEdit.setError(getResources().getString(R.string.empty_value_error));
                return;
            }
            if(rowKEdit.length()==0){
                rowKEdit.setError(getResources().getString(R.string.empty_value_error));
                return;
            }
        }
        if(rowsEdit.length()==0){
            rowsEdit.setError(getResources().getString(R.string.empty_value_error));
            return;
        }
        int startFromRow = 1;
        if(rowNEdit.length()>0 || rowKEdit.length()>0){
            int r1 = Integer.parseInt(rowNEdit.getText().toString());
            int r2 = Integer.parseInt(rowKEdit.getText().toString());
            rows = Math.abs(r1-r2);
            if(r1==r2){
                rowNEdit.setError(getResources().getString(R.string.equal_rows_error));
                rowKEdit.setError(getResources().getString(R.string.equal_rows_error));
                return;
            }
            startFromRow = Math.min(r1,r2);
        } else {
            rows = Integer.parseInt(rowsEdit.getText().toString());
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

        PartialKnittingBaseData result = new PartialKnittingBaseData();
        result.setBase(base);
        result.setPhases(phases);
        result.setFractionalPhases(fractionalPhase);

        Intent intent = new Intent(this, PartialKnittingResultActivity.class);
        intent.putExtra(PartialKnittingResultActivity.RES, result);
        intent.putExtra(PartialKnittingResultActivity.START_FROM_ROW, startFromRow);
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
