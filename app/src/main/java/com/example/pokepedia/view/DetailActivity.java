package com.example.pokepedia.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokepedia.R;
import com.example.pokepedia.controller.DetailController;
import com.example.pokepedia.model.Sprite;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class DetailActivity extends AppCompatActivity {

    DetailController detailcontroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailcontroller = new DetailController(this);

        detailcontroller.onCreate();
    }



    public void showDetails (String name, int id, int height, int weight, int base_exp, Sprite sprite) {

        DecimalFormat df = new DecimalFormat("############0.00");

        TextView nameView = findViewById(R.id.name);
        nameView.setText(name);

        TextView idView = findViewById(R.id.id);
        String idString = String.valueOf(id);
        idView.setText("#"+idString);

        TextView heightView = findViewById(R.id.height);
        heightView.setText("Height : "+df.format(height*0.1)+" m");

        TextView weightView = findViewById(R.id.weight);
        weightView.setText("Weight : "+df.format(weight*0.1)+" kg");

        TextView base_expView = findViewById(R.id.base_exp);
        base_expView.setText("Base experience : "+base_exp+" xp");

        ImageView spriteView = findViewById(R.id.icon_detail);
        Picasso.with(this).load(sprite.getFront_default()).into(spriteView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }
}
