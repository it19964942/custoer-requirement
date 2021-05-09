package com.example.car_doctor.DATABASE;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperCus extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "dbcardoctornew.db";
    private static final String TABLE_NAME2 = "tblorder";

    //order table
    private static final String _ID = "_id";
    private static final String ITEM_ID = "item_id";
    private static final String QTY = "qty";

    public DBHelperCus(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query2 =
                "CREATE TABLE " + TABLE_NAME2 + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        ITEM_ID + " TEXT," +
                        QTY + " TEXT)";
        db.execSQL(query2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }
    public long  addOrderDet(String itemId, String qty){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ITEM_ID,itemId);
        values.put(QTY,qty);

        long result = db.insert(TABLE_NAME2,null,values);
        return result;
    }
}
