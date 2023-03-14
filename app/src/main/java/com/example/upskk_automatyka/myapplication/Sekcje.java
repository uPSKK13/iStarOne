package com.example.upskk_automatyka.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Sekcje extends Activity implements TextWatcher {

    private Button liczba_sekcji, liczba_dysz, parametry_sekcji_a,parametry_sekcji_m, powrót1, powrót12, powrót13, powrót2, powrót3, powrót4, powrót5, zapisz12, zapisz13,  zapisz2, zapisz3, zapisz4, teejet, arag, albuz, lechler;
    EditText parametry_sekcji1_txt, parametry_sekcji2_txt, parametry_sekcji11_txt, liczba_dysz_txt, liczba_dysz1_txt, liczba_dysz2_txt;

    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUI();
        setContentView(R.layout.activity_sekcja);

        sharedPreferences = getSharedPreferences("Main", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        liczba_sekcji = findViewById(R.id.liczba_sekcji);
        parametry_sekcji_a = findViewById(R.id.parametry_sekcji_a);
        parametry_sekcji_m = findViewById(R.id.parametry_sekcji_m);
        liczba_dysz = findViewById(R.id.liczba_dysz);
        powrót1 = findViewById(R.id.powrot1);
        powrót12 = findViewById(R.id.powrot12);
        powrót13 = findViewById(R.id.powrot13);
        powrót2 = findViewById(R.id.powrot2);
        powrót3 = findViewById(R.id.powrot3);
        powrót4 = findViewById(R.id.powrot4);
        powrót5 = findViewById(R.id.powrot5);
        zapisz12 = findViewById(R.id.zapisz12);
        zapisz13 = findViewById(R.id.zapisz13);
        zapisz2 = findViewById(R.id.zapisz2);
        zapisz3 = findViewById(R.id.zapisz3);
        zapisz4 = findViewById(R.id.zapisz4);
        teejet = findViewById(R.id.marka1);
        arag = findViewById(R.id.marka2);
        albuz = findViewById(R.id.marka3);
        lechler = findViewById(R.id.marka4);
        parametry_sekcji1_txt = findViewById(R.id.pressure1_txt);
        parametry_sekcji2_txt = findViewById(R.id.pressure2_txt);
        parametry_sekcji11_txt = findViewById(R.id.pressure11_txt);
        liczba_dysz_txt = findViewById(R.id.liczba_dysz_txt);
        liczba_dysz1_txt = findViewById(R.id.liczba_dysz1_txt);
        liczba_dysz2_txt = findViewById(R.id.liczba_dysz2_txt);
        final ToggleButton liczba_sekcji_txt = findViewById(R.id.liczba_sekcji_txt);
        final ConstraintLayout sekcja1 = findViewById(R.id.sekcjal1);
        final ConstraintLayout sekcja12 = findViewById(R.id.sekcjal12);
        final ConstraintLayout sekcja13 = findViewById(R.id.sekcjal13);
        final ConstraintLayout sekcja2 = findViewById(R.id.sekcjal2);
        final ConstraintLayout sekcja3 = findViewById(R.id.sekcjal3);
        final ConstraintLayout sekcja4 = findViewById(R.id.sekcjal4);
        final ConstraintLayout sekcja5 = findViewById(R.id.sekcjal5);
        final ConstraintLayout teejet = findViewById(R.id.sekcja_teejet);
        final ConstraintLayout arag = findViewById(R.id.sekcja_arag);
        final ConstraintLayout albuz = findViewById(R.id.sekcja_albuz);
        final ConstraintLayout lechler = findViewById(R.id.sekcja_lechler);


        liczba_dysz_txt.setText(sharedPreferences.getString("liczba_dysz", ""));
        liczba_dysz1_txt.setText(sharedPreferences.getString("liczba_dysz1", ""));
        liczba_dysz2_txt.setText(sharedPreferences.getString("liczba_dysz2", ""));
        parametry_sekcji1_txt.setText(sharedPreferences.getString("przepływ_górnej", ""));
        parametry_sekcji2_txt.setText(sharedPreferences.getString("przepływ_dolnej", ""));
        parametry_sekcji11_txt.setText(sharedPreferences.getString("przepływ", ""));
        liczba_sekcji_txt.setChecked(sharedPreferences.getBoolean("liczba_sekcji", true));

        liczba_sekcji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sekcja1.setVisibility(View.GONE);
                sekcja2.setVisibility(View.VISIBLE);
            }
        });

        parametry_sekcji_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sekcja1.setVisibility(View.GONE);
                boolean checked = liczba_sekcji_txt.isChecked();
                if (!checked) {
                    sekcja3.setVisibility(View.VISIBLE);
                } else {
                    sekcja4.setVisibility(View.VISIBLE);
                }
            }
        });

        parametry_sekcji_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sekcja1.setVisibility(View.GONE);
                sekcja5.setVisibility(View.VISIBLE);
            }
        });

        liczba_dysz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sekcja1.setVisibility(View.GONE);
                boolean checked = liczba_sekcji_txt.isChecked();
                if (!checked) {
                    sekcja12.setVisibility(View.VISIBLE);
                } else {
                    sekcja13.setVisibility(View.VISIBLE);
                }
            }
        });

        teejet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sekcja5.setVisibility(View.GONE);
                teejet.setVisibility(View.VISIBLE);
            }
        });
        arag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sekcja5.setVisibility(View.GONE);
                arag.setVisibility(View.VISIBLE);
            }
        });
        albuz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sekcja5.setVisibility(View.GONE);
                albuz.setVisibility(View.VISIBLE);
            }
        });
        lechler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sekcja5.setVisibility(View.GONE);
                lechler.setVisibility(View.VISIBLE);
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

        powrót12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sekcja12.setVisibility(View.GONE);
                sekcja1.setVisibility(View.VISIBLE);
            }
        });

        powrót13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sekcja13.setVisibility(View.GONE);
                sekcja1.setVisibility(View.VISIBLE);
            }
        });

        powrót2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sekcja2.setVisibility(View.GONE);
                sekcja1.setVisibility(View.VISIBLE);
            }
        });

        powrót3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sekcja3.setVisibility(View.GONE);
                sekcja1.setVisibility(View.VISIBLE);
            }
        });

        powrót4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sekcja4.setVisibility(View.GONE);
                sekcja1.setVisibility(View.VISIBLE);
            }
        });

        powrót5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sekcja5.setVisibility(View.GONE);
                sekcja1.setVisibility(View.VISIBLE);
            }
        });

        zapisz12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String liczba_dysz = liczba_dysz_txt.getText().toString().trim();
                editor.putString("liczba_dysz", liczba_dysz);
                editor.putBoolean("synchro_sekcje", true);
                editor.apply(); // commit changes
                Toast.makeText(getApplicationContext(), "Zapisano",
                        Toast.LENGTH_SHORT).show();
            }
        });

        zapisz13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String liczba_dysz1 = liczba_dysz1_txt.getText().toString().trim();
                String liczba_dysz2 = liczba_dysz2_txt.getText().toString().trim();
                editor.putString("liczba_dysz1", liczba_dysz1);
                editor.putString("liczba_dysz2", liczba_dysz2);
                editor.putBoolean("synchro_sekcje", true);
                editor.apply(); // commit changes
                Toast.makeText(getApplicationContext(), "Zapisano",
                        Toast.LENGTH_SHORT).show();
            }
        });

        zapisz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean liczba_sekcji = liczba_sekcji_txt.isChecked();
                editor.putBoolean("liczba_sekcji", liczba_sekcji);
                editor.putBoolean("synchro_sekcje", true);
                editor.apply(); // commit changes
                Toast.makeText(getApplicationContext(), "Zapisano",
                        Toast.LENGTH_SHORT).show();
            }
        });

        zapisz3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String przepływ = parametry_sekcji11_txt.getText().toString().trim();
                editor.putString("przepływ", przepływ);
                editor.putBoolean("synchro_sekcje", true);
                editor.apply(); // commit changes
                Toast.makeText(getApplicationContext(), "Zapisano",
                        Toast.LENGTH_SHORT).show();
            }
        });

        zapisz4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String przepływ_górnej = parametry_sekcji1_txt.getText().toString().trim();
                editor.putString("przepływ_górnej", przepływ_górnej);
                String przepływ_dolnej = parametry_sekcji2_txt.getText().toString().trim();
                editor.putString("przepływ_dolnej", przepływ_dolnej);
                editor.putBoolean("synchro_sekcje", true);
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        parametry_sekcji11_txt.removeTextChangedListener(this);


        parametry_sekcji11_txt.setText(String.format("4.2f", s));
        parametry_sekcji11_txt.setSelection(parametry_sekcji11_txt.getText().length());

        parametry_sekcji11_txt.addTextChangedListener(this);
    }
}


