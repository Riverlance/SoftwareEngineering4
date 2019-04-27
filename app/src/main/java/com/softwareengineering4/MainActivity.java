package com.softwareengineering4;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    public static Button refreshButton;
    public static ListView personListView;

    public static PersonListItemAdapter personListItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshButton = findViewById(R.id.refreshButton);
        personListView = findViewById(R.id.personListView);

        personListItemAdapter = new PersonListItemAdapter(this);
        personListView.setAdapter(personListItemAdapter);
        personListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), PersonDetailsActivity.class);
                intent.putExtra("person", (Parcelable) personListView.getAdapter().getItem(i));
                startActivity(intent);
            }
        });

        // Atualiza lista
        updatePersonList();
    }

    public void onClickRefreshButton(View view) {
        updatePersonList();
    }

    public void updatePersonList() {
        // Fetch data
        FetchDataProcess fetchDataProcess = new FetchDataProcess();
        fetchDataProcess.execute();
    }
}
