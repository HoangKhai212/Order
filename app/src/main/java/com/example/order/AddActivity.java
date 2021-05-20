package com.example.order;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.order.model.Order;
import com.example.order.SQLiteOrder;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    private EditText edtNameAdd, edtPriceAdd;
    private TextView tvDateAdd;
    private Button btnGetDateAdd, btnAddAdd, btnBackAdd;
    private RatingBar ratingAdd;
    private SQLiteOrder sqlHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
        sqlHelper = new SQLiteOrder(AddActivity.this);
        btnAddAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order o = new Order();
                o.setItemName(edtNameAdd.getText().toString());
                o.setDate(tvDateAdd.getText().toString());
                o.setPrice(Double.parseDouble(edtPriceAdd.getText().toString()));
                o.setRating(ratingAdd.getRating());
                long count = sqlHelper.addOrder(o);
                Intent i = new Intent(AddActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnBackAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnGetDateAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int y = c.get(Calendar.YEAR);
                int m = c.get(Calendar.MONTH);
                int d = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvDateAdd.setText(dayOfMonth+"/"+(month+1)+"/"+(year));
                    }
                },y,m,d);
                dpd.show();
            }
        });
    }

    private void init() {
        edtNameAdd = findViewById(R.id.edtNameAdd);
        edtPriceAdd = findViewById(R.id.edtPriceAdd);
        tvDateAdd = findViewById(R.id.tvDateAdd);
        btnGetDateAdd = findViewById(R.id.btnGetDateAdd);
        btnAddAdd = findViewById(R.id.btnAddAdd);
        btnBackAdd = findViewById(R.id.btnBackAdd);
        ratingAdd = findViewById(R.id.ratingBarAdd);
    }
}