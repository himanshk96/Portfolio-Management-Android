package com.example.portman;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class backendCalls {
    Context context;
    String backend="http://hw8portfolio-env.eba-xf5362dh.us-east-1.elasticbeanstalk.com/";

    public backendCalls(Context context) {
        this.context = context;
    }

    public JSONObject[] getDailyData(String symbol){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        final String url =backend+ "api/daily_data/"+symbol;
        final JSONObject[] data = new JSONObject[1];
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Log.e(" result",(String.valueOf(response)));
                        data[0] =response;

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(getRequest);
        return data;
    }
    public JSONArray[] getIexData(String symbol){
        final String url="http://10.0.2.2:8080/api/iex_data/multi/tsla";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        final JSONArray[] data = new JSONArray[1];
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        float stockWorth=0;
                        Log.d("BACKENDIEX", "iexprice request: " + response.toString());
                        data[0] =response;
//                            for (int i = 0; i < response.length(); i++) {
//                                JSONObject obj = (JSONObject) response.get(i);
//                                String t = obj.getString("ticker");
//
//                            }
                    }
                },new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
return data;
    }

//    public JSONObject[] getIexData(String symbol){
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
////        final String url =backend+ "api/daily_data/"+symbol;
//           final String url="http://10.0.2.2:8080/api/iex_data/multi/tsla";
//        final JSONObject[] data = new JSONObject[1];
//        Log.d("Backend call", "getIexData: Called Started here");
//
//        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>()
//                {
//                    @Override
//                    public void onResponse(JSONObject response)
//                    {
//                        Log.e(" result",(String.valueOf(response)));
//                            data[0] =  response;
//
//                    }
//                },
//                new Response.ErrorListener()
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }
//        );
//        requestQueue.add(getRequest);
//        Log.d("Backend call", "getIexData: Called here");
//        return data;
//    }
}
