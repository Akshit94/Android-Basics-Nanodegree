package com.example.jainsaab.inventoryapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jainsaab.inventoryapp.Inventory;

import java.util.ArrayList;

public class InventoryDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "inventory.db";
    private static final int DATABASE_VERSION = 2;

    public InventoryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + InventoryContract.InventoryEntry.TABLE_NAME + " (" +
                InventoryContract.InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                InventoryContract.InventoryEntry.COL_PRODUCT_NAME + " TEXT NOT NULL, " +
                InventoryContract.InventoryEntry.COL_PRODUCT_PRICE + " INTEGER NOT NULL, " +
                InventoryContract.InventoryEntry.COL_PRODUCT_IMAGE + " BLOB NOT NULL, " +
                InventoryContract.InventoryEntry.COL_PRODUCT_SUPPLIER + " TEXT NOT NULL, " +
                InventoryContract.InventoryEntry.COL_PRODUCT_QUANTITY + " INTEGER NOT NULL)";

        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + InventoryContract.InventoryEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public void insert(String productName, int productPrice, int productQuantity, String productSupplier, String productImage) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(InventoryContract.InventoryEntry.COL_PRODUCT_NAME, productName);
        values.put(InventoryContract.InventoryEntry.COL_PRODUCT_PRICE, productPrice);
        values.put(InventoryContract.InventoryEntry.COL_PRODUCT_QUANTITY, productQuantity);
        values.put(InventoryContract.InventoryEntry.COL_PRODUCT_SUPPLIER, productSupplier);
        values.put(InventoryContract.InventoryEntry.COL_PRODUCT_IMAGE, productImage);

        db.insertWithOnConflict(InventoryContract.InventoryEntry.TABLE_NAME,
                null,
                values,
                SQLiteDatabase.CONFLICT_REPLACE);

        db.close(); // Closing database connection
    }

    public void updateQuantity(String productName, int quantityChange, boolean isIncrease) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + InventoryContract.InventoryEntry.TABLE_NAME +
                " WHERE " + InventoryContract.InventoryEntry.COL_PRODUCT_NAME + " = " + "'" + productName + "'", null);

        try {
            int productIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COL_PRODUCT_NAME);
            int quantityIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COL_PRODUCT_QUANTITY);
            int priceIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COL_PRODUCT_PRICE);
            int supplierIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COL_PRODUCT_SUPPLIER);

            if (cursor != null && cursor.moveToFirst()) {
                do {

                    int updatedQuantity;
                    if(isIncrease)
                        updatedQuantity = cursor.getInt(quantityIndex) + quantityChange;
                    else
                        updatedQuantity = cursor.getInt(quantityIndex) - quantityChange;

                    if (updatedQuantity > 0) {
                        ContentValues values = new ContentValues();
                        values.put(InventoryContract.InventoryEntry.COL_PRODUCT_NAME, cursor.getString(productIndex));
                        values.put(InventoryContract.InventoryEntry.COL_PRODUCT_PRICE, cursor.getString(priceIndex));
                        values.put(InventoryContract.InventoryEntry.COL_PRODUCT_QUANTITY, updatedQuantity);
                        values.put(InventoryContract.InventoryEntry.COL_PRODUCT_SUPPLIER, supplierIndex);

                        db.update(InventoryContract.InventoryEntry.TABLE_NAME, values,
                                InventoryContract.InventoryEntry.COL_PRODUCT_NAME + " = ?",
                                new String[]{String.valueOf(cursor.getString(productIndex))});
                    }
                } while (cursor.moveToNext());
            }

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Inventory> getProducts() {
        try {

            SQLiteDatabase db = this.getWritableDatabase();

            String queryString = "SELECT * FROM " + InventoryContract.InventoryEntry.TABLE_NAME;
            Cursor cursor = db.rawQuery(queryString, null);

            int productIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COL_PRODUCT_NAME);
            int priceIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COL_PRODUCT_PRICE);
            int quantityIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COL_PRODUCT_QUANTITY);
            int supplierIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COL_PRODUCT_SUPPLIER);
            int imageIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COL_PRODUCT_IMAGE);

            ArrayList<Inventory> inventoryList = new ArrayList<>();

            if (cursor != null && cursor.moveToFirst()) {
                do {

                    Inventory inventory = new Inventory();
                    inventory.setProductName(cursor.getString(productIndex));
                    inventory.setPrice(cursor.getInt(priceIndex));
                    inventory.setQuantity(cursor.getInt(quantityIndex));
                    inventory.setSupplier(cursor.getString(supplierIndex));
                    inventory.setImage(cursor.getString(imageIndex));

                    inventoryList.add(inventory);

                } while (cursor.moveToNext());
            }

            cursor.close();
            return inventoryList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteProduct(String productName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(InventoryContract.InventoryEntry.TABLE_NAME,
                InventoryContract.InventoryEntry.COL_PRODUCT_NAME + " = ?",
                new String[]{productName});
        db.close();
    }
}
