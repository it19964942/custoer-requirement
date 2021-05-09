package com.example.car_doctor.DATABASE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "dbcardoctornew.db";
    private static final String TABLE_NAME = "item";
    private static final String TABLE_NAME2 = "tblorder";
    private static final String TABLE_NAME3 = "tblDriverOrders";
    private static final String TABLE_NAME4 = "tblPayment";


    //item table
    private static final String _ID = "_id";
    private static final String ITEM_NAME = "item_name";
    private static final String ITEM_BRAND = "brand_name";
    private static final String ITEM_SIZE = "item_size";
    private static final String ITEM_WEIGHT = "item_weight";
    private static final String ITEM_UPRICE = "item_uprice";

    private static final String ITEM_ID = "item_id";
    private static final String QTY = "qty";

    //driver
    private static final String D_ORDERID = "order_id";
    private static final String COST_PER_UNIT = "cost_per_unit";


    //admin
    private static final String USER_TYPE = "user_type";
    private static final String YEAR = "year";
    private static final String MONTH = "month";
    private static final String AMOUNT = "amount";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create item table
        String query =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        ITEM_NAME + " TEXT," +
                        ITEM_BRAND + " TEXT," +
                        ITEM_SIZE + " TEXT," +
                        ITEM_WEIGHT + " TEXT," +
                        ITEM_UPRICE + " TEXT)";

        //create order table
        String query2 =
                "CREATE TABLE " + TABLE_NAME2 + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        ITEM_ID + " TEXT," +
                        QTY + " TEXT)";

        //create driver table
        String query3 =
                "CREATE TABLE " + TABLE_NAME3 + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        D_ORDERID + " TEXT," +
                        COST_PER_UNIT + " TEXT)";

        //create admin payment table
        String query4 =
                "CREATE TABLE " + TABLE_NAME4 + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        USER_TYPE + " TEXT," +
                        YEAR + " TEXT," +
                        MONTH + " TEXT," +
                        AMOUNT + " TEXT)";

        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME3);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME4);
        onCreate(db);

    }

    public long addItems(String iName, String bName, String size, String weight, String uPrice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ITEM_NAME, iName);
        values.put(ITEM_BRAND, bName);
        values.put(ITEM_SIZE, size);
        values.put(ITEM_WEIGHT, weight);
        values.put(ITEM_UPRICE, uPrice);

        long newRowId = db.insert(TABLE_NAME, null, values);
        return newRowId;
    }

    public long addDriverOders(String orderId, String cost) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(D_ORDERID, orderId);
        values.put(COST_PER_UNIT, cost);

        long res = db.insert(TABLE_NAME3, null, values);
        return res;
    }

    public long addpayment(String userType, String year, String month, String amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USER_TYPE, userType);
        values.put(YEAR, year);
        values.put(MONTH, month);
        values.put(AMOUNT, amount);

        long res = db.insert(TABLE_NAME4, null, values);
        return res;
    }


    public Cursor readAllData() {
        String sql = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(sql, null);

        }
        return cursor;
    }

    public Cursor readAllDriverOders() {
        String sql = "SELECT * FROM " + TABLE_NAME3;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(sql, null);
        }
        return cursor;
    }

    public Cursor readAllPayment() {
        String sql = "SELECT * FROM " + TABLE_NAME4;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
         if(db != null){
             cursor = db.rawQuery(sql,null);
         }
         return cursor;
    }

    public long updateData(String row_id, String name, String brand, String size, String weight, String price) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ITEM_NAME, name);
        values.put(ITEM_BRAND, brand);
        values.put(ITEM_SIZE, size);
        values.put(ITEM_WEIGHT, weight);
        values.put(ITEM_UPRICE, price);

        long res = db.update(TABLE_NAME, values, "_ID=?", new String[]{row_id});
        return res;

    }
    public long updateDriverOder(String row_id, String cost){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COST_PER_UNIT,cost);
        long res = db.update(TABLE_NAME3,values,"_ID=?",new String[]{row_id});
        return res;
    }
    public long updatePayment(String row_id, String cost){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AMOUNT,cost);
        long res = db.update(TABLE_NAME4,values,"_ID=?",new String[]{row_id});
        return res;
    }

    public long deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long val = db.delete(TABLE_NAME, "_ID=?", new String[]{row_id});
        return val;
    }

    public long deleteOder(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long val = db.delete(TABLE_NAME2, "_ID=?", new String[]{row_id});
        return val;
    }

    public long deleteDriverOrder(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long val = db.delete(TABLE_NAME3, "_ID=?", new String[]{row_id});
        return val;
    }
    public long deletePayment(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long val = db.delete(TABLE_NAME4, "_ID=?", new String[]{row_id});
        return val;
    }
    public long addOrderDet(String itemId, String qty) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ITEM_ID, itemId);
        values.put(QTY, qty);

        long result = db.insert(TABLE_NAME2, null, values);
        return result;
    }

    public Cursor readAllOders() {
        String sql = "SELECT * FROM " + TABLE_NAME2;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(sql, null);
        }
        return cursor;
    }

    public long updateOderDetails(String row_id, String qty) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QTY, qty);
        long res = db.update(TABLE_NAME2, values, " _ID=?", new String[]{row_id});
        return res;
    }
}
