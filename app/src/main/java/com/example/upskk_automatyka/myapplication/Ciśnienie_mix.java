package com.example.upskk_automatyka.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.szagurskii.patternedtextwatcher.PatternedTextWatcher;

public class Ciśnienie_mix extends Activity {

    private Button powrót, zapisz;
    EditText ciśnienie_mieszania_txt;

    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUI();
        setContentView(R.layout.activity_cisnienie_mix);

        sharedPreferences = getSharedPreferences("Main",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        powrót = findViewById(R.id.powrot);
        zapisz = findViewById(R.id.zapisz);
        ciśnienie_mieszania_txt = findViewById(R.id.ciśnienie_mieszania_txt);

        ciśnienie_mieszania_txt.setText(sharedPreferences.getString("ciśnienie_mix",""));
        ciśnienie_mieszania_txt.addTextChangedListener(new PatternedTextWatcher("#.#"));


        powrót.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Intent intent = new Intent(context, Menu.class);
                startActivity(intent);
            }
        });

        zapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ciśnienie = ciśnienie_mieszania_txt.getText().toString().trim();
                editor.putString("ciśnienie_mix", ciśnienie);
                editor.putBoolean("ciśnienie_mix_synchro", true);
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
