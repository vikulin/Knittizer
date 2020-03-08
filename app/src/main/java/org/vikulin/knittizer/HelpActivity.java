package org.vikulin.knittizer;

import android.os.Bundle;
import android.util.Base64;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;

public class HelpActivity extends AlertActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            LinearLayout ll = findViewById(R.id.content);
            WebView help = new WebView(getApplicationContext());
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            help.setLayoutParams(lp);
            ll.addView(help);
            int activity = extras.getInt(SavingActivity.ACTIVITY);
            String base64 = null;
            try {
                switch (activity) {
                    case SavingActivity.ONE_SIDE_KNITTING:
                        setTitle(getResources().getString(R.string.help)+": "+getResources().getString(R.string.one_side_menu).toLowerCase());
                        base64 = Base64.encodeToString(readResource(R.raw.one_side_knitting), Base64.DEFAULT);
                        break;
                    case SavingActivity.TWO_SIDE_KNITTING:
                        setTitle(getResources().getString(R.string.help)+": "+getResources().getString(R.string.double_knitting).toLowerCase());
                        base64 = Base64.encodeToString(readResource(R.raw.two_side_fason_knitting), Base64.DEFAULT);
                        break;
                    case SavingActivity.DOUBLE_KNITTING:
                        setTitle(getResources().getString(R.string.help)+": "+getResources().getString(R.string.two_side_menu).toLowerCase());
                        base64 = Base64.encodeToString(readResource(R.raw.double_knitting), Base64.DEFAULT);
                        break;
                    case SavingActivity.PARTIAL_KNITTING:
                        setTitle(getResources().getString(R.string.help)+": "+getResources().getString(R.string.partial_knitting).toLowerCase());
                        base64 = Base64.encodeToString(readResource(R.raw.partial_knitting), Base64.DEFAULT);
                        break;
                    case SavingActivity.SAMPLE_KNITTING:
                        setTitle(getResources().getString(R.string.help)+": "+getResources().getString(R.string.sample_calculate_menu).toLowerCase());
                        base64 = Base64.encodeToString(readResource(R.raw.sample_html), Base64.DEFAULT);
                        break;
                    // You can have any number of case statements.
                    default:
                        // Statements
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            help.loadData(base64, "text/html; charset=utf-8", "base64");
        } else {
            finish();
        }
    }

    private byte[] readResource(int id) throws IOException {
        InputStream is = getResources().openRawResource(id);
        byte[] b = new byte[is.available()];
        is.read(b);
        return b;
    }
}
