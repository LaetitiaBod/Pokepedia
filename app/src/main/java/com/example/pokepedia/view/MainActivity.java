package com.example.pokepedia.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.pokepedia.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click_pokedex(View view) {
        Intent randomIntent = new Intent(this, ListActivity.class);
        startActivity(randomIntent);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

}
