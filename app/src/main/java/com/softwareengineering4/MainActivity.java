package com.softwareengineering4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static Button refreshButton;
    // public static TextView dataTextView;
    public static ListView personListView;

    public static PersonListItemAdapter personListItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshButton = findViewById(R.id.refreshButton);
        // dataTextView = findViewById(R.id.textView);
        personListView = findViewById(R.id.personListView);

        personListItemAdapter = new PersonListItemAdapter(this);
        personListView.setAdapter(personListItemAdapter);

        // Atualiza lista
        updatePersonList();
    }

    public void onClickRefreshButton(View view) {
        updatePersonList();
    }

    public void updatePersonList() {
        // Fetch data
        FetchDataProcess fetchDataProcess = new FetchDataProcess(this);
        fetchDataProcess.execute();
    }
}
