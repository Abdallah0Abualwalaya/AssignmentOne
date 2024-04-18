package com.example.assignmentone;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Calendar;
import java.util.Date;

public class AddYourDutiesActivity extends AppCompatActivity {
    private EditText edtTitle;
    private CalendarView calendarView_Duty;
    private Date selectedDate;
    private Button btnAddDuty;
    private Button btnShowDuty;
    private FloatingActionButton fabBack;
    private SharedPreferences prefs; //read
    private SharedPreferences.Editor editor; //write

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_your_duties);

        setUpViews();
        setUpSharedPrefs();

        btnAddDuty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDuty();
                ClearEDTS();
            }
        });

        btnShowDuty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(AddYourDutiesActivity.this,ShowYourDutiesActivity.class);
                intent3.putExtra("title", getIntent().getStringExtra("title"));
                startActivity(intent3);
            }
        });

        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {finish();}
        });

        calendarView_Duty.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar sc = Calendar.getInstance();
                sc.set(year,month,dayOfMonth);
                selectedDate = sc.getTime();
            }
        });
    }
    private void setUpViews(){
        edtTitle = findViewById(R.id.edtTitle);
        calendarView_Duty = findViewById(R.id.calendarView_Duty);
        btnAddDuty = findViewById(R.id.btnAddDuty);
        btnShowDuty = findViewById(R.id.btnShowDuty);
        fabBack = findViewById(R.id.fabBack);
    }
    private void setUpSharedPrefs(){
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }
    private void ClearEDTS(){
        edtTitle.setText("");
    }
    private void addDuty(){
        String title = edtTitle.getText().toString();
        if(title.isEmpty()) title = "N/A";
        Duty newDuty = new Duty(title, selectedDate);
        Subject subject = DatabaseItems.search(getIntent().getStringExtra("title")).get(0);
        subject.getDuties().add(newDuty);
    }
}