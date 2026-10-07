package com.example.smsguard;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private static final int SMS_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 50, 40, 40);

        TextView title = new TextView(this);
        title.setText("SMS Guard");
        title.setTextSize(28);
        layout.addView(title);

        TextView info = new TextView(this);
        info.setText("\nDetects repeated SMS from the same sender and records them locally.\n\n"
                + "After 5 messages from the same sender, the sender is flagged as spam.\n");
        info.setTextSize(18);
        layout.addView(info);

        Button permission = new Button(this);
        permission.setText("Grant SMS Permission");
        permission.setOnClickListener(v -> requestSmsPermission());
        layout.addView(permission);

        setContentView(layout);
        requestSmsPermission();
    }

    private void requestSmsPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS},
                    SMS_PERMISSION_CODE
            );
        } else {
            Toast.makeText(this, "SMS permission is already granted.", Toast.LENGTH_SHORT).show();
        }
    }
}
