package com.example.upskk_automatyka.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.szagurskii.patternedtextwatcher.PatternedTextWatcher;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.RED;

public class Program extends Activity implements View.OnClickListener {
    private Button powrót1, powrót2, zmiana2, zapisz1, zapisz2, rozstawa1, rozstawa2, rozstawa3, rozstawa4, rozstawa5, rozstawa6, rozstawa7, rozstawa8, wydatek1, wydatek2, wydatek3, wydatek4, wydatek5, wydatek6, wydatek7, wydatek8, zmiana1;
    EditText wydatek_txt1, wydatek_txt2, wydatek_txt3, wydatek_txt4, wydatek_txt5, wydatek_txt6, wydatek_txt7, wydatek_txt8, rozstawa_txt1, rozstawa_txt2, rozstawa_txt3, rozstawa_txt4, rozstawa_txt5, rozstawa_txt6, rozstawa_txt7, rozstawa_txt8;
    int parametr_r = 0, parametr_w = 0;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUI();

        boolean type = getIntent().getBooleanExtra("type", false);
        setContentView(R.layout.activity_program);


        sharedPreferences = getSharedPreferences("Main", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        powrót1 = findViewById(R.id.powrot1);
        powrót2 = findViewById(R.id.powrot2);
        zapisz1 = findViewById(R.id.zapisz1);
        zapisz2 = findViewById(R.id.zapisz2);
        zmiana1 = findViewById(R.id.zmiana1);
        zmiana2 = findViewById(R.id.zmiana2);
        rozstawa_txt1 = findViewById(R.id.rozstawa_txt1);
        rozstawa_txt2 = findViewById(R.id.rozstawa_txt2);
        rozstawa_txt3 = findViewById(R.id.rozstawa_txt3);
        rozstawa_txt4 = findViewById(R.id.rozstawa_txt4);
        rozstawa_txt5 = findViewById(R.id.rozstawa_txt5);
        rozstawa_txt6 = findViewById(R.id.rozstawa_txt6);
        rozstawa_txt7 = findViewById(R.id.rozstawa_txt7);
        rozstawa_txt8 = findViewById(R.id.rozstawa_txt8);
        rozstawa_txt1.addTextChangedListener(new PatternedTextWatcher("#.##"));
        rozstawa_txt2.addTextChangedListener(new PatternedTextWatcher("#.##"));
        rozstawa_txt3.addTextChangedListener(new PatternedTextWatcher("#.##"));
        rozstawa_txt4.addTextChangedListener(new PatternedTextWatcher("#.##"));
        rozstawa_txt5.addTextChangedListener(new PatternedTextWatcher("#.##"));
        rozstawa_txt6.addTextChangedListener(new PatternedTextWatcher("#.##"));
        rozstawa_txt7.addTextChangedListener(new PatternedTextWatcher("#.##"));
        rozstawa_txt8.addTextChangedListener(new PatternedTextWatcher("#.##"));

        rozstawa1 = findViewById(R.id.rozstawa1);
        rozstawa2 = findViewById(R.id.rozstawa2);
        rozstawa3 = findViewById(R.id.rozstawa3);
        rozstawa4 = findViewById(R.id.rozstawa4);
        rozstawa5 = findViewById(R.id.rozstawa5);
        rozstawa6 = findViewById(R.id.rozstawa6);
        rozstawa7 = findViewById(R.id.rozstawa7);
        rozstawa8 = findViewById(R.id.rozstawa8);

        wydatek_txt1 = findViewById(R.id.wydatek_txt1);
        wydatek_txt2 = findViewById(R.id.wydatek_txt2);
        wydatek_txt3 = findViewById(R.id.wydatek_txt3);
        wydatek_txt4 = findViewById(R.id.wydatek_txt4);
        wydatek_txt5 = findViewById(R.id.wydatek_txt5);
        wydatek_txt6 = findViewById(R.id.wydatek_txt6);
        wydatek_txt7 = findViewById(R.id.wydatek_txt7);
        wydatek_txt8 = findViewById(R.id.wydatek_txt8);
        wydatek_txt1.addTextChangedListener(new PatternedTextWatcher("####"));
        wydatek_txt2.addTextChangedListener(new PatternedTextWatcher("####"));
        wydatek_txt3.addTextChangedListener(new PatternedTextWatcher("####"));
        wydatek_txt4.addTextChangedListener(new PatternedTextWatcher("####"));
        wydatek_txt5.addTextChangedListener(new PatternedTextWatcher("####"));
        wydatek_txt6.addTextChangedListener(new PatternedTextWatcher("####"));
        wydatek_txt7.addTextChangedListener(new PatternedTextWatcher("####"));
        wydatek_txt8.addTextChangedListener(new PatternedTextWatcher("####"));

        wydatek1 = findViewById(R.id.wydatek1);
        wydatek2 = findViewById(R.id.wydatek2);
        wydatek3 = findViewById(R.id.wydatek3);
        wydatek4 = findViewById(R.id.wydatek4);
        wydatek5 = findViewById(R.id.wydatek5);
        wydatek6 = findViewById(R.id.wydatek6);
        wydatek7 = findViewById(R.id.wydatek7);
        wydatek8 = findViewById(R.id.wydatek8);

        rozstawa1.setOnClickListener(this);
        rozstawa2.setOnClickListener(this);
        rozstawa3.setOnClickListener(this);   //reakcja na przyciski
        rozstawa4.setOnClickListener(this);
        rozstawa5.setOnClickListener(this);
        rozstawa6.setOnClickListener(this);
        rozstawa7.setOnClickListener(this);
        rozstawa8.setOnClickListener(this);

        wydatek1.setOnClickListener(this);
        wydatek2.setOnClickListener(this);
        wydatek3.setOnClickListener(this);   //reakcja na przyciski
        wydatek4.setOnClickListener(this);
        wydatek5.setOnClickListener(this);
        wydatek6.setOnClickListener(this);
        wydatek7.setOnClickListener(this);
        wydatek8.setOnClickListener(this);

        //wydatek_cieczy_txt.setText(sharedPreferences.getString("wydatek_cieczy",""));
        //rozstawa_txt.setText(sharedPreferences.getString("rozstawa",""));

        final ConstraintLayout program2 = findViewById(R.id.program2);
        final ConstraintLayout program2b = findViewById(R.id.program2b);
        final ConstraintLayout program3 = findViewById(R.id.program3);
        final ConstraintLayout program3b = findViewById(R.id.program3b);

        if(type) {
            program2b.setVisibility(View.VISIBLE);
            wydatek();
        } else {
            program3b.setVisibility(View.VISIBLE);
            rozstawa();
        }



        powrót1.setOnClickListener(v -> {
            Context context = getApplicationContext();
            Intent intent = new Intent(context, Menu.class);
            startActivity(intent);
        });

        powrót2.setOnClickListener(v -> {
            Context context = getApplicationContext();
            Intent intent = new Intent(context, Menu.class);
            startActivity(intent);
        });

        zmiana1.setOnClickListener(v -> {
            program3b.setVisibility(View.GONE);
            program3.setVisibility(View.VISIBLE);
        });

        zmiana2.setOnClickListener(v -> {
            program2b.setVisibility(View.GONE);
            program2.setVisibility(View.VISIBLE);
        });

        zapisz1.setOnClickListener(v -> {
            String s_wydatek1 = wydatek_txt1.getText().toString().trim();
            if(!TextUtils.isEmpty(s_wydatek1)) {
                editor.putString("wydatek1", s_wydatek1);
                wydatek1.setText(s_wydatek1);
            }
            String s_wydatek2 = wydatek_txt2.getText().toString().trim();
            if(!TextUtils.isEmpty(s_wydatek2)) {
                editor.putString("wydatek2", s_wydatek2);
                wydatek2.setText(s_wydatek2);
            }
            String s_wydatek3 = wydatek_txt3.getText().toString().trim();
            if(!TextUtils.isEmpty(s_wydatek3)) {
                editor.putString("wydatek3", s_wydatek3);
                wydatek3.setText(s_wydatek3);
            }
            String s_wydatek4 = wydatek_txt4.getText().toString().trim();
            if(!TextUtils.isEmpty(s_wydatek4)) {
                editor.putString("wydatek4", s_wydatek4);
                wydatek4.setText(s_wydatek4);
            }
            String s_wydatek5 = wydatek_txt5.getText().toString().trim();
            if(!TextUtils.isEmpty(s_wydatek5)) {
                editor.putString("wydatek5", s_wydatek5);
                wydatek5.setText(s_wydatek5);
            }
            String s_wydatek6 = wydatek_txt6.getText().toString().trim();
            if(!TextUtils.isEmpty(s_wydatek6)) {
                editor.putString("wydatek6", s_wydatek6);
                wydatek6.setText(s_wydatek6);
            }
            String s_wydatek7 = wydatek_txt7.getText().toString().trim();
            if(!TextUtils.isEmpty(s_wydatek7)) {
                editor.putString("wydatek7", s_wydatek7);
                wydatek7.setText(s_wydatek7);
            }
            String s_wydatek8 = wydatek_txt8.getText().toString().trim();
            if(!TextUtils.isEmpty(s_wydatek8)) {
                editor.putString("wydatek8", s_wydatek8);
                wydatek8.setText(s_wydatek8);
            }
            editor.apply(); // commit changes
            program2.setVisibility(View.GONE);
            program2b.setVisibility(View.VISIBLE);
        });

        zapisz2.setOnClickListener(v -> {
            String s_rozstawa1 = rozstawa_txt1.getText().toString().trim();
            if(!TextUtils.isEmpty(s_rozstawa1)) {
                editor.putString("rozstawa1", s_rozstawa1);
                rozstawa1.setText(s_rozstawa1);
            }
            String s_rozstawa2 = rozstawa_txt2.getText().toString().trim();
            if(!TextUtils.isEmpty(s_rozstawa2)) {
                editor.putString("rozstawa2", s_rozstawa2);
                rozstawa2.setText(s_rozstawa2);
            }
            String s_rozstawa3 = rozstawa_txt3.getText().toString().trim();
            if(!TextUtils.isEmpty(s_rozstawa3)) {
                editor.putString("rozstawa3", s_rozstawa3);
                rozstawa3.setText(s_rozstawa3);
            }
            String s_rozstawa4 = rozstawa_txt4.getText().toString().trim();
            if(!TextUtils.isEmpty(s_rozstawa4)) {
                editor.putString("rozstawa4", s_rozstawa4);
                rozstawa4.setText(s_rozstawa4);
            }
            String s_rozstawa5 = rozstawa_txt5.getText().toString().trim();
            if(!TextUtils.isEmpty(s_rozstawa5)) {
                editor.putString("rozstawa5", s_rozstawa5);
                rozstawa5.setText(s_rozstawa5);
            }
            String s_rozstawa6 = rozstawa_txt6.getText().toString().trim();
            if(!TextUtils.isEmpty(s_rozstawa6)) {
                editor.putString("rozstawa6", s_rozstawa6);
                rozstawa6.setText(s_rozstawa6);
            }
            String s_rozstawa7 = rozstawa_txt7.getText().toString().trim();
            if(!TextUtils.isEmpty(s_rozstawa7)) {
                editor.putString("rozstawa7", s_rozstawa7);
                rozstawa7.setText(s_rozstawa7);
            }
            String s_rozstawa8 = rozstawa_txt8.getText().toString().trim();
            if(!TextUtils.isEmpty(s_rozstawa8)) {
                editor.putString("rozstawa8", s_rozstawa8);
                rozstawa8.setText(s_rozstawa8);
            }
            editor.apply(); // commit changes
            program3.setVisibility(View.GONE);
            program3b.setVisibility(View.VISIBLE);
        });
    }

    public void rozstawa() {
        int para = sharedPreferences.getInt("rozstawa_p", 1);
        switch(para) {
            case 1:
                rozstawa1.setTextColor(RED);
                break;
            case 2:
                rozstawa2.setTextColor(RED);
                break;
            case 3:
                rozstawa3.setTextColor(RED);
                break;
            case 4:
                rozstawa4.setTextColor(RED);
                break;
            case 5:
                rozstawa5.setTextColor(RED);
                break;
            case 6:
                rozstawa6.setTextColor(RED);
                break;
            case 7:
                rozstawa7.setTextColor(RED);
                break;
            case 8:
                rozstawa8.setTextColor(RED);
                break;
        }
        rozstawa1.setText(sharedPreferences.getString("rozstawa1", "2.5"));
        rozstawa2.setText(sharedPreferences.getString("rozstawa2", "2.6"));
        rozstawa3.setText(sharedPreferences.getString("rozstawa3", "2.7"));
        rozstawa4.setText(sharedPreferences.getString("rozstawa4", "2.8"));
        rozstawa5.setText(sharedPreferences.getString("rozstawa5", "2.9"));
        rozstawa6.setText(sharedPreferences.getString("rozstawa6", "3.0"));
        rozstawa7.setText(sharedPreferences.getString("rozstawa7", "3.1"));
        rozstawa8.setText(sharedPreferences.getString("rozstawa8", "3.2"));
    }

    public void wydatek() {
        int para = sharedPreferences.getInt("wydatek_cieczy_p", 1);
        switch(para) {
            case 1:
                wydatek1.setTextColor(RED);
                break;
            case 2:
                wydatek2.setTextColor(RED);
                break;
            case 3:
                wydatek3.setTextColor(RED);
                break;
            case 4:
                wydatek4.setTextColor(RED);
                break;
            case 5:
                wydatek5.setTextColor(RED);
                break;
            case 6:
                wydatek6.setTextColor(RED);
                break;
            case 7:
                wydatek7.setTextColor(RED);
                break;
            case 8:
                wydatek8.setTextColor(RED);
                break;
        }
        wydatek1.setText(sharedPreferences.getString("wydatek1", "500"));
        wydatek2.setText(sharedPreferences.getString("wydatek2", "600"));
        wydatek3.setText(sharedPreferences.getString("wydatek3", "700"));
        wydatek4.setText(sharedPreferences.getString("wydatek4", "800"));
        wydatek5.setText(sharedPreferences.getString("wydatek5", "900"));
        wydatek6.setText(sharedPreferences.getString("wydatek6", "1000"));
        wydatek7.setText(sharedPreferences.getString("wydatek7", "1100"));
        wydatek8.setText(sharedPreferences.getString("wydatek8", "1200"));
    }

    @Override
    public void onClick(View view) {
        rozstawa1.setTextColor(BLACK);
        rozstawa2.setTextColor(BLACK);
        rozstawa3.setTextColor(BLACK);
        rozstawa4.setTextColor(BLACK);
        rozstawa5.setTextColor(BLACK);
        rozstawa6.setTextColor(BLACK);
        rozstawa7.setTextColor(BLACK);
        rozstawa8.setTextColor(BLACK);
        wydatek1.setTextColor(BLACK);
        wydatek2.setTextColor(BLACK);
        wydatek3.setTextColor(BLACK);
        wydatek4.setTextColor(BLACK);
        wydatek5.setTextColor(BLACK);
        wydatek6.setTextColor(BLACK);
        wydatek7.setTextColor(BLACK);
        wydatek8.setTextColor(BLACK);

        int helpr, helpw;
        helpr=parametr_r;
        helpw=parametr_w;

        if (view.getId() == rozstawa1.getId()) {
            parametr_r = 1;
            rozstawa1.setTextColor(RED);
            editor.putString("rozstawa", sharedPreferences.getString("rozstawa1", "2.5"));
        } else if (view.getId() == rozstawa2.getId()) {
            parametr_r = 2;
            rozstawa2.setTextColor(RED);
            editor.putString("rozstawa", sharedPreferences.getString("rozstawa2", "2.6"));
        } else if (view.getId() == rozstawa3.getId()) {
            parametr_r = 3;
            rozstawa3.setTextColor(RED);
            editor.putString("rozstawa", sharedPreferences.getString("rozstawa3", "2.7"));
        } else if (view.getId() == rozstawa4.getId()) {
            parametr_r = 4;
            rozstawa4.setTextColor(RED);
            editor.putString("rozstawa", sharedPreferences.getString("rozstawa4", "2.8"));
        } else if (view.getId() == rozstawa5.getId()) {
            parametr_r = 5;
            rozstawa5.setTextColor(RED);
            editor.putString("rozstawa", sharedPreferences.getString("rozstawa5", "2.9"));
        } else if (view.getId() == rozstawa6.getId()) {
            parametr_r = 6;
            rozstawa6.setTextColor(RED);
            editor.putString("rozstawa", sharedPreferences.getString("rozstawa6", "3.0"));
        } else if (view.getId() == rozstawa7.getId()) {
            parametr_r = 7;
            rozstawa7.setTextColor(RED);
            editor.putString("rozstawa", sharedPreferences.getString("rozstawa7", "3.1"));
        } else if (view.getId() == rozstawa8.getId()) {
            parametr_r = 8;
            rozstawa8.setTextColor(RED);
            editor.putString("rozstawa", sharedPreferences.getString("rozstawa8", "3.2"));
        }
        if(helpr!=parametr_r) {
            editor.putInt("rozstawa_p", parametr_r);
            editor.apply();
        }
        if (view.getId() == wydatek1.getId()) {
            parametr_w = 1;
            wydatek1.setTextColor(RED);
            editor.putString("wydatek_cieczy", sharedPreferences.getString("wydatek1", "500"));
        } else if (view.getId() == wydatek2.getId()) {
            parametr_w = 2;
            wydatek2.setTextColor(RED);
            editor.putString("wydatek_cieczy", sharedPreferences.getString("wydatek2", "600"));
        } else if (view.getId() == wydatek3.getId()) {
            parametr_w = 3;
            wydatek3.setTextColor(RED);
            editor.putString("wydatek_cieczy", sharedPreferences.getString("wydatek3", "700"));
        } else if (view.getId() == wydatek4.getId()) {
            parametr_w = 4;
            wydatek4.setTextColor(RED);
            editor.putString("wydatek_cieczy", sharedPreferences.getString("wydatek4", "800"));
        } else if (view.getId() == wydatek5.getId()) {
            parametr_w = 5;
            wydatek5.setTextColor(RED);
            editor.putString("wydatek_cieczy", sharedPreferences.getString("wydatek5", "900"));
        } else if (view.getId() == wydatek6.getId()) {
            parametr_w = 6;
            wydatek6.setTextColor(RED);
            editor.putString("wydatek_cieczy", sharedPreferences.getString("wydatek6", "1000"));
        } else if (view.getId() == wydatek7.getId()) {
            parametr_w = 7;
            wydatek7.setTextColor(RED);
            editor.putString("wydatek_cieczy", sharedPreferences.getString("wydatek7", "1100"));
        } else if (view.getId() == wydatek8.getId()) {
            parametr_w = 8;
            wydatek8.setTextColor(RED);
            editor.putString("wydatek_cieczy", sharedPreferences.getString("wydatek8", "1200"));
        }
        if(helpw!=parametr_w) {
            editor.putInt("wydatek_cieczy_p", parametr_w);
            editor.apply();
        }

        editor.putBoolean("synchro_program", true);
        editor.apply();
        Toast.makeText(getApplicationContext(), "Zapisano",
                Toast.LENGTH_SHORT).show();
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
                View.SYSTEM_UI_FLAG_IMMERSIVE
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
