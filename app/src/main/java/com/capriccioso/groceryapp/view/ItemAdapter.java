package com.capriccioso.groceryapp.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.capriccioso.groceryapp.R;
import com.capriccioso.groceryapp.model.Item;

import java.util.ArrayList;

/**
 * Created by dobit on 11/9/2018.
 */

//View that shows how to display the model and how it is configured
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private View view;
    private ArrayList<Item> itemList;
    private RecyclerView recyclerView;
    private OnRemoveListener onRemoveListener;
    private Item item;


    public ItemAdapter(ArrayList<Item> itemList, RecyclerView recyclerView, OnRemoveListener onRemoveListener) {
        this.itemList = itemList;
        this.recyclerView = recyclerView;
        this.onRemoveListener = onRemoveListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        item = itemList.get(position);

        holder.itemNameTextView.setText(item.getName());
        holder.itemQuantityTextView.setText(item.getQuantity()+"");
        holder.itemColorTextView.setText(item.getColor());
        holder.itemSizeTextView.setText(item.getSize()+"");
        holder.itemBrandTextView.setText(item.getBrand());
        holder.itemPriceTextView.setText(item.getPrice()+"");

    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView itemNameTextView;
        private TextView itemQuantityTextView;
        private TextView itemColorTextView;
        private TextView itemSizeTextView;
        private TextView itemBrandTextView;
        private TextView itemPriceTextView;
        private ImageView deleteImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.tvItemName);
            itemQuantityTextView = itemView.findViewById(R.id.tvItemQuantity);
            itemColorTextView = itemView.findViewById(R.id.tvItemColor);
            itemSizeTextView = itemView.findViewById(R.id.tvItemSize);
            itemBrandTextView = itemView.findViewById(R.id.tvItemBrand);
            itemPriceTextView = itemView.findViewById(R.id.tvItemPrice);

            deleteImageView = itemView.findViewById(R.id.ivDelete);
            deleteImageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onRemoveListener.onRemove(item, getAdapterPosition());
            removeAt(getAdapterPosition());
        }


        //Method to remove an item from the list
        private void removeAt(int position) {
            itemList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, itemList.size());

        }
    }

    public interface OnRemoveListener {

        void onRemove(Item item, int position);

    }

}
