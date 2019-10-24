package com.example.mercerph;

import androidx.appcompat.app.AppCompatActivity;
import io.paperdb.Paper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class AdminAddNewProductActivity extends AppCompatActivity {

    private Button HomeButton;
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);

        categoryName = getIntent().getExtras().get("category").toString();

        Toast.makeText(this, categoryName, Toast.LENGTH_SHORT).show();


        HomeButton = (Button) findViewById(R.id.HelloButton);

        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().destroy();

                Intent intent = new Intent(AdminAddNewProductActivity.this, MainActivity.class); //sends the admin to the Homepage of the App
                startActivity(intent);
            }
        });

        Toast.makeText(this, "Welcome BOSS", Toast.LENGTH_SHORT).show();

    }
}
