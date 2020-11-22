package com.example.portman;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class backendCalls {
    Context context;
    String backend="http://hw8portfolio-env.eba-xf5362dh.us-east-1.elasticbeanstalk.com/";

    public backendCalls(Context context) {
        this.context = context;
    }

    private JSONObject[] getDailyData(String symbol){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String backend="http://hw8portfolio-env.eba-xf5362dh.us-east-1.elasticbeanstalk.com/";
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
}
