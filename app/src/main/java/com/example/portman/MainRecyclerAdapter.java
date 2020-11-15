package com.example.portman;


import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    public MainRecyclerAdapter(List<Section> sectionList) {
        this.sectionList = sectionList;
    }

    List<Section> sectionList;
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
    holder.childRecyclerview.setAdapter(childRecyclerAdapter);

        


    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

    TextView sectionNameTextView;
    RecyclerView childRecyclerview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sectionNameTextView=itemView.findViewById(R.id.sectionNameTextView);
            childRecyclerview=itemView.findViewById(R.id.childRecyclerView);


        }
    }

}
