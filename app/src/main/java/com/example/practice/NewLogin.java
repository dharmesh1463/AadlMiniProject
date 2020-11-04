package com.example.practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;


public class NewLogin extends AppCompatActivity {
    Button regbtn,loginbtn;
    EditText Username,Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        regbtn=findViewById(R.id.regbtn);
        loginbtn=findViewById(R.id.loginbtn);
        Username=findViewById(R.id.username);
        Password=findViewById(R.id.password);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if
                    (!validateUsername(Username) |  !validatePassword(Password))
                    return ;
                else
                    isUser();


            }
        });


    }

    private void isUser() {

        final String userEnteredName = Username.getText().toString().trim();
        final String userEnteredPassword = Password.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                Log.d("tag1", "Value is: " + map);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("2", "Failed to read value.", error.toException());
            }
        });
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredName);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot messageSnapshot: snapshot.getChildren()) {
                        String passwordFromDB = (String) messageSnapshot.child("password").getValue();
                        //Log.d("pass", "onDataChange: "+passwordFromDB);
                    }
                    Username.setError(null);

                    String passwordFromDB = snapshot.child(userEnteredName).child("password").getValue(String.class);
                    if(passwordFromDB.equals(userEnteredPassword)){
                        Username.setError(null);
                        openActivity();
                    }
                    else {
                        Password.setError("Wrong Password");
                        Password.requestFocus();
                    }
                }
                else{
                    Username.setError("User doesnot exist");
                    Username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private boolean validateUsername(EditText username){
        String userInput = username.getText().toString();
        if(!userInput.isEmpty()){
//            Toast.makeText(this, "Validated", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            Toast.makeText(this, "username req", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    private boolean validatePassword(EditText Password){
        String val= Password.getText().toString();
        String passwordVal = "^" + "(?=.*[A-Za-z])" + "(?=.*[@$!%*#?&])" + ".{8,}" + "$";


        if(!val.isEmpty() && val.matches(passwordVal)){
//            Toast.makeText(this, "Strong Password", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            Toast.makeText(this, "Weak Password", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void openActivity1(){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
    public void openActivity(){
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
    }
}