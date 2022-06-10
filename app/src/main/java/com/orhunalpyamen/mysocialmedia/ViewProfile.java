package com.orhunalpyamen.mysocialmedia;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class ViewProfile extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    EditText editTextTextPersonName2;
    ProfileRcyclerAdapter profileRcyclerAdapter;


    ArrayList<String> userEmailFromFB2;
    ArrayList<String> userCommentFromFB2;
    ArrayList<String> userImageFromFB2;
    ArrayList<String> userIDFromFB2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        editTextTextPersonName2=findViewById(R.id.editTextTextPersonName2);

        firebaseAuth=FirebaseAuth.getInstance();

        userEmailFromFB2=new ArrayList<>();
        userCommentFromFB2=new ArrayList<>();
        userImageFromFB2=new ArrayList<>();
        userIDFromFB2=new ArrayList<>();

        firebaseFirestore=FirebaseFirestore.getInstance();

        RecyclerView recyclerView=findViewById(R.id.rcycl);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         profileRcyclerAdapter=new ProfileRcyclerAdapter(userEmailFromFB2,userCommentFromFB2,userImageFromFB2,userIDFromFB2);
         recyclerView.setAdapter(profileRcyclerAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(ViewProfile.this,feedActivity.class);
        startActivity(intent);
    }

    public void listele(View view){
        getData();
    }

    public void getData(){

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String userEmail = firebaseUser.getEmail();



        if (editTextTextPersonName2.getText().toString().equals("")){
            CollectionReference collectionReference = firebaseFirestore.collection("Posts");

            collectionReference.whereEqualTo("useremail",userEmail).addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                    if (e != null) {
                        Toast.makeText(ViewProfile.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                    }

                    if (queryDocumentSnapshots != null) {

                        for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {

                            Map<String,Object> data = snapshot.getData();

                            //Casting
                            String comment = (String) data.get("comment");
                            String userEmail = (String) data.get("useremail");
                            String downloadUrl = (String) data.get("downloadurl");
                            String id = (String) snapshot.getId();
                            System.out.println(id);

                            userCommentFromFB2.add(comment);
                            userEmailFromFB2.add(userEmail);
                            userImageFromFB2.add(downloadUrl);
                            userIDFromFB2.add(id);

                        }
                        profileRcyclerAdapter.notifyDataSetChanged();

                    }

                }
            });

        }

        else{
            CollectionReference collectionReference = firebaseFirestore.collection("Posts");

            collectionReference.whereEqualTo("useremail",userEmail).whereEqualTo("comment",editTextTextPersonName2.getText().toString()).addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                    if (e != null) {
                        Toast.makeText(ViewProfile.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                    }

                    if (queryDocumentSnapshots != null) {

                        for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {

                            Map<String,Object> data = snapshot.getData();

                            //Casting
                            String comment = (String) data.get("comment");
                            String userEmail = (String) data.get("useremail");
                            String downloadUrl = (String) data.get("downloadurl");
                            String id = (String) snapshot.getId();


                            userCommentFromFB2.add(comment);
                            userEmailFromFB2.add(userEmail);
                            userImageFromFB2.add(downloadUrl);
                            userIDFromFB2.add(id);

                        }
                        profileRcyclerAdapter.notifyDataSetChanged();

                    }

                }
            });

        }



    }






}