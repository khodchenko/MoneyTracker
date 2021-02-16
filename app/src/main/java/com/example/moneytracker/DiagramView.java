package com.example.moneytracker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DiagramView extends View {

    private int income;
    private int expense;
    private Paint incomePaint = new Paint();
    private Paint expensePaint = new Paint();

    public DiagramView(Context context) {
        this(context, null);
    }

    public DiagramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DiagramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        incomePaint.setColor(getResources().getColor(R.color.balance_income_color));
        expensePaint.setColor(getResources().getColor(R.color.balance_expense_color));
    }

    public void update(int income, int expense) {
        this.income = income;
        this.expense = expense;
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawRectDiagram(canvas);
    }

    private void drawPieDiagram(Canvas canvas) {

    }

    private void drawRectDiagram(Canvas canvas) {
        if (expense == income)
            return;

        long max = Math.max(expense, income); //check what digit bigger
        long expensesHeight = getHeight() * expense / max;
        long incomesHeight = getHeight() * income / max;

        int w = getWidth() / 4;

        canvas.drawRect(w / 2, getHeight() - expensesHeight, w * 3 / 2, getHeight(), expensePaint);
        canvas.drawRect(5 * w / 2, getHeight() - incomesHeight, w * 7 / 2, getHeight(), incomePaint);


    }
}
