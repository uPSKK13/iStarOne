package com.example.upskk_automatyka.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.szagurskii.patternedtextwatcher.PatternedTextWatcher;

public class Czujnik extends Activity {

    private Button kalibracja, max_ciśnienie, powrót1, powrót2, zapisz;
    EditText max_ciśnienie_txt;

    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUI();
        setContentView(R.layout.activity_czujnik);

        sharedPreferences = getSharedPreferences("Main",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        max_ciśnienie = findViewById(R.id.max_ciśnienie);
        kalibracja = findViewById(R.id.kalibracja);
        powrót1 = findViewById(R.id.powrot1);
        powrót2 = findViewById(R.id.powrot2);
        zapisz = findViewById(R.id.zapisz);
        max_ciśnienie_txt = findViewById(R.id.max_ciśnienie_txt);
        max_ciśnienie_txt.addTextChangedListener(new PatternedTextWatcher("##"));
        final ConstraintLayout czujnik1 = findViewById(R.id.czujnik1);
        final ConstraintLayout czujnik2 = findViewById(R.id.czujnik2);

        max_ciśnienie_txt.setText(sharedPreferences.getString("max_czujnik",""));

        max_ciśnienie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                czujnik1.setVisibility(View.GONE);
                czujnik2.setVisibility(View.VISIBLE);
            }
        });

        kalibracja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("kalibracja_ciśnienia", true);
                editor.putBoolean("synchro_czujnik", true);
                editor.apply();
            }
        });

        powrót1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Intent intent = new Intent(context, Menu.class);
                startActivity(intent);
            }
        });

        powrót2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                czujnik2.setVisibility(View.GONE);
                czujnik1.setVisibility(View.VISIBLE);
            }
        });

        zapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ciśnienie = max_ciśnienie_txt.getText().toString().trim();
                editor.putString("max_czujnik", ciśnienie);
                editor.putBoolean("synchro_czujnik", true);
                editor.apply(); // commit changes
                Toast.makeText(getApplicationContext(), "Zapisano",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }






    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}
