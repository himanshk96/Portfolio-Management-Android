package com.example.portman;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
//import android.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private AutoSuggestAdapter autoSuggestAdapter;
    private Handler handler;
    private static final int TRIGGER_AUTO_COMPLETE = 1300;
    private static final long AUTO_COMPLETE_DELAY = 100;

    String backend="http://hw8portfolio-env.eba-xf5362dh.us-east-1.elasticbeanstalk.com/";
    public static final String TAG="MainActivity";

    ArrayAdapter<String> arrayAdapter;
    List<Section> sectionList=new ArrayList<>();
    RecyclerView mainRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.include);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.showOverflowMenu();

        TextView todaysDateView1=findViewById(R.id.todaysDateView);
        String currentDate = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(new Date());
        Log.d(TAG, "onCreate: Date"+ currentDate);
        todaysDateView1.setText(currentDate);
        //Search Part

        // Search part complete

        initData();
        mainRecyclerView=findViewById(R.id.mainRecyclerView);

        CoordinatorLayout coordinatorLayout=findViewById(R.id.coordinatorLayout);

        MainRecyclerAdapter mainRecyclerAdapter=new MainRecyclerAdapter(this,sectionList);
        mainRecyclerView.setAdapter(mainRecyclerAdapter);
        mainRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));







//        ListView listView=findViewById(R.id.my_list);
//        Button btn=(Button) findViewById(R.id.go_to_details);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, Details.class));
//            }
//        });
//        List<String> mylist=new ArrayList<>();
//        mylist.add("Eraser");
//        mylist.add("iuhiasdhfa");
//        mylist.add("iabsdfbaf");
//
//        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,mylist);
//
//        listView.setAdapter(arrayAdapter);


    }

    private void initData(){
        String sectionName="PORTFOLIO";

        List<String> sectionItems=new ArrayList<>();
        sectionItems.add("Your string");
        sectionItems.add("Hello");
        sectionItems.add("Bye");
        sectionItems.add("Hi");

        String sectionName2="FAVORTIES";
        List<String> sectionItems2=new ArrayList<>();
//        getDailyData("aapl");

//        Log.d(TAG, "initData: "+getIexData("aapl"));
//        Log.d(TAG, "initData: "+getDailyData("aapl"));
        sectionItems2.add("Hey");
        sectionItems2.add("Zey");
        sectionItems2.add("Fi");
        sectionItems2.add("Fi");

        sectionList.add(new Section(sectionName,sectionItems));
        sectionList.add(new Section(sectionName2,sectionItems2));
        Log.d(TAG,"Referesh Every15 sec");

    }

//    private JSONObject[] getIexData(String symbol){
//        Log.d(TAG, "getIexData: call"+symbol);
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        final String url =backend+ "api/iex_data/multi/"+symbol;
//        final JSONObject[] data = new JSONObject[1];
//        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>()
//                {
//                    @Override
//                    public void onResponse(JSONObject response)
//                    {
//
//                        Log.e(" result"+response.length(),(String.valueOf(response)));
//                        data[0] =response;
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
////        Log.d(TAG, "getIexData: "+data);
//        return data;
//    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
////        getMenuInflater().inflate(R.menu.my_menu,menu);
////        MenuItem menuItem=menu.findItem(R.id.search_icon);
////        SearchView searchView=(SearchView) menuItem.getActionView();
//////        searchView.setQueryHint("Stocks");
////        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
////            @Override
////            public boolean onQueryTextSubmit(String query) {
////
////                return false;
////            }
////
////            @Override
////            public boolean onQueryTextChange(String newText) {
//////                arrayAdapter.getFilter().filter(newText);
////                return false;
////            }
////        });
////
////        return super.onCreateOptionsMenu(menu);
//    }
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        autoSuggestAdapter = new AutoSuggestAdapter(this,
                android.R.layout.simple_dropdown_item_1line);
        getMenuInflater().inflate(R.menu.my_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search_icon);
        SearchView search = (SearchView) menuItem.getActionView();
//        MenuItemCompat.setOnActionExpandListener(search, new MenuItemCompat.OnActionExpandListener() {
//
//            @Override
//            public boolean onMenuItemActionExpand(MenuItem item) {
//                // Set styles for expanded state here
//                if (getSupportActionBar() != null) {
////                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.RED));
////                    getSupportActionBar().s
//                }
//                return true;
//            }
//
//            @Override
//            public boolean onMenuItemActionCollapse(MenuItem item) {
//                // Set styles for collapsed state here
//                if (getSupportActionBar() != null) {
////                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLUE));
//                }
//                return true;
//            }
//        });

        final SearchView.SearchAutoComplete autoCompleteTextView = search.findViewById(androidx.appcompat.R.id.search_src_text);
        autoCompleteTextView.setAdapter(autoSuggestAdapter);
        autoCompleteTextView.setDropDownHeight(1100);
        autoCompleteTextView.setThreshold(3);
        autoCompleteTextView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        String[] symbol=autoSuggestAdapter.getObject(position).split("-");
                        goToDetails(symbol[0]);
//                        autoCompleteTextView.setText(autoSuggestAdapter.getObject(position));


                    }
                });
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int
                    count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == TRIGGER_AUTO_COMPLETE) {
                    if (!TextUtils.isEmpty(autoCompleteTextView.getText())) {
                        makeApiCall(autoCompleteTextView.getText().toString());
                    }
                }
                return false;
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
//                intent.putExtra("query",query);
//                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                handler.removeMessages(TRIGGER_AUTO_COMPLETE);
                handler.sendEmptyMessageDelayed(TRIGGER_AUTO_COMPLETE,
                        AUTO_COMPLETE_DELAY);
                Toast.makeText(getApplicationContext(), newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
    private void makeApiCall(String text) {
        RequestQueue queue = Volley.newRequestQueue(this);
        final List<String> stringList = new ArrayList<>();

        String url=backend+"api/search/"+text;
        JsonArrayRequest jjj = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, String.valueOf(response));
                try {
                    for(int i=0;i<response.length();i++ ){
                        JSONObject obj = (JSONObject) response.get(i);
                        String addto = obj.getString("ticker")+" - "+obj.getString("name");
                        if(addto.length()>36) addto = addto.substring(0,34)+"..";
                        stringList.add(addto);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                autoSuggestAdapter.setData(stringList);
                autoSuggestAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR","error => "+error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Ocp-Apim-Subscription-Key", "ac024cec74564fddaf6810bf13a1702a");

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jjj);

    }
    public void goToDetails(String symbol){
        Intent details_intent= new Intent(this, Details.class);
//        Log.d("Child", "onClick: "+symbol);
        details_intent.putExtra("symbol",symbol);
        this.startActivity(details_intent);
    }
}