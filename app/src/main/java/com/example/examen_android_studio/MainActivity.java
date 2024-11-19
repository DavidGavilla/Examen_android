package com.example.examen_android_studio;

import android.content.Intent;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;  // Correct import for androidx Toolbar

public class MainActivity extends AppCompatActivity {

    private FrameLayout skyFrame;
    private ImageView sun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        skyFrame = findViewById(R.id.sky);
        sun = findViewById(R.id.sun);

        // Bring the sun to the front so it appears above the sky fragment
        sun.setTranslationZ(1);  // Make sure the sun is above the sky view in Z order

        skyFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColorToNightAndMoveSun();
            }
        });

        // Setup the toolbar and set it as the action bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void changeColorToNightAndMoveSun() {
        // Sunset animation for sky background color
        ObjectAnimator dayToSunset = ObjectAnimator.ofArgb(skyFrame, "backgroundColor",
                getResources().getColor(R.color.LightBlue_morning), getResources().getColor(R.color.orange_sunset));
        dayToSunset.setDuration(3000);
        dayToSunset.start();

        // Animate the sun downward in front of the sky and behind the bottom view
        float moveDistance = 850f;
        ObjectAnimator sunMovement = ObjectAnimator.ofFloat(sun, "translationY", 0f, moveDistance);
        sunMovement.setDuration(4500);
        sunMovement.start();

        // Once the sunset animation ends, transition to night
        dayToSunset.addListener(new android.animation.Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(android.animation.Animator animation) {}

            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                ObjectAnimator sunsetToNight = ObjectAnimator.ofArgb(skyFrame, "backgroundColor",
                        getResources().getColor(R.color.orange_sunset), getResources().getColor(R.color.black_night));
                sunsetToNight.setDuration(1500);
                sunsetToNight.start();
            }

            @Override
            public void onAnimationCancel(android.animation.Animator animation) {}

            @Override
            public void onAnimationRepeat(android.animation.Animator animation) {}
        });
    }

    @Override

    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == R.id.sunset) {
            // Do nothing when Sunset is clicked (no navigation)
            return true;
        }

        if (item.getItemId() == R.id.face) {
            // Navigate to ColorSwapsActivity when "ColorChange" is clicked
            Intent intent = new Intent(MainActivity.this, ColorSwaps.class);
            startActivity(intent);
            return true;
        }

        // Default case if no other items are selected
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu with items from XML
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
