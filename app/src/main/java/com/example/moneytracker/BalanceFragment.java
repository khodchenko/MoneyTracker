package com.example.moneytracker;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.moneytracker.api.Api;
import com.example.moneytracker.api.BalanceResult;

import java.util.Random;

public class BalanceFragment extends Fragment {
    private static final String TAG = "BalanceFragment";
    private TextView total;
    private TextView expense;
    private TextView income;
    private DiagramView diagramView;

    private Api api;
    private App app;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frragment_balance, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        total = view.findViewById(R.id.tv_balance_total);
        expense = view.findViewById(R.id.tv_balance_expense);
        income = view.findViewById(R.id.tv_balance_income);
        diagramView = view.findViewById(R.id.diagram);
        updateData();
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if(isVisibleToUser){
//            Log.i(TAG, "setUserVisibleHint:  true");
//            updateData();
//        }
//    }

    private void updateData() {
        Random random = new Random();
        BalanceResult result = new BalanceResult();
        result.expense = random.nextInt(50000);
        result.income = random.nextInt(50000);
        total.setText(getString(R.string.price, result.income - result.expense));
        expense.setText(getString(R.string.price, result.expense));
        income.setText(getString(R.string.price, result.income));
        diagramView.update(result.income,result.expense);
    }
}


