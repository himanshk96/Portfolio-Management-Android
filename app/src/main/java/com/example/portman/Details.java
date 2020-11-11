package com.example.portman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Details extends AppCompatActivity {
    private Button saveButton;
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
        saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                portfolio_qty.put("aapl",1);
                saveMap(portfolio_qty);
            }
        });
    }
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT, "testDataa");
//        editor.putBoolean(SWITCH1, switch1.isChecked());
        editor.apply();
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }

    private void saveMap(Map<String,Integer> inputMap){
//        Gson gson=new Gson();

//        SharedPreferences pSharedPref = getApplicationContext().getSharedPreferences("MyVariables", Context.MODE_PRIVATE);

//        if (pSharedPref != null){
//            JSONObject jsonObject = new JSONObject(inputMap);
//            String jsonString = jsonObject.toString();
//            SharedPreferences.Editor editor = pSharedPref.edit();
//            editor.remove("My_map").commit();
//            editor.putString("My_map", jsonString);
//            editor.commit();
//        }

        SharedPreferences keyValues = getApplicationContext().getSharedPreferences("Your_Shared_Prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor keyValuesEditor = keyValues.edit();

        for (String s : portfolio_qty.keySet()) {
            keyValuesEditor.putString(s, String.valueOf(portfolio_qty.get(s)));
        }

        keyValuesEditor.commit();
    }

}