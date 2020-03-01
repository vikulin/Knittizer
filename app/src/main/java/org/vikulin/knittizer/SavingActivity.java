package org.vikulin.knittizer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class SavingActivity extends Activity {

    public static final String RES = "result";
    public static final String ACTIVITY = "activity";
    public static final int ONE_SIDE_KNITTING = 1;
    public static final int TWO_SIDE_KNITTING = 2;
    public static final int DOUBLE_KNITTING = 3;
    public static final int PARTIAL_KNITTING = 4;
    public static final int SAMPLE_KNITTING = 5;

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
            int activity = extras.getInt(ACTIVITY);
            result.add(0, activity+"");//getResources().getString(R.string.one_side_menu)
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
            EditText savedName = findViewById(R.id.saveName);
            if(savedName.length()==0){
                savedName.setError(getResources().getString(R.string.empty_name_error));
                return;
            }
            String value = preferences.getString(savedName.getText().toString(), null);
            if(value!=null){
                savedName.setError(getResources().getString(R.string.existing_name_error));
                return;
            }
            Gson gson = new Gson();
            preferences.edit().putString(savedName.getText().toString(), gson.toJson(result)).apply();
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
