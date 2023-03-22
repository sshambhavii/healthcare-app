package com.example.ourhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    Button dateButton,timeButton,btnBook,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        tv=findViewById(R.id.textViewBookApp);
        ed1=findViewById(R.id.editTextBookAppName);
        ed2=findViewById(R.id.editTextBookAppEmail);
        ed3=findViewById(R.id.editTextBookAppNo);
        ed4=findViewById(R.id.editTextBookAppFees);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it=getIntent();
        String title=it.getStringExtra("text1");
        String fullname=it.getStringExtra("text2");
        String address=it.getStringExtra("text3");
        String contact=it.getStringExtra("text4");
        String fee=it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Cons fee "+fee+"/-");

        dateButton=findViewById(R.id.buttonBookAppDate);
        timeButton=findViewById(R.id.buttonBookAppTime);

        btnBack=findViewById(R.id.buttonBookAppBack);
        btnBook=findViewById(R.id.buttonBookAppRegister);

        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();

                Database db=new Database(getApplicationContext(), "healthcare",null,1);
                if(db.checkAppointment(username,title+ " => "+fullname,address,contact,dateButton.getText().toString(),timeButton.getText().toString())==1)
                    Toast.makeText(getApplicationContext(),"Appointment already exists",Toast.LENGTH_SHORT).show();
                else
                {
                    db.addOrder(username,title+ " => "+fullname,address,contact,0,dateButton.getText().toString(),timeButton.getText().toString(),Float.parseFloat(fee),"medicine");
                    Toast.makeText(getApplicationContext(),"Your Appointment is done successfully!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BookAppointmentActivity.this,HomeActivity.class));
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookAppointmentActivity.this,FindDoctorActivity.class));
            }
        });
    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                dateButton.setText (i2+"/"+i1+"/"+i);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get (Calendar.YEAR);
        int month = cal.get (Calendar.MONTH);
        int day = cal.get (Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog( this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }

    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeButton.setText(i+":"+i1);
            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get (Calendar.HOUR);
        int min = cal.get (Calendar.MINUTE);
        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog( this, style, timeSetListener,hrs,min,true);
    }
}