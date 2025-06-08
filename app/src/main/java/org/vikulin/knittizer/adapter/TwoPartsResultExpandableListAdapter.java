package org.vikulin.knittizer.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.vikulin.knittizer.R;
import org.vikulin.knittizer.ResultActivity;
import org.vikulin.knittizer.SavingActivity;
import org.vikulin.knittizer.model.TwoPartsResult;

import java.util.ArrayList;
import java.util.List;

public class TwoPartsResultExpandableListAdapter extends BaseExpandableListAdapter {

    private final List<TwoPartsResult> list;
    private final Context context;
    private final int startFromRow;
    private final int numberOfRowSeries;
    private final int activity;

    public TwoPartsResultExpandableListAdapter(final Context context, List<TwoPartsResult> list, int startFromRow, int numberOfRowSeries, int activity){
        this.context = context;
        this.list = list;
        this.startFromRow = startFromRow;
        this.numberOfRowSeries = numberOfRowSeries;
        this.activity = activity;
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
        TwoPartsResult r = this.list.get(groupPosition);
        return getRows(r, startFromRow);
    }

    public static Object getRows(TwoPartsResult r, int startFromRow){
        List<String> rows = new ArrayList<>();
        int fr = r.getFirstRowPeriod();
        int fn = r.getFirstNumber();
        int rowNumber = startFromRow;
        for (int i = 1; i <= fn; i++) {
            rowNumber = rowNumber + fr;
            rows.add(rowNumber+"("+r.getFirstStitchesNumber()+")");
        }
        if(!r.isPartEquals()) {
            fr = r.getSecondRowPeriod();
            fn = r.getSecondNumber();
            rowNumber = startFromRow + r.getFirstRowPeriod() * r.getFirstNumber();
            for (int i = 1; i <= fn; i++) {
                rowNumber = rowNumber + fr;
                rows.add(rowNumber + "(" + r.getSecondStitchesNumber()+")");
            }
        }
        return rows;
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
        TwoPartsResult r = this.list.get(position);
        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.list_result, parent, false);
            holder = new ResultHolder();
            holder.text = row.findViewById(R.id.text);
            row.setTag(holder);
        } else {
            holder = (ResultHolder)row.getTag();
        }
        holder.text.setText(r.toString(context));
        return row;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ResultHolder holder;
        final List<Integer> rows = (List<Integer>) getChild(groupPosition, childPosition);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_rows_result, parent, false);
            Button saveButton = convertView.findViewById(R.id.saveButton);
            saveButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(context, SavingActivity.class);
                    ArrayList<String> list = new ArrayList<>();
                    TwoPartsResult r = TwoPartsResultExpandableListAdapter.this.list.get(groupPosition);
                    r.setStartFromRow(startFromRow);
                    Gson gson = new Gson();
                    list.add(gson.toJson(r));
                    intent.putStringArrayListExtra(SavingActivity.RES, list);
                    intent.putExtra(SavingActivity.ACTIVITY, activity);
                    ((ResultActivity)context).startActivityForResult(intent, activity);
                }
            });
            holder = new ResultHolder();
            holder.text = convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (ResultHolder) convertView.getTag();
        }
        holder.text.setText(TextUtils.join(", ", rows));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    static class ResultHolder {
        TextView text;
    }
}
