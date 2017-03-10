package com.chenmo.jizhangbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 作者：沉默
 * 日期：2017/3/9
 * QQ:823925783
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String COST_TITLE = "cost_title";
    public static final String COST_DATE = "cost_date";
    public static final String COST_MONEY = "cost_money";
    public static final String COST = "cost";

    public DatabaseHelper(Context context) {
        super(context, "daily", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists cost(" +
                "id integer primary key," +
                "cost_title varchar," +
                "cost_date varchar," +
                "cost_money varchar)");
    }
    public void insertCost(CostBean costBean){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COST_TITLE,costBean.costTitle);
        cv.put(COST_DATE,costBean.costDate);
        cv.put(COST_MONEY,costBean.costMoney);
        database.insert(COST,null,cv);
    }

    public void deletrAllData(){
        SQLiteDatabase database=getWritableDatabase();
        database.delete(COST,null,null);
    }

    //ASC按照顺序排列
    public Cursor getAllCostData(){
        SQLiteDatabase database=getWritableDatabase();
        return database.query(COST,null,null,null,null,null,COST_DATE+" ASC");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
