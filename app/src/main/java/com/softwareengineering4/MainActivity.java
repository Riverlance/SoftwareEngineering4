package com.softwareengineering4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static Button clickmeButton;
    public static TextView dataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickmeButton = findViewById(R.id.clickmeButton);
        dataTextView = findViewById(R.id.dataTextView);
    }

    public void onClickMeButton(View view) {
        FetchDataProcess fetchDataProcess = new FetchDataProcess();
        fetchDataProcess.execute();
    }
}
