package org.vikulin.knittizer.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import org.vikulin.knittizer.R;
import java.util.List;

public class PartialKnittingExpandableListAdapter extends BaseExpandableListAdapter {

    private final List<String> list;
    private final Context context;
    private final int phaseNumber;

    public PartialKnittingExpandableListAdapter(final Context context, List<String> list, int phaseNumber){
        this.context = context;
        this.list = list;
        this.phaseNumber = phaseNumber;
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
        ResultHolder holder = null;
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
        holder.text.setText("Всего фаз "+phaseNumber);
        return row;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ResultHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_rows_result, parent, false);
            holder = new ResultHolder();
            holder.text = convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (ResultHolder) convertView.getTag();
        }
        List<String> rows = (List<String>) getChild(groupPosition, childPosition);

        holder.text.setText(TextUtils.join(", ", rows));
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
