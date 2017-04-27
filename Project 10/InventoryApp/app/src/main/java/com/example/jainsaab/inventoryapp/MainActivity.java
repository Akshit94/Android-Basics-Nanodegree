package com.example.jainsaab.inventoryapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jainsaab.inventoryapp.database.InventoryDBHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ListView mProductListView;
    public static InventoryAdapter mInventoryAdapter;
    public static ArrayList<Inventory> mInventoryList = new ArrayList<>();
    public static InventoryDBHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        mProductListView = (ListView) findViewById(R.id.product_listview);
        TextView emptyView = (TextView) findViewById(R.id.empty_view);
        mProductListView.setEmptyView(emptyView);

        mProductListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent detailIntent = new Intent(getApplicationContext(), DetailsActivity.class);
                detailIntent.putExtra("ProductName", mInventoryList.get(i).getProductName());
                detailIntent.putExtra("Quantity", mInventoryList.get(i).getQuantity());
                detailIntent.putExtra("Price", mInventoryList.get(i).getPrice());
                detailIntent.putExtra("Supplier", mInventoryList.get(i).getSupplier());
                detailIntent.putExtra("Image", mInventoryList.get(i).getImage());
                startActivity(detailIntent);
            }
        });

        mDbHelper = new InventoryDBHelper(getApplicationContext());
        refreshDb(getApplicationContext());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addProductIntent = new Intent(getApplicationContext(), AddProductActivity.class);
                startActivity(addProductIntent);
            }
        });

        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getApplicationContext(), "Add a new product.", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void refreshDb(Context context) {
        mInventoryList.clear();
        mInventoryList = mDbHelper.getProducts();
        mInventoryAdapter = new InventoryAdapter(context, mInventoryList);
        mProductListView.setAdapter(mInventoryAdapter);
        mInventoryAdapter.notifyDataSetChanged();
    }
}
