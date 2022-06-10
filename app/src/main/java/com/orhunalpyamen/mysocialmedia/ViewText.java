package com.orhunalpyamen.mysocialmedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ViewText extends AppCompatActivity {
    ArrayList<Notes> noteList = new ArrayList<>();
    RecyclerView rvNote;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    EditText editTextTextPersonName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_text);
        rvNote = findViewById(R.id.recyclerView3);
        F_GetList();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        editTextTextPersonName=findViewById(R.id.editTextTextPersonName);

    }



    public void btnAra(View view) {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        noteList = db.getNoteListSearch(editTextTextPersonName.getText().toString());
        NoteAdapter adp = new NoteAdapter(this, noteList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvNote.setLayoutManager(layoutManager);
        rvNote.setHasFixedSize(true);
        rvNote.setAdapter(adp);

        //adp.setOnItemClickListener(onItemNoteClickListener);

        db.close();
    }

    void F_GetList() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        noteList = db.getNoteListAll();

        NoteAdapter adp = new NoteAdapter(this, noteList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvNote.setLayoutManager(layoutManager);
        rvNote.setHasFixedSize(true);
        rvNote.setAdapter(adp);

        //adp.setOnItemClickListener(onItemNoteClickListener);

        db.close();
    }


    };




