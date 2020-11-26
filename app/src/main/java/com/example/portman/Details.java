package com.example.portman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Details extends AppCompatActivity {
    private static final String TAG = "Details";
    private TextView titleSymbolView;
    private TextView titleCompanyView;
    private TextView titlePriceView;
    private TextView titleChangeView;

    private Button showMoreButton;
    private TextView aboutStock;

    HashMap<String,String> ticker_mapping;
    ArrayList<String> watchlist;
    Boolean inWatchList;
    ImageView starButton;
    String titleSymbol;
String demo="This is a companyd demo. xysasidhasdfuasdfsdfis This is a companyd demo. This is a companyd demo. This is a companyd demo. This is a companyd demo. This is a companyd demo. This is a companyd demo. This is a companyd demo.";
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
        //Back Button to go back to MainActivity
        Toolbar toolbar = findViewById(R.id.top_toolbar);
//        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_grey_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Your code
                finish();
            }
        });

        titleSymbolView = (TextView) findViewById(R.id.titleSymbol);
        titleCompanyView=(TextView) findViewById(R.id.titleCompany);
        titlePriceView=(TextView) findViewById(R.id.titlePrice);
        titleChangeView=(TextView) findViewById(R.id.titleChange);




        aboutStock =(TextView) findViewById(R.id.textView_about)         ;
        aboutStock.setText(demo);
        showMoreButton = (Button) findViewById(R.id.button_showmore);
        showMoreButton.setOnClickListener(showMoreListenere);
        Intent currIntent=getIntent();
        titleSymbol=currIntent.getStringExtra("symbol");
        Log.d(TAG, "onCreate: "+titleSymbol);


        titleSymbolView.setText(titleSymbol);
        titleCompanyView.setText("titleCompany");
        titlePriceView.setText("Price");
        titleChangeView.setText("Change");



        ticker_mapping=readFromSP("ticker_mapping");
        watchlist = ArrayreadFromSP("watchlist");
        inWatchList = watchlist.contains(titleSymbol);

        String company_name=ticker_mapping.getOrDefault(titleSymbol,"Default");
        titleSymbolView.setText(titleSymbol);


        starButton =(ImageView) findViewById(R.id.starToggle);
        if (inWatchList){
            starButton.setBackgroundResource(R.drawable.ic_baseline_star_24);
        }
        starButton.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                try {
                    // Update String

                    ticker_mapping.put(titleSymbol,"Apple Inc.");
                    insertToSP(ticker_mapping,"ticker_mapping");
                    if (!inWatchList){
                        watchlist.add(titleSymbol);
                        starButton.setBackgroundResource(R.drawable.ic_baseline_star_24);
                        int dur=Toast.LENGTH_SHORT;
                        CharSequence text = "'"+titleSymbol+"'"+" was added to Favorites";

                        Toast toast = Toast.makeText(getApplicationContext(), text, dur);
                        toast.show();
                    }
                    else {
                        watchlist.remove(titleSymbol);
                        starButton.setBackgroundResource(R.drawable.ic_baseline_star_border_24);
                        int dur=Toast.LENGTH_SHORT;
                        CharSequence text = "'"+titleSymbol+"'"+" was removed from Favorites";
                        Toast toast = Toast.makeText(getApplicationContext(), text, dur);
                        toast.show();
                    }
                    inWatchList = !inWatchList;
                    ArrayinsertToSP(watchlist,"watchlist");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "onClick: Watclist"+ watchlist.toString());
            }
        });



    }
    private View.OnClickListener showMoreListenere = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Toast.makeText(getApplicationContext(),"show more",Toast.LENGTH_SHORT).show();
            if(showMoreButton.getText().equals("Show more...")){
                showMoreButton.setText("Show less");
                aboutStock.setEllipsize(null);

                aboutStock.setMaxLines(Integer.MAX_VALUE);
            }
            else {//clicked show less
                showMoreButton.setText("Show more...");
                aboutStock.setEllipsize(TextUtils.TruncateAt.END);
                aboutStock.setMaxLines(2);
            }
        }
    };

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
    private void ArrayinsertToSP(ArrayList<String> jsonMap, String data_) {
        String jsonString = new Gson().toJson(jsonMap);
        SharedPreferences sharedPreferences = getSharedPreferences("ArrayList", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(data_, jsonString);
        editor.apply();
    }


    private ArrayList<String> ArrayreadFromSP(String data_){
        SharedPreferences sharedPreferences = getSharedPreferences("ArrayList", MODE_PRIVATE);
        String defValue = new Gson().toJson(new ArrayList<String>());
        String json=sharedPreferences.getString(data_,defValue);
        TypeToken<ArrayList<String>> token = new TypeToken<ArrayList<String>>() {};
        ArrayList<String> retrievedMap=new Gson().fromJson(json,token.getType());
        Log.d(TAG, "ArrayreadFromSP: Works");
        return retrievedMap;
    }




}