package com.example.ourhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMedicineDetailsActivity extends AppCompatActivity {
    TextView tvPackageName,tvTotalCost;
    EditText ed;
    Button btnCart,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);

        tvPackageName=findViewById(R.id.titleBMDPackageName);
        tvTotalCost=findViewById(R.id.textViewBMDTotalCost);
        ed=findViewById(R.id.editTextBMDMultiLine);
        btnBack=findViewById(R.id.buttonBMDBack);
        btnCart=findViewById(R.id.buttonBMDCart);

        ed.setKeyListener(null);

        Intent it=getIntent();
        tvPackageName.setText(it.getStringExtra("text1"));
        ed.setText(it.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost : "+it.getStringExtra("text3")+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                String product=tvPackageName.getText().toString();
                float price=Float.parseFloat(getIntent().getStringExtra("text3").toString());

                Database db=new Database(getApplicationContext(), "healthcare",null,1);

                if(db.checkCart(username,product)==1)
                {
                    Toast.makeText(getApplicationContext(),"Product Already Added",Toast.LENGTH_SHORT).show();
                }
                else{
                    db.addToCart(username,product,price,"medicine");
                    Toast.makeText(getApplicationContext(),"Record inserted to cart",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
                }
            }
        });
    }
}