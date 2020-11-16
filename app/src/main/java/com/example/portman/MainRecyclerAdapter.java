package com.example.portman;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    public MainRecyclerAdapter(Context context, List<Section> sectionList) {
        this.sectionList = sectionList;
        this.context=context;
    }
    public List<Section> getData() {
        return this.sectionList;
    }
    public void restoreItem(Section item, int position) {
        this.sectionList.add(position, item);
        notifyItemInserted(position);
    }
    public void removeItem(int position) {
        this.sectionList.remove(position);
        notifyItemRemoved(position);
    }


    Context context;

    List<Section> sectionList;
    RecyclerView childRecyclerview;
//    CoordinatorLayout coordinatorLayout;
    @NonNull
    @Override
    public MainRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.section_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerAdapter.ViewHolder holder, int position) {
Section section=sectionList.get(position);
String sectionName=section.getSectionName() ;
    List<String> items= section.getSectionItems();
    holder.sectionNameTextView.setText(sectionName);

    ChildRecyclerAdapter childRecyclerAdapter=new ChildRecyclerAdapter(items);
    holder.childRecyclerview.addItemDecoration(new DividerItemDecoration(holder.childRecyclerview.getContext(),DividerItemDecoration.VERTICAL));

    holder.childRecyclerview.setAdapter(childRecyclerAdapter);


        enableSwipeToDeleteAndUndo(childRecyclerAdapter, holder.coordinatorLayout,holder.childRecyclerview );
        Log.d("MainRecycler", "onBindViewHolder: called enableSwipte");


    }

    public void enableSwipeToDeleteAndUndo(ChildRecyclerAdapter mAdapter, CoordinatorLayout coordinatorLayout, RecyclerView recyclerView) {
        Log.d("TAG", "enableSwipeToDeleteAndUndo: Working");

        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(context) {

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                Log.d("enableSwipe", "onSwiped: Swipped Child");

                final int position = viewHolder.getAdapterPosition();

                final String item = mAdapter.getData().get(position);

                mAdapter.removeItem(position);


//                Snackbar snackbar = Snackbar
//                        .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
//                snackbar.setAction("UNDO", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        mAdapter.restoreItem(item, position);
//                        recyclerView.scrollToPosition(position);
//                    }
//                });
//
//                snackbar.setActionTextColor(Color.YELLOW);
//                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
        Log.d("MAINRECYCLER", "enableSwipeToDeleteAndUndo: done");
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

    TextView sectionNameTextView;
        RecyclerView childRecyclerview;
    CoordinatorLayout coordinatorLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sectionNameTextView=itemView.findViewById(R.id.sectionNameTextView);
            childRecyclerview=itemView.findViewById(R.id.childRecyclerView);
            coordinatorLayout= itemView.findViewById(R.id.coordinatorLayout);

            Log.d("TAG", "ViewHolder: CoordinatorLayout created");
        }
    }

}
