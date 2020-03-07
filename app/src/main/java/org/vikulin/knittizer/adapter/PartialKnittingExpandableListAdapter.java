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
import org.vikulin.knittizer.SavingActivity;
import org.vikulin.knittizer.model.PartialKnittingResult;

import java.util.ArrayList;

public class PartialKnittingExpandableListAdapter extends BaseExpandableListAdapter {

    private final PartialKnittingResult list;
    private final Context context;
    private final String groupTitle;
    public PartialKnittingExpandableListAdapter(final Context context, PartialKnittingResult list, String groupTitle){
        this.context = context;
        this.list = list;
        this.groupTitle = groupTitle;
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
        return this.groupTitle;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list;
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
        GroupHolder holder = null;
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.list_result, parent, false);
            holder = new GroupHolder();
            holder.text = row.findViewById(R.id.text);
            holder.group = row.findViewById(R.id.group);
            row.setTag(holder);
        } else {
            holder = (GroupHolder)row.getTag();
        }
        holder.text.setText(getGroup(position).toString());
        holder.group.setText(context.getResources().getString(R.string.partial_knitting));
        
        return row;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        PkResultHolder holder;
        final PartialKnittingResult result = (PartialKnittingResult) getChild(groupPosition, childPosition);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_rows_result_pk, parent, false);
            Button saveButton = convertView.findViewById(R.id.saveButton);
            saveButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(context, SavingActivity.class);
                    Gson gson = new Gson();
                    ArrayList<String> r = new ArrayList();
                    r.add(gson.toJson(result));
                    intent.putStringArrayListExtra(SavingActivity.RES, r);
                    intent.putExtra(SavingActivity.ACTIVITY, SavingActivity.PARTIAL_KNITTING);
                    ((Activity)context).startActivityForResult(intent, SavingActivity.PARTIAL_KNITTING);
                }
            });
            holder = new PkResultHolder();
            holder.pkStitchesList = convertView.findViewById(R.id.list1);
            holder.pkRows = convertView.findViewById(R.id.list2);
            convertView.setTag(holder);
        } else {
            holder = (PkResultHolder) convertView.getTag();
        }
        holder.pkStitchesList.setText(TextUtils.join(", ", result.getPartialKnittingStitchesList()));
        holder.pkRows.setText(TextUtils.join(", ", result.getPartialKnittingRowsList()));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    static class PkResultHolder {
        TextView pkStitchesList;
        TextView pkRows;
    }

    static class GroupHolder {
        TextView text;
        TextView group;
    }
}
