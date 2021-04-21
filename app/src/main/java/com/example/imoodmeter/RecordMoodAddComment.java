package com.example.imoodmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RecordMoodAddComment extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_mood_add_comment);

        Button saveCommentButton = (Button) findViewById(R.id.save_comment);
        saveCommentButton.setOnClickListener(this);

        Button skipCommentButton = (Button) findViewById(R.id.skip_comment);
        skipCommentButton.setOnClickListener(this);

        // get previously added moodVal
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            float moodVal = extras.getFloat("moodVal");
        }



    }

    @Override
    public void onClick(View view) {
        // TODO:
        // 1. Find a way to locally transfer int value from recordMood + comment section + datetime

        // logic flow if comment is saved. transfer mood comment value
        if (view.getId() == R.id.save_comment) {
            Intent intent = new Intent(this, RecordMoodRecorded.class);
            startActivity(intent);
        }

        // logic flow if comment is not saved. ignore mood comment value
        if (view.getId() == R.id.skip_comment) {
            Intent intent = new Intent(this, RecordMoodRecorded.class);
            startActivity(intent);
        }
    }
}