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

import java.util.Collections;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class ChildRecyclerAdapter extends RecyclerView.Adapter<ChildRecyclerAdapter.ViewHolder> implements ItemTouchHelperAdapter  {
    public ChildRecyclerAdapter(List<ItemRow> items) {
        this.items = items;
    }

    List<ItemRow> items;

    @Override
    public int getItemViewType(int position){
        if (items.get(position).getStockName().contains("Net Worth"))
            return 123567; // any random integer you can use
        return super.getItemViewType(position);
    }
    public void removeItem(int position) {
        this.items.remove(position);
        notifyItemRemoved(position);
    }
    public void restoreItem(ItemRow item, int position) {
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
        holder.itemTextView.setText(items.get(position).getStockName());
        Log.d("Child bind", "onBindViewHolder: "+holder.priceTextView);
        holder.quantityTextView.setText(items.get(position).getQuantity());
        holder.priceTextView.setText( String.valueOf((int)items.get(position).getCurrPrice()));
        holder.changeText.setText(String.valueOf((int)items.get(position).getChange()));
//        Log.d("Child Binder", "onBindViewHolder: "+items.get(position).getCurrPrice());

//        Log.d("Child", "onBindViewHolder: "+items.get(position));
    }
    catch (Exception e) {
        //e.printStackTrace();
    }

    }

    public List<ItemRow> getData() {
        return this.items;
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(items, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(items, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemTextView;
        ImageView imageButton;
        TextView quantityTextView;
    TextView priceTextView;
    TextView changeText;
    public ViewHolder(@NonNull View itemView) {
            super(itemView);

        itemTextView=(TextView) itemView.findViewById(R.id.itemTextView);
            quantityTextView=(TextView) itemView.findViewById(R.id.textview_quantity);
            priceTextView=(TextView) itemView.findViewById(R.id.textview_price);
            changeText= (TextView) itemView.findViewById(R.id.textview_change);
//        Log.d("ViewHolder Child", "ViewHolder: "+changeText.getText());
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
//    class ViewHolder1 extends RecyclerView.ViewHolder{
//        TextView itemTextView;
//        ImageView imageButton;
//        public void goToDetailsOnClik(View v){
//            try {
//                //Go to Details;
//                String symbol=itemTextView.getText().toString();
//                Intent details_intent= new Intent(itemView.getContext(), Details.class);
//                Log.d("Child", "onClick: "+symbol);
//                details_intent.putExtra("symbol",symbol);
//                itemView.getContext().startActivity(details_intent);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        public ViewHolder1(@NonNull View itemView) {
//            super(itemView);
//
//            itemTextView=(TextView) itemView.findViewById(R.id.itemTextView);
////            String symbol=itemTextView.getText().toString();
//            try{
//                itemView.setOnClickListener(new View.OnClickListener()   {
//                    public void onClick(View v)  {
//                        goToDetailsOnClik(v);
//                    }
//                });
//
//                imageButton =(ImageView) itemView.findViewById(R.id.goToDetailsArrow);
//                imageButton.setOnClickListener(new View.OnClickListener()   {
//                    public void onClick(View v)  {
//                        goToDetailsOnClik(v);
//                    }
//                });} catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//    }

}
