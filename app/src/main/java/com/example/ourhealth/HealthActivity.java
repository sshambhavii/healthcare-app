package com.example.ourhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthActivity extends AppCompatActivity {
    private String[][] health_details= {
            {"Walking Daily", "", "", "", "Click for More Details"},
            {"Home care of COVID-19", "", "", "", "Click for More Details"},
            {"Stop Smoking", "", "", "", "Click for More Details"},
            {"Menstrual Cramps", "", "", "", "Click for More Details"},
            {"Healthy Gut", "", "", "", "Click for More Details"},
    };
    private int[] images={
            R. drawable.health1,
            R.drawable. health2,
            R. drawable.health3,
            R.drawable.health4,
            R.drawable.health5
    };
    HashMap<String,String > item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnback;
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        lst=findViewById(R.id.ListViewHA);
        btnback=findViewById(R.id.buttonHABack);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthActivity.this,HomeActivity.class));
            }
        });

        list=new ArrayList();
        for(int i=0;i<health_details.length;i++)
        {
            item= new HashMap<String,String>();
            item.put("line1",health_details[i][0]);
            item.put("line2",health_details[i][1]);
            item.put("line3",health_details[i][2]);
            item.put("line4",health_details[i][3]);
            item.put("line5",health_details[i][4]);
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.linea,R.id.lineb,R.id.linec,R.id.lined,R.id.linee});

        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(HealthActivity.this,HealthDetailsActivity.class);
                it.putExtra("text1",health_details[i][0]);
                it.putExtra("text2",images[i]);
                startActivity(it);
            }
        });
    }
}