package org.vikulin.knittizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.vikulin.knittizer.model.TwoSidesResult;

import java.math.BigDecimal;
import java.util.ArrayList;

public class OneSideCalculationActivity extends AlertActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_side_calculation);
        getSupportActionBar().setTitle(R.string.one_side_menu);
    }

    public void calculate(View view) {
        EditText rowsEdit = findViewById(R.id.editRows);
        EditText uNEdit = findViewById(R.id.editW);
        EditText uKEdit = findViewById(R.id.editH);
        if(uNEdit.length()==0){
            uNEdit.setError(getResources().getString(R.string.empty_value_error));
            return;
        }
        if(uKEdit.length()==0){
            uKEdit.setError(getResources().getString(R.string.empty_value_error));
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
        if(rowNEdit.length()==0 && rowKEdit.length()==0 && rowsEdit.length()==0){
            rowsEdit.setError(getResources().getString(R.string.empty_value_error));
            return;
        }
        int rows = 0;
        int startFromRow = 0;
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
        int ur = Integer.parseInt(uNEdit.getText().toString())-Integer.parseInt(uKEdit.getText().toString());
        boolean isStartStitchLessEndStitch = ur<0;
        //if(ur<0){
        //Прибавки
        //} else {
        //Убавки
        //}
        int u = (int)Math.abs(ur);
        if(u==0){
            showAlertDialog(getResources().getString(R.string.error),getResources().getString(R.string.division_by_zero_error));
            return;
        }
        ArrayList<TwoSidesResult> resultList = new ArrayList<>();
        if(rows % u==0){
            //String result = "Убавлять в каждом "+(rows / u)+" ряду";

            TwoSidesResult object = new TwoSidesResult();
            object.setFirstNumber(u);
            object.setFirstRowPeriod(rows / u);
            object.setSecondNumber(u);
            object.setSecondRowPeriod(rows / u);
            object.setStartStitchLessEndStitch(isStartStitchLessEndStitch);
            resultList.add(object);
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra(ResultActivity.RES, resultList);
            intent.putExtra(SavingActivity.ACTIVITY, SavingActivity.ONE_SIDE_KNITTING);
            intent.putExtra(ResultActivity.NUMBER_OF_ROW_SERIES, 1);
            startActivity(intent);
            return;
        }

        for(int a=3;a<rows;a++){
            for(int b=2;b<rows;b++){
                if(a==b){
                    continue;
                }
                double r = (a*rows-u*a*b)/(a-b);
                BigDecimal bigDecimal = new BigDecimal(String.valueOf(r));
                int intValue = bigDecimal.intValue();
                BigDecimal o = bigDecimal.subtract(new BigDecimal(intValue));
                //System.out.println("Double Number: " + bigDecimal.toPlainString());
                //System.out.println("Integer Part: " + intValue);
                //System.out.println("Decimal Part: " + o.toPlainString());
                if(intValue<rows && intValue>0 && a<=intValue/2 && o.compareTo(new BigDecimal(0.01))<0 && (intValue % a)==0 && ((rows-intValue)%b)==0 && (rows-intValue)/b>1){
                    TwoSidesResult object = new TwoSidesResult();
                    if(a<b) {
                        object.setFirstNumber(intValue / a);
                        object.setFirstRowPeriod(a);
                        object.setSecondNumber((rows - intValue) / b);
                        object.setSecondRowPeriod(b);
                        object.setStartStitchLessEndStitch(isStartStitchLessEndStitch);
                    } else {
                        object.setFirstNumber((rows - intValue) / b);
                        object.setFirstRowPeriod(b);
                        object.setSecondNumber(intValue / a);
                        object.setSecondRowPeriod(a);
                        object.setStartStitchLessEndStitch(isStartStitchLessEndStitch);
                    }
                    resultList.add(object);
                }
            }
        }
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(ResultActivity.RES, resultList);
        intent.putExtra(ResultActivity.START_FROM_ROW, startFromRow);
        intent.putExtra(SavingActivity.ACTIVITY, SavingActivity.ONE_SIDE_KNITTING);
        intent.putExtra(ResultActivity.NUMBER_OF_ROW_SERIES, 1);
        startActivity(intent);
    }

    public void help(View view) {
        Intent intent = new Intent(this, HelpActivity.class);
        intent.putExtra(SavingActivity.ACTIVITY, SavingActivity.ONE_SIDE_KNITTING);
        startActivity(intent);
    }
}
