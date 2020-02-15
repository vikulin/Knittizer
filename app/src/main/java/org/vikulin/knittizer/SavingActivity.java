package org.vikulin.knittizer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class SavingActivity extends Activity {

    public static final String RES = "result";
    public static final String ACTIVITY = "activity";
    public static final String PARTIAL_KNITTING = "partial_knitting";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ArrayList<String> result = extras.getStringArrayList(RES);
        } else {
            finish();
        }
    }

    public void onSave(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ArrayList<String> result = extras.getStringArrayList(RES);
            String activity = extras.getString(ACTIVITY);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
            EditText savedName = findViewById(R.id.saveName);
            if(savedName.length()==0){
                savedName.setError("Введите имя");
                return;
            }
            Set<String> set = preferences.getStringSet(savedName.getText().toString(), new LinkedHashSet());
            set.add(result.toString());
            preferences.edit().putStringSet(savedName.getText().toString(), set).apply();
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        } else {
            finish();
        }
    }

    public void onCancel(View view) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
