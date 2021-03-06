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

import org.vikulin.knittizer.R;
import org.vikulin.knittizer.SavingActivity;

import java.util.ArrayList;
import java.util.List;

public class StringResultExpandableListAdapter extends BaseExpandableListAdapter {

    private final List<String> list;
    private final Context context;
    private final String groupTitle;
    private final int activity;

    public StringResultExpandableListAdapter(final Context context, List<String> list, String groupTitle, final int activity){
        this.context = context;
        this.list = list;
        this.groupTitle = groupTitle;
        this.activity = activity;
    }

    @Override
    public int getGroupCount() {
        return 1;
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return this.list.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.list;
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
        ResultHolder holder;
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
        holder.text.setText(groupTitle);
        return row;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ResultHolder holder;
        final ArrayList<String> rows = (ArrayList<String>) getChild(groupPosition, childPosition);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_rows_result, parent, false);
            Button saveButton = convertView.findViewById(R.id.saveButton);
            saveButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(context, SavingActivity.class);
                    intent.putStringArrayListExtra(SavingActivity.RES, rows);
                    intent.putExtra(SavingActivity.ACTIVITY, activity);
                    ((Activity)context).startActivityForResult(intent, activity);
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
