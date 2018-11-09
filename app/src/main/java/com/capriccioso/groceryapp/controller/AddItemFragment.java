package com.capriccioso.groceryapp.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.capriccioso.groceryapp.R;
import com.capriccioso.groceryapp.model.Item;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by dobit on 11/9/2018.
 */

public class AddItemFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Button addItemButton;
    private EditText editTextItemName, editTextItemQuantity, editTextItemColor, editTextItemSize, editTextItemBrand, ediTextItemPrice;
    private Realm realm;
    private String itemName, itemColor, itemBrand;
    private int itemSize, itemQuantity;
    private float itemPrice;
    private ArrayList<Item> items;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_item, container, false); //R.layout.fragmnent_add_item is the UI of add items functionality

        addItemButton = view.findViewById(R.id.btnAddItem);
        addItemButton.setOnClickListener(this);

        editTextItemName = view.findViewById(R.id.etItemName);
        editTextItemQuantity = view.findViewById(R.id.etQuantity);
        editTextItemColor = view.findViewById(R.id.etColor);
        editTextItemSize = view.findViewById(R.id.etSize);
        editTextItemBrand = view.findViewById(R.id.etBrand);
        ediTextItemPrice = view.findViewById(R.id.etPrice);

        realm = Realm.getDefaultInstance();
        items = new ArrayList<>();



        return view;
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "Item added", Toast.LENGTH_SHORT).show();

        itemName = editTextItemName.getText().toString();
        itemQuantity = Integer.parseInt(editTextItemQuantity.getText().toString());
        itemColor = editTextItemColor.getText().toString();
        itemSize = Integer.parseInt(editTextItemSize.getText().toString());
        itemBrand = editTextItemBrand.getText().toString();
        itemPrice = Float.parseFloat(ediTextItemPrice.getText().toString());


        Item item = new Item();
        item.setName(itemName);
        item.setQuantity(itemQuantity);
        item.setColor(itemColor);
        item.setSize(itemSize);
        item.setBrand(itemBrand);
        item.setPrice(itemPrice);

        items.add(item);
        addToRealmDB();

        editTextItemName.setText("");
        editTextItemQuantity.setText("");
        editTextItemColor.setText("");
        editTextItemSize.setText("");
        editTextItemBrand.setText("");
        ediTextItemPrice.setText("");



    }


    //Adds item to the Realm database
    private void addToRealmDB(){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Item item = realm.createObject(Item.class);
                item.setName(itemName);
                item.setQuantity(itemQuantity);
                item.setColor(itemColor);
                item.setSize(itemSize);
                item.setBrand(itemBrand);
                item.setPrice(itemPrice);
            }
        });
    }


}
