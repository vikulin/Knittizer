package org.vikulin.knittizer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AlertActivity extends AppCompatActivity {

    public void showAlertDialog(String title, String message){
        if(!this.isFinishing())
            new AlertDialog.Builder(this)
                    .setTitle(title)
                    .setMessage(message)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
