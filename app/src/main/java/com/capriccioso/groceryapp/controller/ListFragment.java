package com.capriccioso.groceryapp.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.capriccioso.groceryapp.R;
import com.capriccioso.groceryapp.model.Item;
import com.capriccioso.groceryapp.view.ItemAdapter;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by dobit on 11/9/2018.
 */

public class ListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, ItemAdapter.OnRemoveListener {

    private View view;
    private ArrayList<Item> items;
    private Realm realm;
    private ItemAdapter itemAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RealmResults<Item> results;
    private int listPosition;
    private Item listItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);  //R,layout.fragment_list is the UI of show list and remove list functionality
        realm = Realm.getDefaultInstance();
        items = new ArrayList<>();

        swipeRefreshLayout = view.findViewById(R.id.swipeContainer);
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView = view.findViewById(R.id.rvItems);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        results = realm.where(Item.class).findAll();    //Realm query to get all the results

        if (results != null && !results.isEmpty()) {
            items.addAll(results);
        }

        itemAdapter = new ItemAdapter(items,recyclerView,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(itemAdapter);

        return view;
    }


    //When user swipes vertically, this method is called
    @Override
    public void onRefresh() {
        clear();        //Clears the list before adding the new set of items
        addAllItems();
        swipeRefreshLayout.setRefreshing(false);
    }

    private void clear(){
        items.clear();
        itemAdapter.notifyDataSetChanged();
    }

    private void addAllItems(){
        items.addAll(results);
        itemAdapter.notifyDataSetChanged();
    }


    //Removes the item from the database
    @Override
    public void onRemove(Item item, int position) {
        listPosition = position;
        listItem = item;

        Toast.makeText(view.getContext(), items.get(position).getName()+" removed", Toast.LENGTH_SHORT).show();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                listItem = results.get(listPosition);
                listItem.deleteFromRealm();
            }
        });
    }
}
