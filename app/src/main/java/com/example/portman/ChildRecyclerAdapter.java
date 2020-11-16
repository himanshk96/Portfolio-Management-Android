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

    @Override
    public int getItemViewType(int position){
        if (items.get(position).contains("Your string"))
            return 123567; // any random integer you can use
        return super.getItemViewType(position);
    }
    public void removeItem(int position) {
        this.items.remove(position);
        notifyItemRemoved(position);
    }
    public void restoreItem(String item, int position) {
        this.items.add(position, item);
        notifyItemInserted(position);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 123567) // the view type you returned
        {
            LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
            View view=layoutInflater.inflate(R.layout.extra_row,parent,false);
            return new ViewHolder(view);
        }
        else{
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_row,parent,false);

        return new ViewHolder(view);}
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    try{
        holder.itemTextView.setText(items.get(position));
        Log.d("Child", "onBindViewHolder: "+items.get(position));}
    catch (Exception e) {
        //e.printStackTrace();
    }

    }

    public List<String> getData() {
        return this.items;
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
try{
            imageButton =(ImageView) itemView.findViewById(R.id.goToDetailsArrow);
            itemView.setOnClickListener(new View.OnClickListener()   {
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
            });} catch (Exception e) {
    e.printStackTrace();
}

        }
    }
    class ViewHolder1 extends RecyclerView.ViewHolder{
        TextView itemTextView;
        ImageView imageButton;
        public void goToDetailsOnClik(View v){
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
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);

            itemTextView=(TextView) itemView.findViewById(R.id.itemTextView);
//            String symbol=itemTextView.getText().toString();
            try{
                itemView.setOnClickListener(new View.OnClickListener()   {
                    public void onClick(View v)  {
                        goToDetailsOnClik(v);
                    }
                });

                imageButton =(ImageView) itemView.findViewById(R.id.goToDetailsArrow);
                imageButton.setOnClickListener(new View.OnClickListener()   {
                    public void onClick(View v)  {
                        goToDetailsOnClik(v);
                    }
                });} catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
