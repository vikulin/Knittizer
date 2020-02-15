package org.vikulin.knittizer.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.vikulin.knittizer.PartialKnittingResultActivity;
import org.vikulin.knittizer.R;
import org.vikulin.knittizer.ResultActivity;
import org.vikulin.knittizer.SavingActivity;
import org.vikulin.knittizer.model.TwoSidesResult;

import java.util.ArrayList;
import java.util.List;

public class ResultExpandableListAdapter extends BaseExpandableListAdapter {

    private final List<TwoSidesResult> list;
    private final Context context;
    private final int startFromRow;
    private final int numberOfRowSeries;

    public ResultExpandableListAdapter(final Context context, List<TwoSidesResult> list, int startFromRow, int numberOfRowSeries){
        this.context = context;
        this.list = list;
        this.startFromRow = startFromRow;
        this.numberOfRowSeries = numberOfRowSeries;
    }

    @Override
    public int getGroupCount() {
        return this.list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.numberOfRowSeries;
    }

    @Override
    public Object getGroup(int i) {
        return this.list.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if(childPosition==0) {
            List<Integer> rows = new ArrayList<>();
            TwoSidesResult r = this.list.get(groupPosition);
            int fr = r.getFirstRowPeriod();
            int fn = r.getFirstNumber();
            int rowNumber = startFromRow;
            for (int i = 1; i <= fn; i++) {
                rowNumber = rowNumber + fr;
                rows.add(rowNumber);
            }
            return rows;
        } else {
            List<Integer> rows = new ArrayList<>();
            TwoSidesResult r = this.list.get(groupPosition);
            int fr = r.getSecondRowPeriod();
            int fn = r.getSecondNumber();
            int rowNumber = startFromRow + r.getFirstRowPeriod()*r.getFirstNumber();
            for (int i = 1; i <= fn; i++) {
                rowNumber = rowNumber + fr;
                rows.add(rowNumber);
            }
            return rows;
        }
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 100*childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int position, boolean b, View row, ViewGroup parent) {
        ResultHolder holder = null;
        TwoSidesResult r = this.list.get(position);
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.list_result, parent, false);
            holder = new ResultHolder();
            holder.text = row.findViewById(R.id.text);
            row.setTag(holder);
        } else {
            holder = (ResultHolder)row.getTag();
        }
        holder.text.setText(r.toString());
        return row;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ResultHolder holder;
        final List<Integer> rows = (List<Integer>) getChild(groupPosition, childPosition);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_rows_result, parent, false);
            Button saveButton = (Button) convertView.findViewById(R.id.saveButton);

            saveButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent((ResultActivity)context, SavingActivity.class);
                    ArrayList<String> list = new ArrayList<String>();
                    list.add(rows.toString());
                    intent.putStringArrayListExtra(SavingActivity.RES, list);
                    ((ResultActivity)context).startActivityForResult(intent, PartialKnittingResultActivity.SAVE);
                }
            });
            holder = new ResultHolder();
            holder.text = convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (ResultHolder) convertView.getTag();
        }


        holder.text.setText(rows.toString());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    static class ResultHolder
    {
        TextView text;
    }
}
