package com.example.portman;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
//import android.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
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


        initData();
        mainRecyclerView=findViewById(R.id.mainRecyclerView);

        CoordinatorLayout coordinatorLayout=findViewById(R.id.coordinatorLayout);

        MainRecyclerAdapter mainRecyclerAdapter=new MainRecyclerAdapter(this,sectionList);
        mainRecyclerView.setAdapter(mainRecyclerAdapter);
        mainRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

//    enableSwipeToDeleteAndUndo(mainRecyclerAdapter,mainRecyclerView,coordinatorLayout);






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
    private void enableSwipeToDeleteAndUndo(MainRecyclerAdapter mAdapter,RecyclerView recyclerView, CoordinatorLayout coordinatorLayout ) {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final Section item = mAdapter.getData().get(position);

                mAdapter.removeItem(position);


                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mAdapter.restoreItem(item, position);
                        recyclerView.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
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
        sectionItems2.add("Hey");
        sectionItems2.add("Zey");
        sectionItems2.add("Fi");
        sectionItems2.add("Fi");sectionItems2.add("Fi");sectionItems2.add("Fi");sectionItems2.add("Fi");sectionItems2.add("Fi");sectionItems2.add("Fi");sectionItems2.add("Fi");sectionItems2.add("Fi");sectionItems2.add("Fi");sectionItems2.add("Fi");sectionItems2.add("Fi");sectionItems2.add("Fi");

        sectionList.add(new Section(sectionName,sectionItems));
        sectionList.add(new Section(sectionName2,sectionItems2));
        Log.d(TAG,"Referesh Every15 sec");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        MenuItem menuItem=menu.findItem(R.id.search_icon);
        SearchView searchView=(SearchView) menuItem.getActionView();
//        searchView.setQueryHint("Stocks");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}