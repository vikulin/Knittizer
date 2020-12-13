package org.vikulin.knittizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    public static String help1 = "help1";
    public static String list1 = "list1";
    public static String help2 = "help2";
    public static String list2 = "list2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String help1 = extras.getString(ViewActivity.help1,"");
            String list1 = extras.getString(ViewActivity.list1,"");
            String help2 = extras.getString(ViewActivity.help2,"");
            String list2 = extras.getString(ViewActivity.list2,"");
            TextView help1Text = findViewById(R.id.help1);
            TextView list1Text = findViewById(R.id.list1);
            TextView help2Text = findViewById(R.id.help2);
            TextView list2Text = findViewById(R.id.list2);
            help1Text.setText(help1);
            list1Text.setText(list1);
            help2Text.setText(help2);
            list2Text.setText(list2);
        }
    }
}