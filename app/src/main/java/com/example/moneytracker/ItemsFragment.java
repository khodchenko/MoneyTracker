package com.example.moneytracker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.moneytracker.api.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemsFragment extends Fragment {

    private static final String TYPE_KEY = "type";
    private static final String TAG = "ItemsFragment";
    public static final int ADD_ITEM_REQUEST_CODE = 123;

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
    private ItemsAdapter adapter;
    private SwipeRefreshLayout refresh;

    private Api api;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ItemsAdapter();
        adapter.setListener(new AdapterListener());

        Bundle bundle = getArguments();
        type = bundle.getString(TYPE_KEY, Item.TYPE_EXPENSES);

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


        refresh = view.findViewById(R.id.refresh);
        refresh.setColorSchemeColors(Color.DKGRAY);
        refresh.setOnRefreshListener(this::loadItems);
        loadItems();
    }

    private void loadItems() {
        Call<List<Item>> call = api.getItems(type);

        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(@NonNull Call<List<Item>> call, @NonNull Response<List<Item>> response) {
                adapter.setData(response.body());
                refresh.setRefreshing(false);
            }

            @Override
            public void onFailure(@NonNull Call<List<Item>> call, @NonNull Throwable t) {
                refresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == ADD_ITEM_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Item item = data.getParcelableExtra("item");
            if (item.type.equals(type)) {
                adapter.addItem(item);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //                          ACTION MODE CODE

    private ActionMode actionMode = null;

    private void removeSelectedItems() {   //метод после нажатия кнопки
        for (int i = adapter.getSelectedItems().size() - 1; i >= 0; i--) {
            adapter.remove(adapter.getSelectedItems().get(i));
        }
        actionMode.finish();
    }

    private class AdapterListener implements ItemsAdapterListener {


        private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
            //хз зачем это из AdapterListener.OnItemClick
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = new MenuInflater(getContext());
                inflater.inflate(R.menu.items_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.remove:
                        showDialog();
                        break;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                adapter.clearSelection();
                actionMode = null;
            }
        };

        @Override
        //меняем тулбар меин активити при нажатии на экшен мод для удаления фрагмента
        public void onItemClick(Item item, int position) {
            //проверка на выделения
            if (isInActionMode()) {
                toggleSelection(position);
            }
        }

        @Override
        public void onItemLongClick(Item item, int position) {
            if (isInActionMode()) {
                return;
            }
            actionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(actionModeCallback);
            toggleSelection(position);
        }

        //метод чтобы не нажимать повторно
        private boolean isInActionMode() {
            return actionMode != null;
        }

        private void toggleSelection(int position) {
            adapter.toggleSelection(position);
        }

    }

    private void showDialog() {
        DeleteItemsDialog dialog = new DeleteItemsDialog();
        dialog.show(getFragmentManager(),"ConfirmationDialog");
    }
}