package org.vikulin.knittizer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ExpandableListView;
import org.vikulin.knittizer.adapter.SavedResultExpandableListAdapter;

import java.util.Map;

public class SavedListActivity extends AlertActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.saved_calculations);
        setAdapter();
    }

    public void setAdapter(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
        Map<String, ?> allSavedData = preferences.getAll();
        SavedResultExpandableListAdapter adapter = new SavedResultExpandableListAdapter(this, allSavedData);
        ExpandableListView resultListView = findViewById(R.id.resultList);
        resultListView.setAdapter(adapter);
    }
}
