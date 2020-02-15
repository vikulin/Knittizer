package org.vikulin.knittizer.adapter;

import android.widget.BaseExpandableListAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.vikulin.knittizer.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SavedResultExpandableListAdapter extends BaseExpandableListAdapter {

    private final Map<String, ?> map;
    private final List<String> keys;
    private final Context context;

    public SavedResultExpandableListAdapter(final Context context, Map<String, ?> map){
        this.context = context;
        this.map = map;
        this.keys = new ArrayList<>(map.keySet());
    }

    @Override
    public int getGroupCount() {
        return this.keys.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return ((Set<String>)map.get(this.keys.get(i))).size();
    }

    @Override
    public Object getGroup(int i) {
        return this.keys.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        Set<String> savedResults = (Set<String>) map.get(this.keys.get(groupPosition));
        if(savedResults!=null){
            return new ArrayList<>(savedResults).get(childPosition);
        } else {
            return "";
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
        String r = this.keys.get(position);
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_rows_saved_result, parent, false);
            holder = new ResultHolder();
            holder.text = convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (ResultHolder) convertView.getTag();
        }
        String row = (String)getChild(groupPosition, childPosition);
        holder.text.setText(row);
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
