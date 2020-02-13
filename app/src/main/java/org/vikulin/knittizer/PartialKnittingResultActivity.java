package org.vikulin.knittizer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ExpandableListView;

import org.vikulin.knittizer.adapter.PartialKnittingExpandableListAdapter;
import org.vikulin.knittizer.model.PartialKnittingResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class PartialKnittingResultActivity extends AppCompatActivity {

    public static final String RES = "result";
    public static final String UN = "un";
    public static final String U = "u";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partial_knitting_result);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ExpandableListView resultListView = findViewById(R.id.resultList);
            PartialKnittingResult result = (PartialKnittingResult) extras.getSerializable(RES);
            int un = extras.getInt(UN, 0);
            int u = extras.getInt(U, 0);
            int base = result.getBase();
            int phases = result.getPhases();

            List<String> resultString = new ArrayList<>();
            List<Integer> resultList = new ArrayList<>();
            if(un>0){

                //int u = 34;
                int d = un;
                int phase = phases;

                resultList.addAll(0, createHeadIntList(d));
                int deltaU = 0;
                for(int i=resultList.get(resultList.size()-1)-1;i>0;i--) {
                    int sum = sum(resultList);
                    int tmp = u-sum-2;

                    if(tmp<0){
                        break;
                    }
                    deltaU = tmp;
                    resultList.add(i);
                }
                System.out.println(resultList.toString());

                //-2 give us 2 free stitches as result 2*(ph-list size)/deltaU = ph-list size
                System.out.println(deltaU);
                Set<Integer> set = run(deltaU, 4);
                System.out.println(set.toString());
                resultList.addAll(set);
                while(phase-resultList.size()>3){
                    int index = getGreaterMinimal(1, resultList);
                    if(index >= 0){
                        int e = resultList.get(index);
                        Set<Integer> add = run(e,4);
                        resultList.remove(index);
                        resultList.addAll(index, add);
                    }
                };
                Collections.sort(resultList, Collections.reverseOrder());
                System.out.println(resultList.toString()+" ЧВ");
                System.out.println("2x1*"+(phase-resultList.size()));
                System.out.println(2*(phase-resultList.size())/(u-sum(resultList)));
                resultString.add(resultList.toString());
                resultString.add("ЧВ");
                resultString.add("2x1*"+(phase-resultList.size()));
            } else {
                int fractionalPhases = result.getFractionalPhases();
                for(int i=1;i<=fractionalPhases;i++){
                    resultList.add(base+1);
                }
                for(int i=1;i<=phases-fractionalPhases;i++){
                    resultList.add(base);
                }
            }
            //balancingProcedure(uk, resultList, 1);
            //List<String> resultString = zeroPhasesProcedure(resultList);


            PartialKnittingExpandableListAdapter adapter = new PartialKnittingExpandableListAdapter(this, resultString, resultList.size());
            resultListView.setAdapter(adapter);
            resultListView.expandGroup(0);
        } else {
            finish();
        }
    }

    private static int getGreaterMinimal(int n, List<Integer> resultList){
        int i = -1;
        int r = Integer.MAX_VALUE;
        int index = -1;
        for(int e:resultList){
            i++;
            if(e>n && e<r){
                r = e;
                index = i;
            }
        }
        return index;
    }

    private static List<Integer> createHeadIntList(int a){
        List<Integer> list = new ArrayList<Integer>();
        list.add(a);
        int i=2;
        int r = a/2;
        while(r>=3) {
            list.add(r);
            i=2*i;
            r = a/i;
        };
        return list;
    }

    private static int sum(List<Integer> list){
        int sum = 0;
        Iterator<Integer> it = list.iterator();
        while(it.hasNext()){
            sum = sum + it.next();
        }
        return sum;
    }

    static void printSplit(Integer[] arr, int l){
        for (int i=0; i<l; i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }


    //find partitions
    static int next(Integer[] arr, int l){
        int i = l-1;
        int sum=0;

        do {
            sum += arr[i--];
        }  while (i>0 && arr[i-1] <= arr[i]);

        arr[i]++;
        l=i+sum;

        for (int j=i+1; j<l; j++)
            arr[j] = 1;

        return l;
    }

    static Set<Integer> run(int n, int max){
        if(n<=0){
            return new HashSet();
        }
        int l=n;
        Integer[] arr = new Integer[n];
        Set<Integer> tmp = new HashSet<Integer>();
        for (int i=0; i<n; i++) arr[i] = 1;
        Set<Integer> converter = new HashSet<Integer>();
        converter.addAll(Arrays.asList(arr));
        if(tmp.size()<converter.size()) {
            tmp.clear();
            tmp.addAll(converter);
        }
        converter.clear();
        printSplit(arr, n);
        do {
            l=next(arr, l);
            converter.addAll(Arrays.asList(arr));
            if(tmp.size()<converter.size() && allLessThen(converter, max)) {
                tmp.clear();
                tmp.addAll(converter);
            }
            converter.clear();
            printSplit(arr, l);

        } while (l>1);
        return tmp;
    }

    static boolean allLessThen(Set<Integer> set, int max) {
        for(int e:set) {
            if(e>max && max!=0) {
                return false;
            }
        }
        return true;
    }

}
