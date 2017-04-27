package com.example.jainsaab.inventoryapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddProductActivity extends AppCompatActivity {
    public static final int REQUEST_PICK_IMAGE = 1;
    private String mProductImage;
    ImageView mUploadedImage;
    EditText mProductName, mProductQty, mProductPrice, mProductSupplier;
    Button mAddBtn;
//    public static InventoryDBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        mProductName = (EditText) findViewById(R.id.product_name_edit_text);
        mProductQty = (EditText) findViewById(R.id.product_qty_edit_text);
        mProductPrice = (EditText) findViewById(R.id.product_price_edit_text);
        mProductSupplier = (EditText) findViewById(R.id.product_supplier_edit_text);
        mAddBtn = (Button) findViewById(R.id.add_btn);

//        mDBHelper = new InventoryDBHelper(getApplicationContext());

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mProductName.getText().toString().equals("") || mProductQty.getText().toString().equals("") ||
                        mProductPrice.getText().toString().equals("") || mProductSupplier.getText().toString().equals("") ||
                        mProductImage == null) {

                    Toast.makeText(getApplicationContext(), "Please fill in all the fields and make sure to upload a picture.", Toast.LENGTH_SHORT).show();

                } else {

                    String productName = mProductName.getText().toString();
                    String supplierName = mProductSupplier.getText().toString();
                    int productPrice = Integer.parseInt(mProductPrice.getText().toString());
                    int productQuantity = Integer.parseInt(mProductQty.getText().toString());


                    MainActivity.mDbHelper.insert(productName, productPrice, productQuantity, supplierName, mProductImage);
                    MainActivity.refreshDb(getApplicationContext());
                    MainActivity.mInventoryAdapter.notifyDataSetChanged();

                    mProductName.setText("");
                    mProductQty.setText("");
                    mProductPrice.setText("");
                    mProductSupplier.setText("");
                    mUploadedImage.setVisibility(View.GONE);

                    Toast.makeText(getApplicationContext(), "New Product Added.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mUploadedImage = (ImageView) findViewById(R.id.uploaded_img);
        mUploadedImage.setVisibility(View.GONE);

        Button uploadImgBtn = (Button) findViewById(R.id.upload_img_btn);
        uploadImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(i, REQUEST_PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == -1 && data != null) {
            Toast.makeText(getApplicationContext(), "Image successfully uploaded", Toast.LENGTH_SHORT).show();
            Uri selectedImage = data.getData();

            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), selectedImage);

                bitmap = Bitmap.createScaledBitmap(bitmap, 200, 200, false);
                mUploadedImage.setImageBitmap(bitmap);
                mUploadedImage.setVisibility(View.VISIBLE);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

                byte[] byteArray = stream.toByteArray();

                mProductImage = Base64.encodeToString(byteArray, Base64.DEFAULT);


            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(getApplicationContext(), "Error Uploading Image", Toast.LENGTH_LONG).show();
        }
    }
}
