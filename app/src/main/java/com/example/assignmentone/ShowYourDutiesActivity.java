package com.example.assignmentone;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ShowYourDutiesActivity extends AppCompatActivity {
    public static ArrayAdapter<Duty> adapter;
    private FloatingActionButton fabBack;
    private SharedPreferences prefs; //read
    private static SharedPreferences.Editor editor; //write

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_your_duties);

        setupSharedPrefs();

        Subject subject = DatabaseItems.search(getIntent().getStringExtra("title")).get(0);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, subject.getDuties());
        adapter.notifyDataSetChanged();

        ListView listView = findViewById(R.id.lvDueTests);
        listView.setAdapter(adapter);

        fabBack = findViewById(R.id.fabBack);
        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }
}