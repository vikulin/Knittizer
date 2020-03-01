package org.vikulin.knittizer.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.vikulin.knittizer.R;
import org.vikulin.knittizer.model.PartialKnittingResult;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.vikulin.knittizer.SavingActivity.DOUBLE_KNITTING;
import static org.vikulin.knittizer.SavingActivity.ONE_SIDE_KNITTING;
import static org.vikulin.knittizer.SavingActivity.PARTIAL_KNITTING;
import static org.vikulin.knittizer.SavingActivity.SAMPLE_KNITTING;
import static org.vikulin.knittizer.SavingActivity.TWO_SIDE_KNITTING;

public class SavedResultExpandableListAdapter extends BaseExpandableListAdapter {

    private Map<String, ?> map;
    private List<String> keys;
    private final Context context;
    Gson gson = new Gson();
    Type listType = new TypeToken<ArrayList<String>>(){}.getType();

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
        String savedResults = (String)map.get(this.keys.get(groupPosition));
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
        GroupHolder holder = null;
        String r = this.keys.get(position);
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.list_result, parent, false);
            holder = new GroupHolder();
            holder.text = row.findViewById(R.id.text);
            row.setTag(holder);
        } else {
            holder = (GroupHolder)row.getTag();
        }
        holder.text.setText(r.toString());
        return row;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final List<String> row = gson.fromJson((String)getChild(groupPosition, childPosition), listType);
        int activity = Integer.parseInt(row.get(0));
        ChildHolder holder;
        if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.list_rows_saved_result_pk, parent, false);
                Button deleteButton = convertView.findViewById(R.id.deleteButton);
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        new AlertDialog.Builder(context)
                                .setTitle(context.getResources().getString(R.string.dialog_title))
                                .setMessage(context.getResources().getString(R.string.confirm_delete))
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(((Activity) context).getBaseContext());
                                        String r = SavedResultExpandableListAdapter.this.keys.get(groupPosition);
                                        preferences.edit().remove(r).commit();
                                        map = preferences.getAll();
                                        SavedResultExpandableListAdapter.this.keys = new ArrayList<>(map.keySet());
                                        SavedResultExpandableListAdapter.this.notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton(android.R.string.no, null).show();
                    }
                });
                holder = new ChildHolder();
                holder.title = convertView.findViewById(R.id.title);
                holder.help1 = convertView.findViewById(R.id.help1);
                holder.list1 = convertView.findViewById(R.id.list1);
                holder.help2 = convertView.findViewById(R.id.help2);
                holder.list2 = convertView.findViewById(R.id.list2);
                convertView.setTag(holder);
            } else {
                holder = (ChildHolder) convertView.getTag();
            }
            switch (activity) {
                case ONE_SIDE_KNITTING:
                    holder.title.setText(R.string.one_side_menu);
                    holder.list1.setText(TextUtils.join(", ", row.subList(1, row.size())));
                    holder.help1.setVisibility(View.GONE);
                    holder.help2.setVisibility(View.GONE);
                    break;
                case TWO_SIDE_KNITTING:
                    holder.title.setText(R.string.two_side_menu);
                    holder.list1.setText(TextUtils.join(", ", row.subList(1, row.size())));
                    holder.help1.setVisibility(View.GONE);
                    holder.help2.setVisibility(View.GONE);
                    break;
                case DOUBLE_KNITTING:
                    holder.title.setText(R.string.double_side);
                    holder.list1.setText(TextUtils.join(", ", row.subList(1, row.size())));
                    holder.help1.setVisibility(View.GONE);
                    holder.help2.setVisibility(View.GONE);
                    break;
                case PARTIAL_KNITTING:
                    holder.title.setText(R.string.partial_knitting);
                    String r = row.get(1);
                    Gson gson = new Gson();
                    PartialKnittingResult pkResult = gson.fromJson(r, PartialKnittingResult.class);
                    holder.list1.setText(TextUtils.join(", ", pkResult.getPartialKnittingStitchesList()));
                    holder.list2.setText(TextUtils.join(", ", pkResult.getPartialKnittingRowsList()));
                    holder.help1.setVisibility(View.VISIBLE);
                    holder.help2.setVisibility(View.VISIBLE);
                    break;
                case SAMPLE_KNITTING:
                    holder.title.setText(R.string.sample_calculate_menu);
                    holder.list1.setText(TextUtils.join(", ", row.subList(1, row.size())));
                    holder.help1.setVisibility(View.GONE);
                    holder.help2.setVisibility(View.GONE);
                    break;
                // You can have any number of case statements.
                default:
                    // Statements
            }
            return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    static class GroupHolder {
        TextView text;
    }

    static class ChildHolder {
        TextView title;
        TextView help1;
        TextView list1;
        TextView help2;
        TextView list2;
    }
}
