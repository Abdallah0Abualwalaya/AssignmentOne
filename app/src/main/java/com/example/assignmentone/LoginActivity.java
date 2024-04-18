package com.example.assignmentone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtEmail;
    private RadioGroup radioG;
    private Button btnSave;
    private SharedPreferences prefs; //read
    private static SharedPreferences.Editor editor; //write

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUpSharedPrefs();
        setupViews();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                int id = radioG.getCheckedRadioButtonId();

                if (id == -1 || name.trim().isEmpty() || email.trim().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please fill in all fields and select an option", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton selectedRadioButton = findViewById(id);
                if (selectedRadioButton == null) {
                    Toast.makeText(LoginActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Save data to SharedPreferences
                editor.putString("name", name);
                editor.putString("email", email);
                editor.putInt("selectedRadioId", id);
                editor.apply();

                Intent intent1 = new Intent(LoginActivity.this, SubjectsActivity.class);
                startActivity(intent1);
            }
        });
    }
    private void setupViews() {
        btnSave = findViewById(R.id.btnSave);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        radioG = findViewById(R.id.radioG);
    }
    private void setUpSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }
}