package com.robosoft.archanakumari.parserassignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splach extends AppCompatActivity {

    private ImageView mImageview;
    private Animation animation;
    private Animation animation2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);
        mImageview = (ImageView)findViewById(R.id.imageView);
        animation = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        animation2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        mImageview.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mImageview.startAnimation(animation2);
                finish();
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }
}