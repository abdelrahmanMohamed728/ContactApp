package com.example.abdo.contactapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Main3Activity extends AppCompatActivity {
   EditText name1;
   EditText phone1;
   Button btn1;
    SQLConnector connector;
    ImageView img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getSupportActionBar().hide();
        name1 = findViewById(R.id.name1);
        phone1 = findViewById(R.id.phone1);
        img1 = findViewById(R.id.img1);
        btn1 = findViewById(R.id.btn1);
        connector = new SQLConnector(this);
        final int sentID = getIntent().getIntExtra("sentID",0);
        pair p = connector.getContact(sentID);
        name1.setText(p.getName());
        phone1.setText(p.getPhone());
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pair p = new pair(sentID,name1.getText().toString(),phone1.getText().toString());
                connector.updateContact(sentID,p);
                finish();

            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Main3Activity.this);
                builder.setMessage("Are you sure ?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        connector.deleteContact(sentID);
                        finish();

                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
              AlertDialog dialog=  builder.create();
              dialog.show();
            }
        });

    }
}
