package com.example.practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUp extends AppCompatActivity {
    EditText name,email,password,rpassword,username;
    Button button;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        email=findViewById(R.id.reg_email);
        button=findViewById(R.id.sign_button);
        password=findViewById(R.id.reg_password);
        rpassword=findViewById(R.id.reg_rpassword);
        name=findViewById(R.id.reg_name);
        username=findViewById(R.id.reg_username);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if
                    (!validateEmail(email)| !validateUsername(username) | !validateName(name) | !validatePassword(password) | !validatePasswords(rpassword))
                    return;

                else

                    rootNode=FirebaseDatabase.getInstance();
                    reference=rootNode.getReference("users");

                String nameInput = name.getText().toString();
                String emailInput = email.getText().toString();
                String passwordInput = password.getText().toString();
                String usersInput = username.getText().toString();


                UserHelperClass helperClass=new UserHelperClass(nameInput,emailInput,passwordInput,usersInput);
                    reference.child(usersInput).setValue(helperClass);
                    openActivity2();



            }




        });








        }


        private boolean validateName(EditText name){
        String nameInput = name.getText().toString();
            if(!nameInput.isEmpty()){
//            Toast.makeText(this, "Validated", Toast.LENGTH_SHORT).show();
                return true;
            }
            else {
                Toast.makeText(this, "name req", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        private boolean validateEmail(EditText email){
        String emailInput= email.getText().toString();


        if(!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
//            Toast.makeText(this, "Validated", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            Toast.makeText(this, "Not Validated", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

        private boolean validatePassword(EditText password){
        String val= password.getText().toString();
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

        private boolean validatePasswords(EditText rpassword ){
        String val= rpassword.getText().toString();
        String newPass= password.getText().toString();



        if(!val.isEmpty() && val.matches(newPass)){
//            Toast.makeText(this, "Password matches", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            Toast.makeText(this, "Not matches", Toast.LENGTH_SHORT).show();
            return false;
        }
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

        public void openActivity2(){
            Intent intent = new Intent(this,NewLogin.class);
            startActivity(intent);
        }



    }
