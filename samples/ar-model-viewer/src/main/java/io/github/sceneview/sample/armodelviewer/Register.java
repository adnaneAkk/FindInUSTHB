package io.github.sceneview.sample.armodelviewer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Register extends AppCompatActivity {



    //create object of DB refrence class to access firebase's realtime db


//    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        DB = new DBHelper(this);

        final EditText fullname = findViewById(R.id.fullname);
        final EditText matricule = findViewById(R.id.matricule);
        final EditText password = findViewById(R.id.password);
        final EditText confirmPassword = findViewById(R.id.Confpassword);
        final Button register = findViewById(R.id.registerBtn);
        final TextView loginNowButton = findViewById(R.id.LoginNowBtn);




        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String fullnameTxt = fullname.getText().toString();
                final String MatriculeTxt = matricule.getText().toString();
                final String passwordTxt = password.getText().toString();
                final String confPasswordTxt = confirmPassword.getText().toString();

                if(MatriculeTxt.isEmpty() ||fullnameTxt.isEmpty() ||confPasswordTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText(Register.this,"Please fill all fields.",Toast.LENGTH_SHORT).show();
                }
                else if(!passwordTxt.equals(confPasswordTxt)){
                    Toast.makeText(Register.this,"Passwords should match.",Toast.LENGTH_SHORT).show();

                }
                else {

                    boolean checkMatricule = DB.CheckUsername(MatriculeTxt);
                    if (!checkMatricule) {
                        Boolean insert = DB.insertData(MatriculeTxt, passwordTxt);
                        if (insert)
                            Toast.makeText(Register.this, "reigstered succesfully!.", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(Register.this, "Registration failed.", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(Register.this, "User already exists. please sign in.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),Login.class);
                        startActivity(intent);
                    }

                }
            }
        });


        loginNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}