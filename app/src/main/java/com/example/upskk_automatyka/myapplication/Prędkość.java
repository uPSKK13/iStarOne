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

public class Prędkość extends Activity {

    private Button impulsy, predkosc_zawracania, powrót1, powrót2, powrót3, zapisz1, zapisz2;
    EditText impulsy_txt, predkosc_txt;

    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUI();
        setContentView(R.layout.activity_predkosc);

        sharedPreferences = getSharedPreferences("Main", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        impulsy = findViewById(R.id.impulsy);
        predkosc_zawracania = findViewById(R.id.predkosc_zawracania);
        powrót1 = findViewById(R.id.powrot1);
        powrót2 = findViewById(R.id.powrot2);
        powrót3 = findViewById(R.id.powrot3);
        zapisz1 = findViewById(R.id.zapisz1);
        zapisz2 = findViewById(R.id.zapisz2);
        predkosc_txt = findViewById(R.id.predkosc_txt);
        predkosc_txt.addTextChangedListener(new PatternedTextWatcher("##.##"));
        impulsy_txt = findViewById(R.id.impulsy_txt);
        impulsy_txt.addTextChangedListener(new PatternedTextWatcher("###"));
        final ConstraintLayout predkosc1 = findViewById(R.id.predkosc1);
        final ConstraintLayout predkosc2 = findViewById(R.id.predkosc2);
        final ConstraintLayout predkosc3 = findViewById(R.id.predkosc3);

        impulsy_txt.setText(sharedPreferences.getString("impulsy",""));
        predkosc_txt.setText(sharedPreferences.getString("predkosc_zawracania",""));

        impulsy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                predkosc1.setVisibility(View.GONE);
                predkosc2.setVisibility(View.VISIBLE);
            }
        });

        predkosc_zawracania.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                predkosc1.setVisibility(View.GONE);
                predkosc3.setVisibility(View.VISIBLE);
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
                predkosc2.setVisibility(View.GONE);
                predkosc1.setVisibility(View.VISIBLE);
            }
        });

        powrót3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                predkosc3.setVisibility(View.GONE);
                predkosc1.setVisibility(View.VISIBLE);
            }
        });

        zapisz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s_impulsy = impulsy_txt.getText().toString().trim();
                editor.putString("impulsy", s_impulsy);
                editor.putBoolean("synchro_prędkość", true);
                editor.apply(); // commit changes
                Toast.makeText(getApplicationContext(), "Zapisano",
                        Toast.LENGTH_SHORT).show();
            }
        });

        zapisz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s_predkosc = predkosc_txt.getText().toString().trim();
                editor.putString("predkosc_zawracania", s_predkosc);
                editor.putBoolean("synchro_prędkość", true);
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
