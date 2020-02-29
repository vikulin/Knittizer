package org.vikulin.knittizer.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import org.vikulin.knittizer.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SavedResultExpandableListAdapter extends BaseExpandableListAdapter {

    private Map<String, ?> map;
    private List<String> keys;
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
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return this.keys.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String savedResults = map.get(this.keys.get(groupPosition)).toString();
        if(savedResults!=null){
            return savedResults;
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
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ResultHolder holder;
        final String row = (String)getChild(groupPosition, childPosition);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_rows_saved_result, parent, false);
            Button deleteButton = convertView.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    new AlertDialog.Builder(context)
                            .setTitle(context.getResources().getString(R.string.dialog_title))
                            .setMessage(context.getResources().getString(R.string.confirm_delete))
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(((Activity)context).getBaseContext());
                                    String r = SavedResultExpandableListAdapter.this.keys.get(groupPosition);
                                    preferences.edit().remove(r).commit();
                                    map = preferences.getAll();
                                    SavedResultExpandableListAdapter.this.keys = new ArrayList<>(map.keySet());
                                    SavedResultExpandableListAdapter.this.notifyDataSetChanged();
                                }})
                            .setNegativeButton(android.R.string.no, null).show();
                }
            });
            holder = new ResultHolder();
            holder.text = convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (ResultHolder) convertView.getTag();
        }

        holder.text.setText(row);
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
