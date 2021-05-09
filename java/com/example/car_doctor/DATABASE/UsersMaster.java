package com.example.car_doctor.DATABASE;

import android.content.Context;
import android.provider.BaseColumns;

public class UsersMaster {
    Context context;
    public UsersMaster() {
    }
    public static class Items implements BaseColumns{
        public static final String TABLE_NAME = "items";
        public static final String COLUMN_NAME_ITEM_NAME = "item_name";
        public static final String COLUMN_NAME_BRAND_NAME = "brand_name";
        public static final String COLUMN_NAME_ITEM_SIZE = "item_size";
        public static final String COLUMN_NAME_ITEM_WEIGHT = "item_weight";
        public static final String COLUMN_NAME_UNIT_PRICE = "item_unit_price";

    }

}
