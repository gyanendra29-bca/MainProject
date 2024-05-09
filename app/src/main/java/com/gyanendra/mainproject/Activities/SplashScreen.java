package com.gyanendra.mainproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gyanendra.mainproject.MainActivity;
import com.gyanendra.mainproject.R;

public class SplashScreen extends AppCompatActivity {

    Animation topanim,bottomanim;
    TextView txt,txt1;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        topanim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomanim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        img = findViewById(R.id.imagesplash);
        txt = findViewById(R.id.textsplash);
        txt1 = findViewById(R.id.txt1);

        img.setAnimation(topanim);
        txt.setAnimation(bottomanim);
        txt1.setAnimation(bottomanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);

    }
}

