package com.example.mercerph;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class ItemDetail extends AppCompatActivity {

    Spinner colorspin, sizespin;
    ImageView itemview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        colorspin = findViewById(R.id.colorspin);
        sizespin = findViewById(R.id.sizespin);
        itemview = findViewById(R.id.itemview);

        ArrayAdapter<String> coloradapt = new ArrayAdapter<String>(ItemDetail.this,
                R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.Colors));
        coloradapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorspin.setAdapter(coloradapt);

        colorspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString().equals("White"))
                    itemview.setImageResource(R.drawable.logohoodiewhite);
                else
                    itemview.setImageResource(R.drawable.logohoodieblack);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> sizeadapt = new ArrayAdapter<String>(ItemDetail.this,
                R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.Sizes));
        sizeadapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizespin.setAdapter(sizeadapt);

        sizespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void addtocart(View v)
    {
        Toast.makeText(this, "Added To Cart!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ItemDetail.this, CatalogPage.class));
    }

    public void backer(View v)
    {
        finish();
    }
}
