package com.example.mercerph;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import io.paperdb.Paper;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class CatalogPage extends AppCompatActivity {

    NavigationView navmenu;
    LinearLayout outlay;
    int[] itempics = new int[]{R.drawable.flagshipteeblackduo,
                               R.drawable.visualsteeblackduo,
                               R.drawable.flagshipteeblacksolo,
                               R.drawable.visualsteeblacksolo,
                               R.drawable.logohoodieblack,
                               R.drawable.logoteeblack};

    String[] itemname = new String[]{"FLAGSHIP TEE (DUO)","VISUALS TEE (DUO)","FLAGSHIP TEE (SOLO)","VISUALS TEE (SOLO)","LOGO HOODIE","LOGO TEE"};
    int[] itemprice = new int[]{649,649,649,649,990,590};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog_page);

        outlay = findViewById(R.id.outlay);
        navmenu = findViewById(R.id.nav_menu);

        navmenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                switch (id)
                {
                    case R.id.profile:
                        startActivity(new Intent(CatalogPage.this,ProfilePage.class));
                        break;
                    case R.id.about:
                        startActivity(new Intent(CatalogPage.this,HomeActivity.class));
                        break;
                    case R.id.catalog:
                        startActivity(new Intent(CatalogPage.this,CatalogPage.class));
                        break;
                    case R.id.logout:
                        Paper.book().destroy();

                        Intent intent = new Intent(CatalogPage.this, MainActivity.class); //sends the user to the Homepage of the App
                        startActivity(intent);
                        break;
                    default:
                        return true;
                }

                return true;
            }
        });

        displayitems();
    }

    public void displayitems()
    {
        for(int x=0; x<6; x++)
        {
            LinearLayout layout = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(850, 700);
            layoutParams.setMargins(0,40,0,0);
            layoutParams.gravity = Gravity.CENTER;
            layout.setLayoutParams(layoutParams);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setBackgroundResource(R.drawable.black_curve);

            ImageView imgitem = new ImageView(this);
            LinearLayout.LayoutParams itemlp = new LinearLayout.LayoutParams(790, 450);
            itemlp.setMargins(30, 30, 40, 0);
            imgitem.setLayoutParams(itemlp);
            imgitem.setBackgroundResource(R.drawable.white_curve);
            imgitem.setImageResource(itempics[x]);
            imgitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gotoitem();
                }
            });

            LinearLayout textlay = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(850, 300);
            textlay.setLayoutParams(layoutParams1);
            textlay.setOrientation(LinearLayout.HORIZONTAL);

            TextView designtext = new TextView(this);
            LinearLayout.LayoutParams designlp = new LinearLayout.LayoutParams(140, 140);
            designlp.setMargins(50, 5, 5, 0);
            designtext.setLayoutParams(designlp);
            designtext.setBackgroundResource(R.drawable.white_circle);

            TextView imgtext = new TextView(this);
            LinearLayout.LayoutParams textlp = new LinearLayout.LayoutParams(700,LinearLayout.LayoutParams.MATCH_PARENT);
            textlp.setMargins(70, 60, 0, 0);
            imgtext.setLayoutParams(textlp);
            imgtext.setText(itemname[x]);
            imgtext.setTextSize(23);
            imgtext.setTextColor(Color.WHITE);

            textlay.addView(designtext);
            textlay.addView(imgtext);

            layout.addView(imgitem);
            layout.addView(textlay);

            outlay.addView(layout);
        }
    }

    public void shopcart(View v)
    {
        startActivity(new Intent(CatalogPage.this,CartPage.class));
    }

    public void menu(View v){

        if(navmenu.getVisibility() == View.INVISIBLE)
            navmenu.setVisibility(View.VISIBLE);
        else
            navmenu.setVisibility(View.INVISIBLE);
    }

    public void close(View v)
    {
        navmenu.setVisibility(View.INVISIBLE);
    }

    public void gotoitem()
    {
        startActivity(new Intent(CatalogPage.this,ItemDetail.class));
    }

}
