package com.example.moneytracker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemsFragment extends Fragment {

    private static final String TYPE_KEY = "type";
    private static final String TAG = "ItemsFragment";
    private static final int ADD_ITEM_REQUEST_CODE = 123;

    public static ItemsFragment createItemsFragment(String type) {
        ItemsFragment fragment = new ItemsFragment();

        Bundle bundle = new Bundle();
        bundle.putString(ItemsFragment.TYPE_KEY, type);
        bundle.putBoolean("key", true);

        fragment.setArguments(bundle);
        return fragment;
    }

    private String type;
    private RecyclerView recycler;
    private FloatingActionButton fab;
    private ItemsAdapter adapter;
    private SwipeRefreshLayout refresh;

    private Api api;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ItemsAdapter();

        Bundle bundle = getArguments();
        type = bundle.getString(TYPE_KEY, Item.TYPE_EXPENSES);  //TODO CHECK

        if (type.equals(Item.TYPE_UNKNOWN)) {
            throw new IllegalArgumentException("Unknown type");
        }

        api = ((App) getActivity().getApplication()).getApi();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler = view.findViewById(R.id.list);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);

        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Log.i(TAG, "OnClick");
            Intent intent = new Intent(getContext(), AddItemActivity.class);
            intent.putExtra(AddItemActivity.TYPE_KEY, type);
            startActivityForResult(intent, ADD_ITEM_REQUEST_CODE);
        });

        refresh = view.findViewById(R.id.refresh);
        refresh.setColorSchemeColors(Color.DKGRAY);
        refresh.setOnRefreshListener(this::loadItems);
        loadItems();
    }

    private void loadItems() {
        Call<List<Item>> call = api.getItems(type);

        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(@NonNull Call<List<Item>> call,@NonNull Response<List<Item>> response) {
                adapter.setData(response.body());
                refresh.setRefreshing(false);
            }

            @Override
            public void onFailure(@NonNull Call<List<Item>> call,@NonNull Throwable t) {
                refresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == ADD_ITEM_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Item item = data.getParcelableExtra("item");
            adapter.addItem(item);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}