package com.orhunalpyamen.mysocialmedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GirisActivity extends AppCompatActivity {
    EditText emailText;
    EditText passwordText;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();
        emailText=findViewById(R.id.emailText);
        passwordText=findViewById(R.id.passwordText);

        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

        if (firebaseUser != null){
            Intent intent=new Intent(GirisActivity.this,feedActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public void sıgnIn(View view){
        String email=emailText.getText().toString().trim();
        String password=passwordText.getText().toString().trim();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Intent intent=new Intent(GirisActivity.this,feedActivity.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(GirisActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();

            }
        });
    }

    public void sıgnUp(View view){
        String email=emailText.getText().toString().trim();
        String password=passwordText.getText().toString().trim();

        if(email.equals("") || password.equals("")){

            Toast.makeText(GirisActivity.this, "Lütfen mail adresi ve şifre girin",Toast.LENGTH_LONG).show();

        }
        else{


            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {

                    Toast.makeText(GirisActivity.this,"Başarılı",Toast.LENGTH_LONG).show();

                    Intent intent=new Intent(GirisActivity.this,feedActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(GirisActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }
            });

        }


    }

}