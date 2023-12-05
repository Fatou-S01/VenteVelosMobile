package sn.ept.git.dic2.ventevelosandroid.activities.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import sn.ept.git.dic2.ventevelosandroid.R;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        String message = getIntent().getStringExtra("Message");

        TextView textView = findViewById(R.id.message);
        textView.setText(message);
    }
}