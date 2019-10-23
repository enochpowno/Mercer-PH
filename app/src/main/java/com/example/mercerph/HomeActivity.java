package com.example.mercerph;

import androidx.appcompat.app.AppCompatActivity;
import io.paperdb.Paper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button LogoutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        LogoutButton = (Button) findViewById(R.id.logout_button);

        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Paper.book().destroy();

                Intent intent = new Intent(HomeActivity.this, MainActivity.class); //sends the user to the Homepage of the App
                startActivity(intent);

            }
        });
    }
}
