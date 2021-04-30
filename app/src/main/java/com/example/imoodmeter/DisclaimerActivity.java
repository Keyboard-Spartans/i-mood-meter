package com.example.imoodmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DisclaimerActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeAppTitle();
        setContentView(R.layout.activity_disclaimer);

        Button disclaimerConfirm = findViewById(R.id.confirm_disclaimer);

        disclaimerConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {finish();}

    public void removeAppTitle(){
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
    }
}