package io.github.sceneview.sample.armodelviewer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity {


    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DB = new DBHelper(this);

        final EditText matricule = findViewById(R.id.matricule);
        final EditText password = findViewById(R.id.password);
        final Button loginbtn = findViewById(R.id.LoginBtn);
        final TextView registerNowBtn = findViewById(R.id.RegisternowBtn);

        registerNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //open register activity
                startActivity(new Intent(Login.this,Register.class));

            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String matriculeTxt = matricule.getText().toString();
                final String pswdTxt = password.getText().toString();

                if(matriculeTxt.isEmpty() || pswdTxt.isEmpty()){
                    Toast.makeText(Login.this,"Veuillez entrer votre Matricule ou Mot de pass!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuserpass = DB.checkUserPass(matriculeTxt,pswdTxt);
                    if (checkuserpass){
                        Toast.makeText(Login.this,"Logged in successfully!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this,"Invalid credentials!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}