package com.example.fa_jayesh_c0880752_android;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText productID, productDesc, productName, productPrice;
    private Button addProductBtn,viewProductBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productID = findViewById(R.id.productID);
        productDesc = findViewById(R.id.productDesc);
        productName = findViewById(R.id.productName);
        productPrice = findViewById(R.id.productPrice);
        addProductBtn = findViewById(R.id.addProductBtn);
        viewProductBtn = findViewById(R.id.viewProduct);

        dbHandler = new DBHandler(MainActivity.this);

        addProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String courseName = productID.getText().toString();
                String courseTracks = productDesc.getText().toString();
                String courseDuration = productName.getText().toString();
                String courseDescription = productPrice.getText().toString();

                if (courseName.isEmpty() && courseTracks.isEmpty() && courseDuration.isEmpty() && courseDescription.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addNewCourse(courseName, courseDuration, courseDescription, courseTracks);
                Toast.makeText(MainActivity.this, "Product has been added.", Toast.LENGTH_SHORT).show();
                productID.setText("");
                productName.setText("");
                productDesc.setText("");
                productPrice.setText("");
            }
        });

        viewProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor viewProdoct = dbHandler.getReadableDatabase().rawQuery("Select * From productDB", null);
                viewProdoct.moveToFirst();
                String productID = viewProdoct.getString(0);
                String productName = viewProdoct.getString(1);
                String productDesc = viewProdoct.getString(2);
                String productPrice = viewProdoct.getString(3);

                Intent intent = new Intent(getApplicationContext(), Activity2.class);
                intent.putExtra("productID",productID);
                intent.putExtra("productName", productName);
                intent.putExtra("productDesc", productDesc);
                intent.putExtra("productPrice", productPrice);
                startActivity(intent);

            }
        });
    }
}

