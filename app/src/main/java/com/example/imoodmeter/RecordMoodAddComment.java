package com.example.imoodmeter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.imoodmeter.controller.MoodController;
import com.example.imoodmeter.model.MoodModel;
import com.example.imoodmeter.utils.moodMeterUtils;
import com.google.android.material.textfield.TextInputEditText;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDateTime;

public class RecordMoodAddComment extends AppCompatActivity implements View.OnClickListener{
    moodMeterUtils moodUtils = new moodMeterUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeAppTitle();
        setContentView(R.layout.record_mood_add_comment);

        Button saveCommentButton = findViewById(R.id.save_comment);
        saveCommentButton.setOnClickListener(this);

        Button skipCommentButton = findViewById(R.id.skip_comment);
        skipCommentButton.setOnClickListener(this);

        // get previously added moodVal
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            float moodVal = extras.getFloat("moodValue");
            String moodCommentQuestion = moodUtils.floatToQuestionConverter(moodVal);
            String moodEditHint= moodUtils.floatToEditConverter(moodVal);

            TextView textMoodQuestion = findViewById(R.id.text_mood_question);
            textMoodQuestion.setText(moodCommentQuestion);

//            TextInputEditText moodDescriptionEdit = findViewById(R.id.mood_description_edit);
//            moodDescriptionEdit.setHint(moodEditHint);
        }



    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addToMoodsArray(boolean saveComment) {
        Bundle extras = getIntent().getExtras();
        float moodVal = extras.getFloat("moodValue");
        LocalDateTime timeStamp = LocalDateTime.now();

        MoodModel moodToAdd;
        if (saveComment) {
            TextInputEditText moodDescriptionEdit = findViewById(R.id.mood_description_edit);
            String moodDesc = moodDescriptionEdit.getText().toString();

            moodToAdd = new MoodModel((int) moodVal, moodDesc, timeStamp);
        } else{
            moodToAdd = new MoodModel((int) moodVal, "No Description", timeStamp);
        }

        Log.w("ADD_MOOD", moodToAdd.print());

        MoodController.addMood(moodToAdd);
        return;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        // logic flow if comment is saved. transfer mood comment value
        if (view.getId() == R.id.save_comment || view.getId() == R.id.skip_comment) {
            boolean saveComment = view.getId() == R.id.save_comment;
            addToMoodsArray(saveComment);

            Bundle extras = getIntent().getExtras();
            float moodVal = extras.getFloat("moodValue");

            MoodController.printMoods();

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("moodValue", moodVal);
            intent.putExtra("moodRecorded", true);
            startActivity(intent);
        }
    }

    public void removeAppTitle(){
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
    }
}