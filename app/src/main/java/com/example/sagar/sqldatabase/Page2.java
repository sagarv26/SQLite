package com.example.sagar.sqldatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Page2 extends AppCompatActivity {

    TextView textView;
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        textView=(TextView)findViewById(R.id.textView);
        value=getIntent().getExtras().getString("data");
        textView.setText(value);
    }
}
