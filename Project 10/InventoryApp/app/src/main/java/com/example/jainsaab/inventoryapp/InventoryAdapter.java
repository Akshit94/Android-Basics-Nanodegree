package com.example.jainsaab.inventoryapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jainsaab.inventoryapp.database.InventoryDBHelper;

import java.util.ArrayList;

public class InventoryAdapter extends ArrayAdapter<Inventory> {

    public InventoryAdapter(Context context, ArrayList<Inventory> inventoryArrayList) {
        super(context, 0, inventoryArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        final Inventory inventoryItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_item_layout, parent, false);
        }
        // Lookup view for data population
        TextView productName = (TextView) convertView.findViewById(R.id.product_name);
        TextView productQty = (TextView) convertView.findViewById(R.id.product_qty);
        TextView productPrice = (TextView) convertView.findViewById(R.id.product_price);
        ImageView productImage = (ImageView) convertView.findViewById(R.id.product_image);
        Button button = (Button) convertView.findViewById(R.id.btn_sell);

        // Populate the data into the template view using the data object
        if (inventoryItem != null) {

            productName.setText(inventoryItem.getProductName());
            productQty.setText("Qty: " + inventoryItem.getQuantity());
            productPrice.setText("Rs. " + inventoryItem.getPrice());
            byte[] imageAsBytes = Base64.decode(inventoryItem.getImage(), Base64.DEFAULT);
            Bitmap bmp = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
            productImage.setImageBitmap(bmp);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InventoryDBHelper inventoryDBHelper = new InventoryDBHelper(getContext());
                    inventoryDBHelper.updateQuantity(inventoryItem.getProductName(), 1, false);
                    MainActivity.refreshDb(getContext());
                }
            });
        }
        // Return the completed view to render on screen
        return convertView;
    }
}
