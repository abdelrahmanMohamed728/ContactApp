package com.example.abdo.contactapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  ListView list1;
  Button btn1;
  int cntr;
    List<pair> l;
    SQLConnector connector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list1 = findViewById(R.id.list1);
        btn1 = findViewById(R.id.btn1);
         connector = new SQLConnector(this);
        cntr=0;
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,Main3Activity.class);
                pair p =l.get(position);
                intent.putExtra("sentID",p.getId());

                startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("sentID",cntr);
                cntr++;
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        l =connector.getAllContacts();
        mainListAdapter adapter = new mainListAdapter(MainActivity.this,l);
        list1.setAdapter(adapter);
    }
}
