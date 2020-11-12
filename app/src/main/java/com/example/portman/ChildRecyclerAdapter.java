package com.example.portman;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class ChildRecyclerAdapter extends RecyclerView.Adapter<ChildRecyclerAdapter.ViewHolder> {
    public ChildRecyclerAdapter(List<String> items) {
        this.items = items;
    }

    List<String> items;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.itemTextView.setText(items.get(position));
        Log.d("Child", "onBindViewHolder: "+items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemTextView;
        ImageView imageButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTextView=(TextView) itemView.findViewById(R.id.itemTextView);
//            String symbol=itemTextView.getText().toString();

            imageButton =(ImageView) itemView.findViewById(R.id.goToDetailsArrow);
            imageButton.setOnClickListener(new View.OnClickListener()   {
                public void onClick(View v)  {
                    try {
                        //Go to Details;
                        String symbol=itemTextView.getText().toString();
                        Intent details_intent= new Intent(itemView.getContext(), Details.class);
                        Log.d("Child", "onClick: "+symbol);
                        details_intent.putExtra("symbol",symbol);
                        itemView.getContext().startActivity(details_intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

}
