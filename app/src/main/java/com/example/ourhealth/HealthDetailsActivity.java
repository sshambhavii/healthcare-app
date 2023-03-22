package com.example.ourhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HealthDetailsActivity extends AppCompatActivity {
    TextView tv;
    ImageView img;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_details);

        tv=findViewById(R.id.titleHADTitle);
        img=findViewById(R.id.imageViewHAD);
        btnBack=findViewById(R.id.buttonHADBack);

        Intent it=getIntent();
        tv.setText(it.getStringExtra("text1"));

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            int resId=bundle.getInt("text2");
            img.setImageResource(resId);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthDetailsActivity.this,HealthActivity.class));
            }
        });
    }
}