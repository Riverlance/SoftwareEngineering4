package com.softwareengineering4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.text.WordUtils;

public class PersonDetailsActivity extends AppCompatActivity {
    public ImageView profileImageView;
    public TextView nameTextView;
    public TextView usernameTextView;
    public TextView ageTextView;
    public TextView genderTextView;
    public TextView locationTextView;
    public TextView emailTextView;
    public TextView phoneTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);

        profileImageView = findViewById(R.id.profileImageView);
        nameTextView = findViewById(R.id.nameTextView);
        usernameTextView = findViewById(R.id.usernameTextView);
        ageTextView = findViewById(R.id.ageTextView);
        genderTextView = findViewById(R.id.genderTextView);
        locationTextView = findViewById(R.id.locationTextView);
        emailTextView = findViewById(R.id.emailTextView);
        phoneTextView = findViewById(R.id.phoneTextView);

        Intent intent = getIntent();
        PersonListItem personListItem = intent.getParcelableExtra("person");

        // Image
        if (!personListItem.pictureLargeURL.equals("")) {
            Picasso.with(this).load(personListItem.pictureLargeURL).into(profileImageView);
        }

        // Name
        nameTextView.setText(WordUtils.capitalize(personListItem.getName(true)));

        // Username
        usernameTextView.setText(personListItem.username);

        // Age
        ageTextView.setText(String.format("%d", personListItem.age));

        // Gender
        genderTextView.setText(personListItem.getGenderToString());

        // Location
        locationTextView.setText(personListItem.getLocation());

        // Email
        emailTextView.setText(personListItem.email);

        // Phone
        phoneTextView.setText(personListItem.phone);
    }
}
