package com.example.mercerph;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.Inet4Address;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button createAccountButton;
    private EditText InputName, InputUsername, InputPhoneNumber, InputPassword, InputEmail, InputAddress;
    private ProgressDialog loadingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        createAccountButton = (Button) findViewById(R.id.register_button);
        InputName = (EditText) findViewById(R.id.register_fullname_input);
        InputUsername = (EditText) findViewById(R.id.register_username_input);
        InputPhoneNumber = (EditText) findViewById(R.id.register_phone_number_input);
        InputPassword = (EditText) findViewById(R.id.register_password_input);
        InputEmail = (EditText) findViewById(R.id.register_email_input);
        InputAddress = (EditText) findViewById(R.id.register_address_input);
        loadingBar = new ProgressDialog(this);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }

        private void createAccount() {

            String name = InputName.getText().toString();
            String username = InputUsername.getText().toString();
            String phone = InputPhoneNumber.getText().toString();
            String password = InputPassword.getText().toString();
            String email = InputEmail.getText().toString();
            String address = InputAddress.getText().toString();

            if (TextUtils.isEmpty(name))
            {
                Toast.makeText(this, "Please Write Your Full Name" , Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(username))
            {
                Toast.makeText(this, "Please Write Your Username" , Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(phone))
            {
                Toast.makeText(this, "Please Write Your Mobile Number" , Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(password))
            {
                Toast.makeText(this, "Please Write Your Password" , Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(email))
            {
                Toast.makeText(this, "Please Write Your Email Address" , Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(address))
            {
                Toast.makeText(this, "Please Write Your Home Address" , Toast.LENGTH_SHORT).show();
            }
            else
            {
                loadingBar.setTitle("Create Account");
                loadingBar.setMessage("Please wait");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();

                ValidateUser(name, username, phone, password, email, address);
            }
        }

        private void ValidateUser(final String name, final String username, final String phone, final String password, final String email, final String address)
        {
            final DatabaseReference RootRef;
            RootRef = FirebaseDatabase.getInstance().getReference();

            RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    if (!(dataSnapshot.child("Users").child(username).exists()))
                    {
                        HashMap<String, Object> userdataMap = new HashMap<>();
                        userdataMap.put("name", name);
                        userdataMap.put("username", username);
                        userdataMap.put("phone", phone);
                        userdataMap.put("password", password);
                        userdataMap.put("email", email);
                        userdataMap.put("address", address);

                        RootRef.child("Users").child(username).updateChildren(userdataMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task)
                                    {
                                        if (task.isSuccessful())
                                        {
                                            Toast.makeText(RegisterActivity.this, "Welcome! Your account has been created", Toast.LENGTH_SHORT).show();
                                            loadingBar.dismiss();

                                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                            startActivity(intent);

                                        }
                                        else
                                        {
                                            loadingBar.dismiss();
                                            Toast.makeText(RegisterActivity.this, "Network Error: Please try again!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "This " + username + " already exists!", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                        Toast.makeText(RegisterActivity.this, "Please use another Username", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

}
