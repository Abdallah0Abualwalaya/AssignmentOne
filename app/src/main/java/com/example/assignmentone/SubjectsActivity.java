package com.example.assignmentone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class SubjectsActivity extends AppCompatActivity {
    private Spinner spnSubject;
    private Button btnAdd;
    private ListView listView;
    private SharedPreferences prefs; //read
    private static SharedPreferences.Editor editor; //write
    public static List<Subject> subjectsList;
    public static int POSITION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

        setupViews();
        setUpSharedPrefs();
        populateSpinner();
        DatabaseItems db= new DatabaseItems();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = spnSubject.getSelectedItem().toString();

                subjectsList = DatabaseItems.search(subject);
                Subject[] arr = subjectsList.toArray(new Subject[subjectsList.size()]);
                ArrayAdapter<Subject> adapter = new ArrayAdapter<Subject>(SubjectsActivity.this, android.R.layout.simple_list_item_1, arr);
                listView.setAdapter(adapter);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                POSITION = position;
                Subject subject = subjectsList.get(position);
                Intent intent2 = new Intent(SubjectsActivity.this, AddYourDutiesActivity.class);
                intent2.putExtra("title", subject.getName());
                startActivity(intent2);
            }
        });

        // Retrieve data from SharedPreferences
        String name = prefs.getString("name", "");
        String email = prefs.getString("email", "");
        int selectedRadioId = prefs.getInt("selectedRadioId", -1);
    }
    private void setupViews() {
        spnSubject = findViewById(R.id.spnSubject);
        btnAdd = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.listView);
    }
    private void setUpSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }
    private void populateSpinner() {
        String[] subjects = DatabaseItems.getSubjects();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subjects);
        spnSubject.setAdapter(adapter);
    }
}