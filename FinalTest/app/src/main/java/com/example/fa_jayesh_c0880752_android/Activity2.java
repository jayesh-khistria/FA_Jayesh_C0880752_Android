package com.example.fa_jayesh_c0880752_android;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Activity2 extends AppCompatActivity {

    private ListView listView;
    private DBHandler databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        databaseHelper = new DBHandler(this);
        Cursor data = databaseHelper.getReadableDatabase().rawQuery("Select * From TABLE_NAME", null);

        ArrayList<HashMap<String, String>> userList = (ArrayList<HashMap<String, String>>) databaseHelper.getReadableDatabase().rawQuery("Select * From productDB", null);
        ListView lv = (ListView) findViewById(R.id.list_view);
        ListAdapter adapter = new SimpleAdapter(Activity2.this, userList, R.layout.product_cell,new String[]{"name","designation","location"}, new int[]{R.id.row_iD, R.id.row_name, R.id.row_desc});
        lv.setAdapter(adapter);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, (List) data);

        listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter1);
    }
}