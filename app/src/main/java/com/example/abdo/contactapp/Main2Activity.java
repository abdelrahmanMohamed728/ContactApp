package com.example.abdo.contactapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
   EditText name1;
   EditText phone1;
   Button btn1;
   SQLConnector sqlConnector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name1 = findViewById(R.id.name1);
        phone1 = findViewById(R.id.phone1);
        btn1 = findViewById(R.id.btn1);
        sqlConnector = new SQLConnector(Main2Activity.this);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pair p = new pair(getIntent().getIntExtra("sentID",0),name1.getText().toString(),phone1.getText().toString());
                sqlConnector.addContact(p);
                finish();
            }
        });
    }
}
