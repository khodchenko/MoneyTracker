package com.example.moneytracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class ItemsFragment extends Fragment {
    private static final int TYPE_UNKNOWN = -1;

    public static final int TYPE_INCOMES = 1;
    public static final int TYPE_EXPENSES = 2;

    private static final String TYPE_KEY = "type";


    public static ItemsFragment createItemsFragment(int type) {
        ItemsFragment fragment = new ItemsFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(ItemsFragment.TYPE_KEY, ItemsFragment.TYPE_INCOMES);

        fragment.setArguments(bundle);
        return fragment;
    }


    private int type;
    private RecyclerView recycler;

    private ItemsAdapter adapter;


    private Api api;
    private App app;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ItemsAdapter();

        Bundle bundle = getArguments();
        type = bundle.getInt(TYPE_KEY, TYPE_UNKNOWN);

        if (type == TYPE_UNKNOWN) {
            throw new IllegalArgumentException("Unknown type");
        }

        app = (App) getActivity().getApplication();

        api = app.getApi();
    }




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_items, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler = view.findViewById(R.id.list);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);

        loadItems();
    }

    private void loadItems() {
        api.getItems("fdsfs");
        List<Item> items = new ArrayList<>();
        items.add(new Item("молоко", 35));
        items.add(new Item("молоко", 35));
        items.add(new Item("молоко", 35));

        adapter.setData(items);
    }
}
