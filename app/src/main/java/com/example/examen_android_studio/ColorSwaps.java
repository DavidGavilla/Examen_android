package com.example.examen_android_studio;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ColorSwaps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_color_swaps);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
    }

    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == R.id.sunset) {
            Intent intent = new Intent(ColorSwaps.this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        if (item.getItemId() == R.id.face) {
            // Navigate to ColorSwapsActivity when "ColorChange" is clicked
            Intent intent = new Intent(ColorSwaps.this, ColorSwaps.class);
            startActivity(intent);
            return true;
        }

        // Default case if no other items are selected
        return super.onOptionsItemSelected(item);
    }
}