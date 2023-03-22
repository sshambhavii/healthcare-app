package com.example.ourhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername,edPassword,edEmail,edConfirm;
    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername=findViewById(R.id.editTextRegUsername);
        edPassword=findViewById(R.id.editTextRegPassword);
        edEmail=findViewById(R.id.editTextRegEmail);
        edConfirm=findViewById(R.id.editTextRegConfirmPassword);
        btn=findViewById(R.id.buttonRegister);
        tv=findViewById(R.id.textViewExistingUser);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirm.getText().toString();
                String email = edEmail.getText().toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                if (username.length() == 0 || password.length() == 0 || confirm.length() == 0 || email.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(password.compareTo(confirm)==0){
                        db.register(username,email,password);
                        Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Passwords didn't match", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}