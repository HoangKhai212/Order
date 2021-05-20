package com.example.order;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.order.model.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLiteOrder extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="orderDB.db";
    private static final int DATABASE_VERSION=1;
    public SQLiteOrder(@Nullable Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE orderTB (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT," +
                "dateorder TEXT," +
                "price REAL," +
                "rating REAL)";
        db.execSQL(sql);
    }

    public long addOrder(Order o){
        ContentValues v = new ContentValues();
        v.put("name", o.getItemName());
        v.put("dateorder",o.getDate());
        v.put("price",o.getPrice());
        v.put("rating",o.getRating());
        SQLiteDatabase sld = getWritableDatabase();
        return sld.insert("orderTB",null,v);
    }

    public List<Order> getAll(){
        List<Order> list = new ArrayList<>();
        SQLiteDatabase statement = getReadableDatabase();
        Cursor c = statement.query("orderTB",null,null,null,null,null,null);
        while((c!=null) && c.moveToNext()){
            int id = c.getInt(0);
            String name = c.getString(1);
            String date = c.getString(2);
            float price = c.getFloat(3);
            float rating = c.getFloat(4);
            list.add(new Order(id,name,date,price,rating));
        }
        return list;
    }

    public int updateOrder(Order o){
        ContentValues v = new ContentValues();
        v.put("name",o.getItemName());
        v.put("dateorder",o.getDate());
        v.put("price", o.getPrice());
        v.put("rating", o.getRating());
        SQLiteDatabase slq = getWritableDatabase();
        String wClause = "id=?";
        String[] wArgs = {String.valueOf(o.getId())};
        return slq.update("orderTB",v,wClause,wArgs);
    }

    public int deleteOrder(int id){
        String wClause = "id=?";
        String[] wArgs = {String.valueOf(id)};
        SQLiteDatabase sql = getWritableDatabase();
        return sql.delete("orderTB",wClause,wArgs);
    }

    public List<Order> getByName(String name){
        String sql = "name like ?";
        String[] args = {"%" + name + "%"};
        SQLiteDatabase slq = getReadableDatabase();
        Cursor c = slq.query("orderTB",null, sql, args, null, null, null);
        List<Order> list = new ArrayList<>();
        while((c!=null) && c.moveToNext()){
            int id = c.getInt(0);
            String itemName = c.getString(1);
            String date = c.getString(2);
            float price = c.getFloat(3);
            float rating = c.getFloat(4);
            list.add(new Order(id,itemName,date,price,rating));
        }
        return list;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
