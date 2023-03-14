package com.example.upskk_automatyka.myapplication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import androidx.core.content.ContextCompat;
import androidx.appcompat.widget.AppCompatTextView;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Locale;
import java.util.Objects;

import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;
import static java.lang.Integer.parseInt;


public class MainActivity extends Activity implements View.OnClickListener{

    private Button sekcja1, sekcja2, sekcja3, sekcja4, setup, master, auto_manual, led, mixed, dodaj, odejmij;
    private TextView txtBattery, txtPomoc;
    private ImageView wifi, fan;
    private AppCompatTextView txtPressure, txtSpeed;

    String  ciśnienie_mix, liczba_dysz, liczba_dysz1, liczba_dysz2;
    String prędkość_zawracania, impulsy, max_czujnik, przepływ, przepływ_dolnej, przepływ_górnej;
    int s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20;
    String measurement1, measurement2, expectation1, expectation2, variance1, variance2;
    int wydatek_cieczy, pressure_manual;
    float rozstawa;
    float pressure_esp = 0, speed_esp = 0;
    boolean kalibracja_czuj = false, synchro_prędkość = false, synchro_ciśnienie_mix, synchro_czujnik = false, synchro_program = false, synchro_advanced1, synchro_advanced2, synchro_sekcje = true;
    boolean wifi_case = true, b1, b2, b3, b4, led12, master_b, auto_manual_b, mixed_b, liczba_sekcji;
    String parameterValue = "0";


    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    Handler handlerConnections;
    HandlerThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUI();
        setContentView(R.layout.activity_main);


        sharedPreferences = getSharedPreferences("Main", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        sekcja1 = findViewById(R.id.sekcja1);
        sekcja2 = findViewById(R.id.sekcja2);
        sekcja3 = findViewById(R.id.sekcja3);     //przyciski
        sekcja4 = findViewById(R.id.sekcja4);
        master = findViewById(R.id.master);
        setup = findViewById(R.id.setup);
        auto_manual = findViewById(R.id.auto);
        mixed = findViewById(R.id.mixed);
        wifi = findViewById(R.id.wifi_img);
        led = findViewById(R.id.led);
        dodaj = findViewById(R.id.dodaj);
        odejmij = findViewById(R.id.odejmij);
        fan = findViewById(R.id.fan);


        txtPressure = findViewById(R.id.pressure);  //wyswietlanie konfig
        txtSpeed = findViewById(R.id.speed);
        txtPomoc = findViewById(R.id.help);
        txtBattery = findViewById(R.id.battery);

        sekcja1.setOnClickListener(this);
        sekcja2.setOnClickListener(this);
        sekcja3.setOnClickListener(this);   //reakcja na przyciski
        sekcja4.setOnClickListener(this);
        master.setOnClickListener(this);
        setup.setOnClickListener(this);
        auto_manual.setOnClickListener(this);
        dodaj.setOnClickListener(this);
        odejmij.setOnClickListener(this);
        led.setOnClickListener(this);
        mixed.setOnClickListener(this);
        

        Toast.makeText(getApplicationContext(), "Synchronizacja...",
                Toast.LENGTH_SHORT).show();                                  //informacja przy starcie aplikacji
    }

    @Override
    protected void onStart() {
        super.onStart();

        //SYNCHRO PREFERENCJI

        ciśnienie_mix = sharedPreferences.getString("ciśnienie_mix", "5");
        prędkość_zawracania = sharedPreferences.getString("predkosc_zawracania", "14");
        impulsy = sharedPreferences.getString("impulsy", "200");
        max_czujnik = sharedPreferences.getString("max_czujnik", "60");
        liczba_sekcji = sharedPreferences.getBoolean("liczba_sekcji", true);
        liczba_dysz = sharedPreferences.getString("liczba_dysz", "");
        liczba_dysz1 = sharedPreferences.getString("liczba_dysz1", "");
        liczba_dysz2 = sharedPreferences.getString("liczba_dysz2", "");

        przepływ = sharedPreferences.getString("przepływ", "");
        przepływ_dolnej = sharedPreferences.getString("przepływ_dolnej", "");
        przepływ_górnej = sharedPreferences.getString("przepływ_górnej", "");
        try{
            wydatek_cieczy = parseInt(Objects.requireNonNull(sharedPreferences.getString("wydatek_cieczy", "1000")));
        } catch(NumberFormatException ex){ // handle your exception
            wydatek_cieczy = 1000;
        }
        try{
            rozstawa = Float.parseFloat(Objects.requireNonNull(sharedPreferences.getString("rozstawa", "3")));
        } catch(NumberFormatException ex){ // handle your exception
            rozstawa = 3;
        }
        kalibracja_czuj = sharedPreferences.getBoolean("kalibracja_ciśnienia", false);
        //kalibracja_ciś = sharedPreferences.getBoolean("kalibracja_ciśnienia", false);

        synchro_ciśnienie_mix = sharedPreferences.getBoolean("ciśnienie_mix_synchro", false);
        synchro_czujnik = sharedPreferences.getBoolean("synchro_czujnik", false);
        synchro_program = sharedPreferences.getBoolean("synchro_program", false);
        synchro_prędkość = sharedPreferences.getBoolean("synchro_prędkość", false);
        synchro_sekcje = sharedPreferences.getBoolean("synchro_sekcje", false);

        measurement1 = sharedPreferences.getString("measurement1", "1");
        expectation1 = sharedPreferences.getString("expectation1", "1");
        variance1 = sharedPreferences.getString("variance1", "0.01");
        measurement2 = sharedPreferences.getString("measurement2", "1");
        expectation2 = sharedPreferences.getString("expectation2", "1");
        variance2 = sharedPreferences.getString("variance2", "0.01");

        s1= (int)sharedPreferences.getFloat("slider1",0); s2= (int) sharedPreferences.getFloat("slider2",0); s3= (int)sharedPreferences.getFloat("slider3",0); s4=(int) sharedPreferences.getFloat("slider4",0); s5= (int)sharedPreferences.getFloat("slider5",0);
        s6= (int)sharedPreferences.getFloat("slider6",0); s7= (int)sharedPreferences.getFloat("slider7",0); s8= (int)sharedPreferences.getFloat("slider8",0); s9= (int)sharedPreferences.getFloat("slider9",0); s10= (int)sharedPreferences.getFloat("slider10",0);
        s11= (int)sharedPreferences.getFloat("slider11",0); s12= (int)sharedPreferences.getFloat("slider12",0); s13=(int) sharedPreferences.getFloat("slider13",0); s14= (int)sharedPreferences.getFloat("slider14",0); s15= (int)sharedPreferences.getFloat("slider15",0);
        s16= (int)sharedPreferences.getFloat("slider16",0); s17= (int)sharedPreferences.getFloat("slider17",0); s18= (int)sharedPreferences.getFloat("slider18",0); s19= (int)sharedPreferences.getFloat("slider19",0); s20= (int)sharedPreferences.getFloat("slider20",0);

        synchro_advanced1 = sharedPreferences.getBoolean("synchro_advanced1",false);
        synchro_advanced2 = sharedPreferences.getBoolean("synchro_advanced2",false);

        // registerReceiver(wifiStateReceiver, new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));
        registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        if(!liczba_sekcji) {
            sekcja2.setVisibility(View.INVISIBLE);
            sekcja4.setVisibility(View.INVISIBLE);    //2 sekcje
            b2=false; b4=false;
        } else {
            sekcja2.setVisibility(View.VISIBLE);
            sekcja4.setVisibility(View.VISIBLE);  //4 sekcje
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        handlerConnections.removeCallbacks(aktualizacja);
        thread.interrupt();
    }

    @Override
    public void onResume() {
        super.onResume();

        StartThread();
        Context context = getApplicationContext();
        Intent intent = new Intent(context, Menu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    @Override
    public void onStop() {
        super.onStop();

        //unregisterReceiver(wifiStateReceiver);
        unregisterReceiver(batteryReceiver);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                         //dropdown.setFocusable(false);
                        //dropdown.setOutsideTouchable(true);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == sekcja1.getId()) {  //kolejne przyciski
            if(!mixed_b){b1 = !b1; pozwolenie();}
        } else if (view.getId() == sekcja2.getId()) {
            if(!mixed_b){b2 = !b2; pozwolenie();}
        } else if (view.getId() == sekcja3.getId()) {
            if(!mixed_b){b3 = !b3; pozwolenie();}
        } else if (view.getId() == sekcja4.getId()) {
            if(!mixed_b){b4 = !b4; pozwolenie();}                     //zmiana stanu przycisku i wywołanie funkcji zmiany grafiki (pozwolenie)
        } else if (view.getId() == master.getId()) {
            if(!mixed_b){master_b = !master_b; pozwolenie();}
        } else if (view.getId() == auto_manual.getId()) {
            automanual();
            auto_manual_b = !auto_manual_b;
        } else if (view.getId() == mixed.getId()) {
            mixed_b = !mixed_b;
            mixed();
            //Glide.with(this)
                  //  .load("file:///android_asset/fan.gif")
                   // .into(fan);
        } else if (view.getId() == led.getId()) {
            led();
            led12 = !led12;
        }else if (view.getId() == dodaj.getId()) {
            if(auto_manual_b){
                wydatek_cieczy=wydatek_cieczy+50;
                synchro_program=true;
            }
        } else if (view.getId() == odejmij.getId()) {
            if(auto_manual_b){
                wydatek_cieczy=wydatek_cieczy-50;
                synchro_program=true;
            }
        }else if (view.getId() == setup.getId()) {               //włączenie menu aplikacji, nie zatrzymuje pracy urządzenia, ale zaprzestaje kontroli
            Context context = getApplicationContext();
            Intent intent = new Intent(context, Menu.class);
            startActivity(intent);
        }if(!master_b) {
            parameterValue = "false"+"@"+"false"+"#"+"false"+"$"+"false"+"%"+mixed_b+"&"+led12+"{"+auto_manual_b+"}"+dodaj.isPressed()+"="+odejmij.isPressed()+"data";
        } else {
            parameterValue = b1+"@"+b2+"#"+b3+"$"+b4+"%"+mixed_b+"&"+led12+"{"+auto_manual_b+"}"+dodaj.isPressed()+"="+odejmij.isPressed()+"data";
        }

    }


    private void automanual() {
        if (!auto_manual_b) {
            auto_manual.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_auto_red));
        } else {                                                                                                                //zmiana trybu pracy
            auto_manual.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_auto_grey));
        }
    }

    private void mixed() {
        if (mixed_b) {
            mixed.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_mixed_on));
            b1=false; b2=false; b3=false; b4=false; master_b=false;                                                     //wyłącza sekcje i uruchamia mieszanie
            pozwolenie();
        } else {
            mixed.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_mixed_off));
        }
    }

    private void led() {
        ImageView img1 = findViewById(R.id.led1);
        ImageView img2 = findViewById(R.id.led2);
        if (!led12) {
            img1.setVisibility(View.VISIBLE);
            img2.setVisibility(View.VISIBLE);
            led.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_led_on));
        } else {
            img1.setVisibility(View.GONE);
            img2.setVisibility(View.GONE);
            led.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_led_off));
        }
    }

    private void pozwolenie() {
        ImageView img1 = findViewById(R.id.sprayer1);
        ImageView img2 = findViewById(R.id.sprayer2);
        ImageView img3 = findViewById(R.id.sprayer3);
        ImageView img4 = findViewById(R.id.sprayer4);


        if (master_b) {
            master.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_master_green));
            if (b1) {
                if(liczba_sekcji) {
                img1.setVisibility(View.VISIBLE);
                sekcja1.setTextColor(Color.rgb(33, 196, 58));
              } else {
                img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.VISIBLE);
                sekcja1.setTextColor(Color.rgb(33, 196, 58));
            }
            } else {
                if(liczba_sekcji) {
                    img1.setVisibility(View.GONE);
                    sekcja1.setTextColor(WHITE);
                } else {
                    img1.setVisibility(View.GONE);
                    img2.setVisibility(View.GONE);
                    sekcja1.setTextColor(WHITE);
                }
            }
            if(liczba_sekcji) {
                if (b2) {
                    img2.setVisibility(View.VISIBLE);
                    sekcja2.setTextColor(Color.rgb(33, 196, 58));
                } else {
                    img2.setVisibility(View.GONE);
                    sekcja2.setTextColor(WHITE);
                }
            }
            if (b3) {
                if(liczba_sekcji) {
                    img3.setVisibility(View.VISIBLE);
                    sekcja3.setTextColor(Color.rgb(33, 196, 58));
                } else {
                    img3.setVisibility(View.VISIBLE);
                    img4.setVisibility(View.VISIBLE);
                    sekcja3.setTextColor(Color.rgb(33, 196, 58));
                }
            } else {
                if(liczba_sekcji) {
                    img3.setVisibility(View.GONE);
                    sekcja3.setTextColor(WHITE);
                } else {
                    img3.setVisibility(View.GONE);
                    img4.setVisibility(View.GONE);
                    sekcja3.setTextColor(WHITE);
                }
            }
            if(liczba_sekcji) {
            if (b4) {
                img4.setVisibility(View.VISIBLE);
                sekcja4.setTextColor(Color.rgb(33, 196, 58));
            } else {
                img4.setVisibility(View.GONE);
                sekcja4.setTextColor(WHITE);
             }
            }
        } else {
            img1.setVisibility(View.GONE);
            img2.setVisibility(View.GONE);
            img3.setVisibility(View.GONE);
            img4.setVisibility(View.GONE);
            if (b1 || b2 || b3 || b4) {
                master.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_master_on));
            } else {
                master.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_master_off));
            }
            if(b1){  sekcja1.setTextColor(RED);  }
            else { sekcja1.setTextColor(WHITE); }
            if(b2){ sekcja2.setTextColor(RED); }
            else { sekcja2.setTextColor(WHITE); }
            if(b3){ sekcja3.setTextColor(RED); }
            else { sekcja3.setTextColor(WHITE);}
            if(b4){ sekcja4.setTextColor(RED);}
            else { sekcja4.setTextColor(WHITE);}
        }
    }


    private final Runnable aktualizacja = new Runnable() {
        @Override
        public void run() {

            //Handler handler = new Handler();

            //Możliwość użycia poprzedzających liczb do określania długości ciągu danych określonych parametrów zamiast rozdzielających symboli, opłacalne dla większej ilości parametrów

            handlerConnections.postDelayed(this, 100);
            //txtPomoc.setText("aktualizacja");
            if(parameterValue.equals("0")) {
                if (synchro_ciśnienie_mix) { //jesli True to zacznij synchronizowac user.java
                    parameterValue = ciśnienie_mix + "mieszanie";
                    synchro_ciśnienie_mix = false;
                    editor.putBoolean("ciśnienie_mix_synchro", false);
                    editor.apply();
                } else if (synchro_czujnik) { //jesli True to zacznij synchronizowac serwis.java
                    parameterValue = max_czujnik + "@" + kalibracja_czuj + "czujnik";
                    synchro_czujnik = false;
                    editor.putBoolean("synchro_czujnik", false);
                    editor.putBoolean("kalibracja_ciśnienia", false);
                    editor.apply();
                } else if (synchro_program) {  //jesli True to zacznij synchronizowac system.java
                    parameterValue = rozstawa + "@" + wydatek_cieczy + "program";
                    synchro_program = false;
                    editor.putBoolean("synchro_program", false);
                    editor.apply();
                } else if (synchro_prędkość) {  //jesli True to zacznij synchronizowac system.java
                    parameterValue = prędkość_zawracania + "@" + impulsy + "#" + "predkosc";
                    synchro_prędkość = false;
                    editor.putBoolean("synchro_prędkość", false);
                    editor.apply();
                } else if (synchro_sekcje) {  //jesli True to zacznij synchronizowac system.java
                    parameterValue = przepływ + "@" + przepływ_dolnej + "#" + przepływ_górnej + "$" + liczba_sekcji + "%" + liczba_dysz + "&" + liczba_dysz1 + "{" + liczba_dysz2 + "sekcje";
                    synchro_sekcje = false;
                    editor.putBoolean("synchro_sekcje", false);
                    editor.apply();
                } else if (synchro_advanced1) { //jesli True to zacznij synchronizowac zaawansowane.java
                    parameterValue = s1+"a"+s2+"b"+s3+"c"+s4+"d"+s5+"e"+s6+"f"+s7+"g"+s8+"h"+s9+"i"+s10+"j"+s11+"k"+s12+"l"+s13+"m"+s14+"n"+s15+"o"+s16+"p"+s17+"r"+s18+"s"+s19+"t"+s20+"advanced1";
                    synchro_advanced1 = false;
                    editor.putBoolean("synchro_advanced1", false);
                    editor.apply();
                } else if (synchro_advanced2) { //jesli True to zacznij synchronizowac zaawansowane.java
                    parameterValue = measurement1 + "@" + expectation1 + "#" + variance1 + "$" + measurement2 + "%" + expectation2 + "&" + variance2 + "advanced2";
                    synchro_advanced2 = false;
                    editor.putBoolean("synchro_advanced2", false);
                    editor.apply();
                }
            }

            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo;

            assert wifiManager != null;
            wifiInfo = wifiManager.getConnectionInfo();
            if (wifiInfo.getSupplicantState() == SupplicantState.COMPLETED) {
                String ssid = wifiInfo.getSSID();
                if(Objects.equals(ssid, '"' + "iStarOne_v.03" + '"')) {
                    if(!wifi_case){
                        Toast.makeText(getApplicationContext(), "Synchronizacja...",
                                Toast.LENGTH_SHORT).show();
                    }
                    if(!auto_manual_b) {
                        if(!master_b) {
                            parameterValue = "false"+"@"+"false"+"#"+"false"+"$"+"false"+"%"+mixed_b+"&"+led12+"{"+false+"}"+dodaj.isPressed()+"="+odejmij.isPressed()+"data";
                        } else {
                            parameterValue = b1+"@"+b2+"#"+b3+"$"+b4+"%"+mixed_b+"&"+led12+"{"+false+"}"+dodaj.isPressed()+"="+odejmij.isPressed()+"data";
                        }
                    }
                    sendMessage(parameterValue);
                    wifi_case=true;
                    //wifi.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.wifi_on));
                }
                else{
                    wifi_case=false;
                    pressure_esp=0;
                    speed_esp=0;
                    //wifi.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.wifi_off));
                }
            }else {
                wifi_case=false;
                pressure_esp=0;
                speed_esp=0;
                //wifi.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.wifi_off));
            }

             runOnUiThread(() -> {
                 if(wifi_case) {
                     wifi.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.wifi_on));
                 } else {
                     wifi.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.wifi_off));         //Rysowanie na main threadzie
                 }
                 txtPressure.setText(String.format(Locale.US,"%2.1f",pressure_esp));
                 txtPomoc.setText(getString(R.string.helpera, wydatek_cieczy, rozstawa));
                 txtSpeed.setText(String.format(Locale.US,"%2.1f",speed_esp));

             });

            parameterValue = "0";
            pressure_manual = 0;

        }

    };

    public void StartThread() {
        thread = new HandlerThread("ActualizationThread");
        thread.start();
        handlerConnections = new Handler(thread.getLooper());
        handlerConnections.post(aktualizacja);
    }

    private void sendMessage(final String message) {

        final Handler handler = new Handler();
        Thread thread2 = new Thread(new Runnable() {

            String stringData;

            @Override
            public void run() {

                try (DatagramSocket ds = new DatagramSocket()) {   //wysyłanie danych

                    InetAddress serverAddr = InetAddress.getByName("192.168.0.1");
                    DatagramPacket dp;
                    dp = new DatagramPacket(message.getBytes(), message.length(), serverAddr, 3000);
                    ds.send(dp);

                    byte[] lMsg = new byte[1000];
                    dp = new DatagramPacket(lMsg, lMsg.length);
                    ds.receive(dp);
                    stringData = new String(lMsg, 0, dp.getLength());

                } catch (Exception e) {
                    e.printStackTrace();
                }

                handler.post(() -> {   //odbiór danych
                    try {
                        if (stringData.trim().length() != 0) {
                            if (stringData.contains("@") && stringData.contains("#") && stringData.contains("$")) {
                                speed_esp = Float.parseFloat(stringData.substring(stringData.indexOf("@") + 1, stringData.indexOf("#")).trim());
                                pressure_esp= Float.parseFloat(stringData.substring(stringData.indexOf("#") + 1, stringData.indexOf("$")).trim());
                            }
                        }
                    }  catch (NullPointerException e) {
                        e.printStackTrace();
                }
                });
            }
        });
        thread2.start();
    }

    private final BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @SuppressWarnings("UnusedAssignment")
        @Override
        public void onReceive(Context context, Intent intent) {
            int battery_percent=0;
            if (Build.VERSION.SDK_INT >= 21) {
                BatteryManager bm = (BatteryManager) context.getSystemService(BATTERY_SERVICE);
                assert bm != null;
                battery_percent =  bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

            } else {

                IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
                Intent batteryStatus = context.registerReceiver(null, iFilter);

                int level = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) : -1;
                int scale = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1) : -1;

                battery_percent = 100*level / scale;

            }
            txtBattery.setText(String.format(getString(R.string.battery), battery_percent));
        }
    };



    //private final BroadcastReceiver wifiStateReceiver = new BroadcastReceiver() {
     //   @Override
     //   public void onReceive(Context context, Intent intent) {
   //         txtPomoc.setText("Zmiana sieci");
    //        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    //        WifiInfo wifiInfo;

      //      wifiInfo = wifiManager.getConnectionInfo();
      //      if (wifiInfo.getSupplicantState() == SupplicantState.COMPLETED) {
         //       String ssid = wifiInfo.getSSID();
        //        if(Objects.equals(ssid, '"' + "iStarOne_v.03" + '"')) {
         //           limiter=false;
         //           txtWifi.setText("OK");
         //       }
          //      else{
         //           limiter=true;
          //          txtWifi.setText("ERR");
          //      }
        //    }
        //    }
      //  };

}

