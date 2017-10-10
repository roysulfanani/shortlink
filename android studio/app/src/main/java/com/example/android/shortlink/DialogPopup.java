package com.example.android.shortlink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DialogPopup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_popup);

        TextView shortlink = (TextView) findViewById(R.id.textView);
        //TextView alamat = (TextView) findViewById(R.id.alamat);
        shortlink.setText(getIntent().getStringExtra("shortlink"));
        //alamat.setText(alamat.getText().toString() + " " + getIntent().getStringExtra("alamat"));

    }
}
