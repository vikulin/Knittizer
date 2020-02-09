package org.vikulin.knittizer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ExpandableListView;

import org.vikulin.knittizer.adapter.PartialKnittingExpandableListAdapter;
import org.vikulin.knittizer.model.PartialKnittingResult;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class PartialKnittingResultActivity extends AppCompatActivity {

    public static final String RES = "result";
    public static final String UK  = "uk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partial_knitting_result);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ExpandableListView resultListView = findViewById(R.id.resultList);
            PartialKnittingResult result = (PartialKnittingResult) extras.getSerializable(RES);
            int uk = extras.getInt(UK, 0);
            int base = result.getBase();
            int phases = result.getPhases();
            int fractionalPhases = result.getFractionalPhases();
            List<Integer> resultList = new ArrayList<>();
            for(int i=1;i<=fractionalPhases;i++){
                resultList.add(base+1);
            }
            for(int i=1;i<=phases-fractionalPhases;i++){
                resultList.add(base);
            }
            balancingProcedure(uk, resultList, 1);
            List<String> resultString = zeroPhasesProcedure(resultList);
            PartialKnittingExpandableListAdapter adapter = new PartialKnittingExpandableListAdapter(this, resultString, resultList.size());
            resultListView.setAdapter(adapter);
            resultListView.expandGroup(0);
        } else {
            finish();
        }
    }

    private List<String> zeroPhasesProcedure(List<Integer> resultList) {
        //calculate number of zero elements;
        int numberOfZero = findNumberOfElements(resultList,0);
        if(numberOfZero==0){
            List<String> result = new ArrayList<>();
            for(int e:resultList){
                result.add(String.valueOf(e));
            }
            //finish Partial Knitting
            result.add(" ЧВ");
            return result;
        }
        int greatestDivisor = findGreatestDivisor(numberOfZero);
        int r;
        if(greatestDivisor==0){
            //found prime number
            greatestDivisor = (numberOfZero+1)/2;
            r = (numberOfZero+1)/greatestDivisor;
        } else {
            r = numberOfZero/greatestDivisor;
        }
        //reverse list iteration
        int tmp = r;
        for(int i = resultList.size()-1;i>1;i--){
            if(resultList.get(i)>1){
                resultList.set(i,resultList.get(i)-1);
                tmp--;
                if(tmp==0){
                    break;
                }
            }
        }
        //transform List<Integer> into List<String>
        //delete zero elements
        Iterator<Integer> it = resultList.iterator();
        while(it.hasNext()){
            if(it.next()==0){
                it.remove();
            }
        }
        List<String> result = new ArrayList<>();
        for(int e:resultList){
            result.add(String.valueOf(e));
        }
        //finish Partial Knitting
        result.add(" ЧВ");
        //construct additional string
        String additionalString = r+"x1*"+greatestDivisor;
        result.add(additionalString);
        return result;
    }

    private static int findGreatestDivisor(int num){
        int o = num % 2;
        if(o==0){
            return num/2;
        }
        int c = (int)num/2;
        for(int i=c-1;i>1;i--){
            if(num % i == 0){
                return num/i;
            }
        }
        //the number is prime
        return 0;
    }

    private static int findNumberOfElements(List<Integer> resultList, int e){
        int i=0;
        for(int a:resultList){
            if(a==e){
                i++;
            }
        }
        return i;
    }

    private void balancingProcedure(int uk, List<Integer> resultList, int subIndex) {
        if(uk>resultList.get(0)){
            int delta = uk-resultList.get(0);
            List<Integer> subList =  resultList.subList(subIndex, resultList.size());
            int sum = 0;
            for(int i:subList){
                if(i>0) {
                    sum += i;
                }
            }
            delta = Math.min(sum, delta);
            if(delta<=subList.size()) {
                int i=0;
                int counter = 0;
                while(counter<delta) {
                    int index = resultList.size() - 1 - i;
                    if (resultList.get(index) > 0) {
                        resultList.set(index, resultList.get(index) - 1);
                        resultList.set(0, resultList.get(0) + 1);
                        counter++;
                    }
                    i++;
                }
            } else {
                int i=0;
                int counter = 0;
                while(counter<subList.size()) {
                    int index = resultList.size() - 1 - i;
                    if (resultList.get(index) > 0) {
                        resultList.set(index, resultList.get(index) - 1);
                        resultList.set(0, resultList.get(0) + 1);
                        counter++;
                    }
                    i++;
                }
            }
            balancingProcedure((int)uk/2, resultList.subList(1, resultList.size()), 1);
            //balancingProcedure((int)uk/2, resultList.subList(2, resultList.size()), 2);
        }
    }

}
