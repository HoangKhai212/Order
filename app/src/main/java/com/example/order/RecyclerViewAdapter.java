package com.example.order;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.model.Order;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.OrderViewHolder> {
    private List<Order> listOrder;
    private Context context;

    public RecyclerViewAdapter(Context context) {
        listOrder = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.order_cardview,parent,false);
        return new OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order o = listOrder.get(position);
        holder.tvCardId.setText(o.getId()+"");
        holder.tvCardItemName.setText(o.getItemName());
        holder.tvCardPrice.setText(o.getPrice()+"");
        holder.tvCardDate.setText(o.getDate());
        holder.CardRating.setRating(o.getRating());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, UpdateRemoveActivity.class);
                Order o = listOrder.get(position);
                i.putExtra("order",o);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }

    public void setListOrder(List<Order> list){
        this.listOrder = list;
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{
        private TextView tvCardId, tvCardItemName, tvCardPrice, tvCardDate;
        private RatingBar CardRating;
        public OrderViewHolder(@NonNull View v) {
            super(v);
            tvCardId = v.findViewById(R.id.tvCardID);
            tvCardItemName = v.findViewById(R.id.tvCardItemName);
            tvCardPrice = v.findViewById(R.id.tvCardPrice);
            tvCardDate = v.findViewById(R.id.tvCardDate);
            CardRating = v.findViewById(R.id.cardRating);
        }
    }
}
