package com.example.mercerph;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import io.paperdb.Paper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mercerph.Controller.Prevalent;
import com.example.mercerph.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

public class LoginActivity extends AppCompatActivity {

    private EditText InputUsername, InputPassword, InputAdminPhone;
    private Button LoginButton;
    private ProgressDialog loadingBar;
    private TextView AdminLink, NotAdminLink;

    private CheckBox checkBoxRememberMe;
    private String parentDbName = "Users";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton = (Button) findViewById(R.id.login_button);
        InputUsername = (EditText) findViewById(R.id.login_username_input);
        InputAdminPhone = (EditText) findViewById(R.id.login_admin_phone_input);
        InputPassword = (EditText) findViewById(R.id.login_password_input);
        AdminLink = (TextView) findViewById(R.id.admin_panel_link);
        NotAdminLink = (TextView) findViewById(R.id.not_admin_panel_link);
        loadingBar = new ProgressDialog(this);
        checkBoxRememberMe = (CheckBox) findViewById(R.id.remember_me);
        Paper.init(this);




        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                LoginUser();
            }
        });

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                LoginButton.setText("Login as Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
               // InputUsername.setVisibility(View.INVISIBLE);
               // InputAdminPhone.setVisibility(View.VISIBLE);
               // parentDbName = "Admin";
            }
        });

        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                LoginButton.setText("Login");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
                InputUsername.setVisibility(View.VISIBLE);
                //InputAdminPhone.setVisibility(View.VISIBLE);
                parentDbName = "Users";
            }
        });

    }

    private void LoginUser()
    {
        String username = InputUsername.getText().toString();
        String password = InputPassword.getText().toString();
        //String phonenumber = InputAdminPhone.getText().toString();

        if (TextUtils.isEmpty(username))
        {
            Toast.makeText(this, "Please Write Username" , Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please Write Your Password" , Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessToAccount(username, password);
        }

    }

    private void AllowAccessToAccount(final String username, final String password)
    {

        if(checkBoxRememberMe.isChecked())
        {
            Paper.book().write(Prevalent.UserNameKey, username);
            Paper.book().write(Prevalent.UserPasswordKey, password);
        }


        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.child(parentDbName).child(username).exists())
                {
                    Users usersData = dataSnapshot.child(parentDbName).child(username).getValue(Users.class);

                    //retrieve the users data by setters and getters
                    if (usersData.getUsername().equals(username) && !(usersData.getUsername().equals("Admin")))
                    {
                         //check the password once user exists
                        if (usersData.getPassword().equals(password))
                        {
                            if(parentDbName.equals("Users"))
                            {
                                Toast.makeText(LoginActivity.this, "Logged in Successfully! ", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(LoginActivity.this, CatalogPage.class); //sends the user to the Homepage of the App
                                startActivity(intent);

                            }
                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Password is incorrect!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if (usersData.getUsername().equals("Admin"))
                    {

                        Toast.makeText(LoginActivity.this, "Welcome Boss!", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();

                        Intent intent = new Intent(LoginActivity.this, AdminCategoryActivity.class);
                        startActivity(intent);
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Account with this " + username + " does not exist! ", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });

    }
}
