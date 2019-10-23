package com.example.mercerph;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import io.paperdb.Paper;

public class CheckoutPage extends AppCompatActivity {
    NavigationView navmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_page);
        navmenu = findViewById(R.id.nav_menu);

        navmenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                switch (id)
                {
                    case R.id.profile:
                        startActivity(new Intent(CheckoutPage.this,ProfilePage.class));
                        break;
                    case R.id.about:
                        startActivity(new Intent(CheckoutPage.this,HomeActivity.class));
                        break;
                    case R.id.catalog:
                        startActivity(new Intent(CheckoutPage.this,CatalogPage.class));
                        break;
                    case R.id.logout:
                        Paper.book().destroy();

                        Intent intent = new Intent(CheckoutPage.this, MainActivity.class); //sends the user to the Homepage of the App
                        startActivity(intent);
                        break;
                    default:
                        return true;
                }

                return true;
            }
        });

    }

    public void shopcart(View v)
    {
        startActivity(new Intent(CheckoutPage.this,CartPage.class));
    }

    public void menu(View v){

        if(navmenu.getVisibility() == View.INVISIBLE)
            navmenu.setVisibility(View.VISIBLE);
        else
            navmenu.setVisibility(View.INVISIBLE);
    }
}
