package com.example.jainsaab.inventoryapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jainsaab.inventoryapp.database.InventoryDBHelper;

public class DetailsActivity extends AppCompatActivity {
    Bundle extras;
    int mQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView productName = (TextView) findViewById(R.id.detail_product_name);
        TextView supplierName = (TextView) findViewById(R.id.detail_supplier);
        TextView productPrice = (TextView) findViewById(R.id.detail_price);
        final TextView productQty = (TextView) findViewById(R.id.detail_qty);

        ImageView detailImage = (ImageView) findViewById(R.id.detail_img);

        final EditText sellEditText = (EditText) findViewById(R.id.detail_sell_et);
        Button sellButton = (Button) findViewById(R.id.detail_sell_btn);

        final EditText receiveEditText = (EditText) findViewById(R.id.detail_receive_et);
        Button receiveButton = (Button) findViewById(R.id.detail_receive_btn);

        Button orderMore = (Button) findViewById(R.id.order_more);

        extras = getIntent().getExtras();

        if (extras == null) {
            return;
        } else {
            productName.setText(extras.getString("ProductName"));
            supplierName.setText(extras.getString("Supplier"));
            productPrice.setText("Price: Rs. " + extras.getInt("Price"));
            productQty.setText("Qty: " + extras.getInt("Quantity"));

            byte[] imageAsBytes = Base64.decode(extras.getString("Image"), Base64.DEFAULT);
            Bitmap bmp = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
            detailImage.setImageBitmap(bmp);

            mQuantity = extras.getInt("Quantity");
        }

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (sellEditText.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter quantity!", Toast.LENGTH_SHORT).show();
                } else {
                    int sellQty = Integer.parseInt(sellEditText.getText().toString());
                    if (sellQty > mQuantity) {
                        Toast.makeText(getApplicationContext(), "You don't have enough to sell!", Toast.LENGTH_SHORT).show();
                    } else {
                        InventoryDBHelper inventoryDBHelper = new InventoryDBHelper(getApplicationContext());
                        inventoryDBHelper.updateQuantity(extras.getString("ProductName"), sellQty, false);
                        mQuantity = mQuantity - sellQty;
                        productQty.setText("Qty: " + mQuantity);
                        MainActivity.refreshDb(getApplicationContext());
                    }
                }

            }
        });

        receiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (receiveEditText.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter quantity!", Toast.LENGTH_SHORT).show();
                } else {
                    int receiveQty = Integer.parseInt(receiveEditText.getText().toString());
                    InventoryDBHelper inventoryDBHelper = new InventoryDBHelper(getApplicationContext());
                    inventoryDBHelper.updateQuantity(extras.getString("ProductName"), receiveQty, true);
                    mQuantity = mQuantity + receiveQty;
                    productQty.setText("Qty: " + mQuantity);
                    MainActivity.refreshDb(getApplicationContext());
                }

            }
        });

        orderMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String subject = "Ordering more supply.";
                String message = "Ordering supply for " + extras.getString("ProductName");
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{
                        extras.getString("Supplier")});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
                emailIntent.setType("text/plain");
                startActivity(emailIntent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_delete) {
            showDialog(0);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 0) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setTitle("Deleting");
            builder.setMessage("Are you sure you want to delete this product?");
            builder.setCancelable(true);

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    InventoryDBHelper inventoryDBHelper = new InventoryDBHelper(getApplicationContext());
                    inventoryDBHelper.deleteProduct(extras.getString("ProductName"));
                    MainActivity.refreshDb(getApplicationContext());
                    finish();
                }
            });
            return builder.create();
        }
        return super.onCreateDialog(id);
    }
}
