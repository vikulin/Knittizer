package org.vikulin.knittizer;

import android.os.Bundle;
import android.util.Base64;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import java.io.UnsupportedEncodingException;

public class HelpActivity extends AlertActivity {

    private final String sampleHtml =
            "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><html>\n" +
            "<font color=\"#000000\"><font size=\"4\" style=\"font-size: 16pt;\"><b><p style=\"text-indent: 20px;\">Подготовка образца</p></b></font></font></p>\n" +
            "<p style=\"margin-left: 0.12in; margin-bottom: 0.14in; line-height: 120%; text-indent: 20px;\">\n" +
            "<font size=\"2\" style=\"font-size: 14pt\">Для расчёта петельной пробы необходимо связать образец с определённым количеством петель и рядов.\n" +
            "Затем тщательно отпарить образец, и, дав ему остыть, измерить его линейкой по вертикали и горизонтали.\n" +
            "Полученные цифры измерений ввести в соответствующие поля программы вместе с цифрами петель и рядов: А и В.\n" +
            "Программа автоматически расчитает плотность образца, то есть количество петель и рядов в 1 см.</font></p>\n" +
            "</html>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            LinearLayout ll = findViewById(R.id.content);
            WebView help = new WebView(this);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            help.setLayoutParams(lp);
            ll.addView(help);
            int activity = extras.getInt(SavingActivity.ACTIVITY);
            switch (activity) {
                case SavingActivity.ONE_SIDE_KNITTING:
                    setTitle(getResources().getString(R.string.help)+": "+getResources().getString(R.string.one_side_menu).toLowerCase());
                    break;
                case SavingActivity.TWO_SIDE_KNITTING:
                    setTitle(getResources().getString(R.string.help)+": "+getResources().getString(R.string.two_side_menu).toLowerCase());
                    break;
                case SavingActivity.DOUBLE_KNITTING:
                    setTitle(getResources().getString(R.string.help)+": "+getResources().getString(R.string.double_side).toLowerCase());
                    break;
                case SavingActivity.PARTIAL_KNITTING:
                    setTitle(getResources().getString(R.string.help)+": "+getResources().getString(R.string.partial_knitting).toLowerCase());
                    break;
                case SavingActivity.SAMPLE_KNITTING:
                    setTitle(getResources().getString(R.string.help)+": "+getResources().getString(R.string.sample_calculate_menu).toLowerCase());
                    String base64 = null;
                    try {
                        base64 = Base64.encodeToString(sampleHtml.getBytes("UTF-8"), Base64.DEFAULT);
                        help.loadData(base64, "text/html; charset=utf-8", "base64");
                        //help.loadDataWithBaseURL(random(), sampleHtml, "text/html; charset=utf-8", "base64", null);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }
                    break;
                // You can have any number of case statements.
                default:
                    // Statements
            }
        } else {
            finish();
        }
    }
}
