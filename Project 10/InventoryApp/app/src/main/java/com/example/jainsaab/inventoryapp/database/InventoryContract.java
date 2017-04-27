package com.example.jainsaab.inventoryapp.database;

import android.provider.BaseColumns;

public class InventoryContract {

    public class InventoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "inventories";
        public static final String COL_PRODUCT_NAME = "product";
        public static final String COL_PRODUCT_PRICE = "price";
        public static final String COL_PRODUCT_QUANTITY = "quantity";
        public static final String COL_PRODUCT_IMAGE = "image";
        public static final String COL_PRODUCT_SUPPLIER = "supplier";
    }
}
