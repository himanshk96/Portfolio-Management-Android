package com.example.portman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Details extends AppCompatActivity {
    private static final String TAG = "Details";
    private TextView titleSymbolView;
    HashMap<String,String> watchlist;
    ImageButton starButton;
    String titleSymbol;
    HashMap<String,Integer> portfolio_qty= new HashMap<String,Integer>();
//    HashMap<Integer, String> hash_map = new HashMap<Integer, String>();

//
    public static final String TEXT = "text";
    public static final String SWITCH1 = "switch1";
    public static final String SHARED_PREFS = "sharedPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        titleSymbolView = (TextView) findViewById(R.id.titleSymbol);
        Intent currIntent=getIntent();

        titleSymbol=currIntent.getStringExtra("symbol");
        Log.d(TAG, "onCreate: "+titleSymbol);
        watchlist=readFromSP("watchlist");
        String company_name=watchlist.getOrDefault(titleSymbol,"Default");
        titleSymbolView.setText(titleSymbol+company_name);


        starButton =(ImageButton) findViewById(R.id.starToggle);
        starButton.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                try {
                    //Go to Details;
                    watchlist.put(titleSymbol,"Apple Inc.");
                    insertToSP(watchlist,"watchlist");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void insertToSP(HashMap<String, String> jsonMap,String data_) {
        String jsonString = new Gson().toJson(jsonMap);
        SharedPreferences sharedPreferences = getSharedPreferences("HashMap", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(data_, jsonString);
        editor.apply();
    }


    private HashMap<String, String> readFromSP(String data_){
        SharedPreferences sharedPreferences = getSharedPreferences("HashMap", MODE_PRIVATE);
        String defValue = new Gson().toJson(new HashMap<String, String>());
        String json=sharedPreferences.getString(data_,defValue);
        TypeToken<HashMap<String,String>> token = new TypeToken<HashMap<String,String>>() {};
        HashMap<String,String> retrievedMap=new Gson().fromJson(json,token.getType());
        return retrievedMap;
    }





}