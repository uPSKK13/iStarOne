package com.example.upskk_automatyka.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.material.slider.Slider;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

import static android.graphics.Color.rgb;


public class Zaawansowane extends Activity {

    LineChart chart;
    LineData data;
    ArrayList<Entry> values = new ArrayList<>();
    LineDataSet set1;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    float[] sliders_value = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUI();
        setContentView(R.layout.activity_special);

        sharedPreferences = getSharedPreferences("Main", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        final Button equalizer= findViewById(R.id.equalizer);
        final Button speed = findViewById(R.id.filtr_speed);
        final Button pressure = findViewById(R.id.filtr_pressure);
        final Button powrot = findViewById(R.id.powrot1);
        final Button powrót2 = findViewById(R.id.powrot2);
        final Button powrót3 = findViewById(R.id.powrot3);
        final Button powrót4 = findViewById(R.id.powrot4);
        final Button zapisz2 = findViewById(R.id.zapisz2);
        final Button zapisz3 = findViewById(R.id.zapisz3);
        final Button zapisz4 = findViewById(R.id.zapisz4);

        EditText measurement1 = findViewById(R.id.measurement1);
        EditText expectation1 = findViewById(R.id.expectation1);
        EditText variance1 = findViewById(R.id.variance1);
        EditText measurement2 = findViewById(R.id.measurement2);
        EditText expectation2 = findViewById(R.id.expectation2);
        EditText variance2 = findViewById(R.id.variance2);
        chart = findViewById(R.id.chart1);

        Slider slider1 = findViewById(R.id.slider1);
        Slider slider2 = findViewById(R.id.slider2);
        Slider slider3 = findViewById(R.id.slider3);
        Slider slider4 = findViewById(R.id.slider4);
        Slider slider5 = findViewById(R.id.slider5);
        Slider slider6 = findViewById(R.id.slider6);
        Slider slider7 = findViewById(R.id.slider7);
        Slider slider8 = findViewById(R.id.slider8);
        Slider slider9 = findViewById(R.id.slider9);
        Slider slider10 = findViewById(R.id.slider10);
        Slider slider11 = findViewById(R.id.slider11);
        Slider slider12 = findViewById(R.id.slider12);
        Slider slider13 = findViewById(R.id.slider13);
        Slider slider14 = findViewById(R.id.slider14);
        Slider slider15 = findViewById(R.id.slider15);
        Slider slider16 = findViewById(R.id.slider16);
        Slider slider17 = findViewById(R.id.slider17);
        Slider slider18 = findViewById(R.id.slider18);
        Slider slider19 = findViewById(R.id.slider19);
        Slider slider20 = findViewById(R.id.slider20);



        final ConstraintLayout menu = findViewById(R.id.special1);
        final ConstraintLayout mapa = findViewById(R.id.special2);
        final ConstraintLayout filtr_speed = findViewById(R.id.special3);
        final ConstraintLayout filtr_pressure = findViewById(R.id.special4);

        startValues();
        set1 = new LineDataSet(values, "E-Valve Map");
        data = getData();
        setupChart(chart, data);

        powrot.setOnClickListener(v -> {
            Context context = getApplicationContext();
            Intent intent = new Intent(context, Menu.class);
            startActivity(intent);
        });

        powrót2.setOnClickListener(v -> {
            mapa.setVisibility(View.GONE);
            menu.setVisibility(View.VISIBLE);
        });

        powrót3.setOnClickListener(v -> {
            filtr_speed.setVisibility(View.GONE);
            menu.setVisibility(View.VISIBLE);
        });

        powrót4.setOnClickListener(v -> {
            filtr_pressure.setVisibility(View.GONE);
            menu.setVisibility(View.VISIBLE);
        });

        equalizer.setOnClickListener(v -> {
            slider1.setValue(sharedPreferences.getFloat("slider1",0));
            slider2.setValue(sharedPreferences.getFloat("slider2",0));
            slider3.setValue(sharedPreferences.getFloat("slider3",0));
            slider4.setValue(sharedPreferences.getFloat("slider4",0));
            slider5.setValue(sharedPreferences.getFloat("slider5",0));
            slider6.setValue(sharedPreferences.getFloat("slider6",0));
            slider7.setValue(sharedPreferences.getFloat("slider7",0));
            slider8.setValue(sharedPreferences.getFloat("slider8",0));
            slider9.setValue(sharedPreferences.getFloat("slider9",0));
            slider10.setValue(sharedPreferences.getFloat("slider10",0));
            slider11.setValue(sharedPreferences.getFloat("slider11",0));
            slider12.setValue(sharedPreferences.getFloat("slider12",0));
            slider13.setValue(sharedPreferences.getFloat("slider13",0));
            slider14.setValue(sharedPreferences.getFloat("slider14",0));
            slider15.setValue(sharedPreferences.getFloat("slider15",0));
            slider16.setValue(sharedPreferences.getFloat("slider16",0));
            slider17.setValue(sharedPreferences.getFloat("slider17",0));
            slider18.setValue(sharedPreferences.getFloat("slider18",0));
            slider19.setValue(sharedPreferences.getFloat("slider19",0));
            slider20.setValue(sharedPreferences.getFloat("slider20",0));
            menu.setVisibility(View.GONE);
            mapa.setVisibility(View.VISIBLE);
        });
        speed.setOnClickListener(v -> {
            measurement1.setText(sharedPreferences.getString("measurement1","1"));
            expectation1.setText(sharedPreferences.getString("expectation1","1"));
            variance1.setText(sharedPreferences.getString("variance1","0.01"));
            menu.setVisibility(View.GONE);
            filtr_speed.setVisibility(View.VISIBLE);
        });
        pressure.setOnClickListener(v -> {
            measurement2.setText(sharedPreferences.getString("measurement2","1"));
            expectation2.setText(sharedPreferences.getString("expectation2","1"));
            variance2.setText(sharedPreferences.getString("variance2","0.01"));
            menu.setVisibility(View.GONE);
            filtr_pressure.setVisibility(View.VISIBLE);
        });
        powrot.setOnClickListener(v -> {
            Context context = getApplicationContext();
            Intent intent = new Intent(context, Menu.class);
            startActivity(intent);
        });

        zapisz2.setOnClickListener(v -> {

            editor.putFloat("slider1", sliders_value[0]);
            editor.putFloat("slider2", sliders_value[1]);
            editor.putFloat("slider3", sliders_value[2]);
            editor.putFloat("slider4", sliders_value[3]);
            editor.putFloat("slider5", sliders_value[4]);
            editor.putFloat("slider6", sliders_value[5]);
            editor.putFloat("slider7", sliders_value[6]);
            editor.putFloat("slider8", sliders_value[7]);
            editor.putFloat("slider9", sliders_value[8]);
            editor.putFloat("slider10", sliders_value[9]);
            editor.putFloat("slider11", sliders_value[10]);
            editor.putFloat("slider12", sliders_value[11]);
            editor.putFloat("slider13", sliders_value[12]);
            editor.putFloat("slider14", sliders_value[13]);
            editor.putFloat("slider15", sliders_value[14]);
            editor.putFloat("slider16", sliders_value[15]);
            editor.putFloat("slider17", sliders_value[16]);
            editor.putFloat("slider18", sliders_value[17]);
            editor.putFloat("slider19", sliders_value[18]);
            editor.putFloat("slider20", sliders_value[19]);
            editor.putBoolean("synchro_advanced1",true);

            editor.apply(); // commit changes
            Toast.makeText(getApplicationContext(), "Zapisywanie...",
                    Toast.LENGTH_SHORT).show();
        });

        zapisz3.setOnClickListener(v -> {
            String m1 = measurement1.getText().toString().trim();
            String e1 = expectation1.getText().toString().trim();
            String v1 = variance1.getText().toString().trim();
            editor.putString("measurement1", m1);
            editor.putString("expectation1", e1);
            editor.putString("variance1", v1);
            editor.putBoolean("synchro_advanced2",true);

            editor.apply(); // commit changes
            Toast.makeText(getApplicationContext(), "Zapisywanie...",
                    Toast.LENGTH_SHORT).show();
        });

        zapisz4 .setOnClickListener(v -> {
            String m2 = measurement2.getText().toString().trim();
            String e2 = expectation2.getText().toString().trim();
            String v2 = variance2.getText().toString().trim();
            editor.putString("measurement2", m2);
            editor.putString("expectation2", e2);
            editor.putString("variance2", v2);
            editor.putBoolean("synchro_advanced2",true);

            editor.apply(); // commit changes
            Toast.makeText(getApplicationContext(), "Zapisywanie...",
                    Toast.LENGTH_SHORT).show();
        });


        slider1.addOnChangeListener((slider, value, fromUser) -> {
            sliders_value[0]=value;
            set1.removeEntry(0);
            set1.addEntryOrdered(new Entry(0, value));
            data.notifyDataChanged();
            data.notifyDataChanged();
            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate(); // refresh
        });
        slider2.addOnChangeListener((slider, value, fromUser) -> {
            sliders_value[1]=value;
            set1.removeEntry(1);
            set1.addEntryOrdered(new Entry(1, value));
            data.notifyDataChanged();
            data.notifyDataChanged();
            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate(); // refresh
        });
        slider3.addOnChangeListener((slider, value, fromUser) -> {
            sliders_value[2]=value;
            set1.removeEntry(2);
            set1.addEntryOrdered(new Entry(2, value));
            data.notifyDataChanged();
            data.notifyDataChanged();
            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate(); // refresh
        });
        slider4.addOnChangeListener((slider, value, fromUser) -> {
            sliders_value[3]=value;
            set1.removeEntry(3);
            set1.addEntryOrdered(new Entry(3, value));
            data.notifyDataChanged();
            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate(); // refresh
        });
        slider5.addOnChangeListener((slider, value, fromUser) -> {
            sliders_value[4]=value;
            set1.removeEntry(4);
            set1.addEntryOrdered(new Entry(4, value));
            data.notifyDataChanged();
            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate(); // refresh
        });
        slider6.addOnChangeListener((slider, value, fromUser) -> {
            sliders_value[5]=value;
            set1.removeEntry(5);
            set1.addEntryOrdered(new Entry(5, value));
            data.notifyDataChanged();
            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate(); // refresh
        });
        slider7.addOnChangeListener((slider, value, fromUser) -> {
            sliders_value[6]=value;
            set1.removeEntry(6);
            set1.addEntryOrdered(new Entry(6, value));
            data.notifyDataChanged();
            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate(); // refresh
        });
        slider8.addOnChangeListener((slider, value, fromUser) -> {
            sliders_value[7]=value;
            set1.removeEntry(7);
            set1.addEntryOrdered(new Entry(7, value));
            data.notifyDataChanged();
            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate(); // refresh
        });
        slider9.addOnChangeListener((slider, value, fromUser) -> {
            sliders_value[8]=value;
            set1.removeEntry(8);
            set1.addEntryOrdered(new Entry(8, value));
            data.notifyDataChanged();
            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate(); // refresh
        });
        slider10.addOnChangeListener((slider, value, fromUser) -> {
            sliders_value[9]=value;
            set1.removeEntry(9);
            set1.addEntryOrdered(new Entry(9, value));
            data.notifyDataChanged();
            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate(); // refresh
        });
        slider11.addOnChangeListener((slider, value, fromUser) -> {
            sliders_value[10]=value;
            set1.removeEntry(10);
            set1.addEntryOrdered(new Entry(10, value));
            data.notifyDataChanged();
            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate(); // refresh
        });
        slider12.addOnChangeListener((slider, value, fromUser) -> {
            sliders_value[11]=value;
            set1.removeEntry(11);
            set1.addEntryOrdered(new Entry(11, value));
            data.notifyDataChanged();
            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate(); // refresh
        });
        slider13.addOnChangeListener((slider, value, fromUser) -> {
            sliders_value[12]=value;
            set1.removeEntry(12);
            set1.addEntryOrdered(new Entry(12, value));
            data.notifyDataChanged();
            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate(); // refresh
        });
        slider14.addOnChangeListener((slider, value, fromUser) -> {
            sliders_value[13]=value;
            set1.removeEntry(13);
            set1.addEntryOrdered(new Entry(13, value));
            data.notifyDataChanged();
            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate(); // refresh
        });
        slider15.addOnChangeListener((slider, value, fromUser) -> {
            sliders_value[14]=value;
            set1.removeEntry(14);
            set1.addEntryOrdered(new Entry(14, value));
            data.notifyDataChanged();
            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate(); // refresh
        });
        slider16.addOnChangeListener((slider, value, fromUser) -> {
            sliders_value[15]=value;
            set1.removeEntry(15);
            set1.addEntryOrdered(new Entry(15, value));
            data.notifyDataChanged();
            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate(); // refresh
        });
        slider17.addOnChangeListener((slider, value, fromUser) -> {
            sliders_value[16]=value;
            set1.removeEntry(16);
            set1.addEntryOrdered(new Entry(16, value));
            data.notifyDataChanged();
            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate(); // refresh
        });
        slider18.addOnChangeListener((slider, value, fromUser) -> {
            sliders_value[17]=value;
            set1.removeEntry(17);
            set1.addEntryOrdered(new Entry(17, value));
            data.notifyDataChanged();
            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate(); // refresh
        });
        slider19.addOnChangeListener((slider, value, fromUser) -> {
            sliders_value[18]=value;
            set1.removeEntry(18);
            set1.addEntryOrdered(new Entry(18, value));
            data.notifyDataChanged();
            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate(); // refresh
        });
        slider20.addOnChangeListener((slider, value, fromUser) -> {
            sliders_value[19]=value;
            set1.removeEntry(19);
            set1.addEntryOrdered(new Entry(19, value));
            data.notifyDataChanged();
            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate(); // refresh
        });



    }



    private void setupChart(LineChart chart, LineData data) {

        ((LineDataSet) data.getDataSetByIndex(0)).setCircleHoleColor(rgb(209, 44, 44));

        // no description text
        chart.getDescription().setEnabled(false);

        // enable / disable grid background
        chart.setDrawGridBackground(false);
//        chart.getRenderer().getGridPaint().setGridColor(Color.WHITE & 0x70FFFFFF);

        // enable touch gestures
        chart.setTouchEnabled(false);

        // enable scaling and dragging
        chart.setDragEnabled(false);
        chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setBackgroundColor(rgb(0,60,250));

        // set custom chart offsets (automatic offset calculation is hereby disabled)
        //chart.setViewPortOffsets(10, 10, 10, 10);


        // add data
        chart.setData(data);

        // get the legend (only possible after setting data)
        Description description = new Description(); description.setText(""); chart.setDescription(description);
        chart.getAxisLeft().setDrawLabels(false);
        chart.getAxisRight().setDrawLabels(false);
        chart.getXAxis().setDrawLabels(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getXAxis().setDrawGridLines(false);

        chart.getLegend().setEnabled(false);

        chart.getAxisLeft().setSpaceTop(20);
        chart.getAxisLeft().setSpaceBottom(10);


        // animate calls invalidate()...
        chart.invalidate();
    }

    private LineData getData() {

        // create a dataset and give it a type
        // set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        set1.setLineWidth(1.75f);
        set1.setCircleRadius(5f);
        set1.setCircleHoleRadius(2.5f);
        set1.setColor(Color.WHITE);
        set1.setCircleColor(Color.WHITE);
        set1.setHighLightColor(Color.WHITE);
        set1.setDrawValues(true);

        // create a data object with the data sets
        return new LineData(set1);
    }

    private void startValues() {
        values.add(new Entry(0, sharedPreferences.getFloat("slider1",0)));
        values.add(new Entry(1, sharedPreferences.getFloat("slider2",0)));
        values.add(new Entry(2, sharedPreferences.getFloat("slider3",0)));
        values.add(new Entry(3, sharedPreferences.getFloat("slider4",0)));
        values.add(new Entry(4, sharedPreferences.getFloat("slider5",0)));
        values.add(new Entry(5, sharedPreferences.getFloat("slider6",0)));
        values.add(new Entry(6, sharedPreferences.getFloat("slider7",0)));
        values.add(new Entry(7, sharedPreferences.getFloat("slider8",0)));
        values.add(new Entry(8, sharedPreferences.getFloat("slider9",0)));
        values.add(new Entry(9, sharedPreferences.getFloat("slider10",0)));
        values.add(new Entry(10, sharedPreferences.getFloat("slider11",0)));
        values.add(new Entry(11, sharedPreferences.getFloat("slider12",0)));
        values.add(new Entry(12, sharedPreferences.getFloat("slider13",0)));
        values.add(new Entry(13, sharedPreferences.getFloat("slider14",0)));
        values.add(new Entry(14, sharedPreferences.getFloat("slider15",0)));
        values.add(new Entry(15, sharedPreferences.getFloat("slider16",0)));
        values.add(new Entry(16, sharedPreferences.getFloat("slider17",0)));
        values.add(new Entry(17, sharedPreferences.getFloat("slider18",0)));
        values.add(new Entry(18, sharedPreferences.getFloat("slider19",0)));
        values.add(new Entry(19, sharedPreferences.getFloat("slider20",0)));
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
