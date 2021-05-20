package com.example.order;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.order.model.Order;

import java.util.Calendar;

public class UpdateRemoveActivity extends AppCompatActivity {
    private TextView tvId, tvDate;
    private EditText edtName, edtPrice;
    private RatingBar rating;
    private Button btnGetDate, btnUpdate, btnDelete, btnBack;
    private SQLiteOrder sqLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_update);
        sqLiteHelper = new SQLiteOrder(this);
        init();
        Intent i = getIntent();
        Order o = (Order) i.getSerializableExtra("order");
        tvId.setText(o.getId()+"");
        tvDate.setText(o.getDate());
        edtName.setText(o.getItemName());
        edtPrice.setText(o.getPrice()+"");
        rating.setRating(o.getRating());

        btnGetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int d = c.get(Calendar.DAY_OF_MONTH);
                int m = c.get(Calendar.MONTH);
                int y = c.get(Calendar.YEAR);
                DatePickerDialog dpd = new DatePickerDialog(UpdateRemoveActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvDate.setText(dayOfMonth+"/"+(month+1)+"/"+(year));
                    }
                },y,m,d);
                dpd.show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UpdateRemoveActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order o = new Order();
                o.setId(Integer.parseInt(tvId.getText().toString()));
                o.setItemName(edtName.getText().toString());
                o.setDate(tvDate.getText().toString());
                o.setPrice(Float.parseFloat(edtPrice.getText().toString()));
                o.setRating(rating.getRating());
                sqLiteHelper.updateOrder(o);
                Intent i = new Intent(UpdateRemoveActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(tvId.getText().toString());
                sqLiteHelper.deleteOrder(id);
                Intent i = new Intent(UpdateRemoveActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void init() {
        tvId = findViewById(R.id.tvIdRemoveUpdate);
        tvDate = findViewById(R.id.tvDateRemoveUpdate);
        edtName = findViewById(R.id.edtNameRemoveUpdate);
        edtPrice = findViewById(R.id.edtPriceRemoveUpdate);
        rating = findViewById(R.id.ratingBarRemoveUpdate);
        btnGetDate = findViewById(R.id.btnGetDateRemoveUpdate);
        btnUpdate = findViewById(R.id.btnUpdateRemoveUpdate);
        btnDelete = findViewById(R.id.btnRemoveRemoveUpdate);
        btnBack = findViewById(R.id.btnBackRemoveUpdate);
    }
}
