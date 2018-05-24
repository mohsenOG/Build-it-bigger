package com.wonderfulme.libandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String INTENT_JOKE = "INTENT_JOKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Intent receivedIntent = getIntent();
        if (getIntent() == null) {
            throw new RuntimeException(this.toString() + "Must include a joke");
        }
        String joke = receivedIntent.getStringExtra(INTENT_JOKE);

        TextView jokeTextView = findViewById(R.id.tv_joker);
        jokeTextView.setText(joke);
    }
}
