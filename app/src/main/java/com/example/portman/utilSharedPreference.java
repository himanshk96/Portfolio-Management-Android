package com.example.portman;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.content.Context;

import java.util.HashMap;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.ContextWrapper;
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
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class utilSharedPreference {
//    private void insertToSP(HashMap<String, String> jsonMap, String data_) {
//        String jsonString = new Gson().toJson(jsonMap);
//        SharedPreferences sharedPreferences = getSharedPreferences("HashMap", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(data_, jsonString);
//        editor.apply();
//    }
//
//
//
//
//    private HashMap<String, String> readFromSP(String data_){
//        SharedPreferences sharedPreferences = getSharedPreferences("HashMap", MODE_PRIVATE);
//        String defValue = new Gson().toJson(new HashMap<String, String>());
//        String json=sharedPreferences.getString(data_,defValue);
//        TypeToken<HashMap<String,String>> token = new TypeToken<HashMap<String,String>>() {};
//        HashMap<String,String> retrievedMap=new Gson().fromJson(json,token.getType());
//        return retrievedMap;
//    }
}
