package com.orhunalpyamen.mysocialmedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AddText extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;


    EditText etNote;
    Button btnSave, btnList, btnDelete, btnEdit;
    RecyclerView rvNote;
    String noteID = "";
    ArrayList<Notes> noteList = new ArrayList<>();

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_text);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        textView = findViewById(R.id.textView);
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String userEmail = firebaseUser.getEmail();

        textView.setText(userEmail.toString());


        definitions();
    }

    private void definitions() {
        etNote = findViewById(R.id.etNote);
        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.btnEdit);
        btnList = findViewById(R.id.btnList);
        btnSave = findViewById(R.id.btnSave);
        rvNote = findViewById(R.id.recyclerView2);

    }

    public void btn_List_CLick(View view) {
        F_GetList();
    }

    void F_GetList() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        noteList = db.getNoteList(textView.getText().toString()); 

        NoteAdapter adp = new NoteAdapter(this, noteList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvNote.setLayoutManager(layoutManager);
        rvNote.setHasFixedSize(true);
        rvNote.setAdapter(adp);

        adp.setOnItemClickListener(onItemNoteClickListener);
    }

    View.OnClickListener onItemNoteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int i = viewHolder.getAdapterPosition();
            Notes item = noteList.get(i);

            etNote.setText(item.getNote_text());
            noteID = item.getNote_id();
        }
    };

    public void btn_Save_Click(View view) {
        if (!etNote.getText().toString().trim().equals("")) {
            DatabaseHelper db = new DatabaseHelper(getApplicationContext());
            db.addNote(textView.getText().toString(), etNote.getText().toString());

            db.close();
            etNote.setText("");
        }
    }

    public void btn_Delete_Click(View view) {
        if (!noteID.equals("")) {
            DatabaseHelper db = new DatabaseHelper(getApplicationContext());
            db.deleteNote(noteID);
            db.close();

            Toast.makeText(getApplicationContext(), "Not silindi", Toast.LENGTH_SHORT).show();
            noteID = "";
            etNote.setText("");
            F_GetList();
        } else
            Toast.makeText(getApplicationContext(), "Lütfen silinecek notu seçiniz", Toast.LENGTH_SHORT).show();
    }

    public void btn_Edit_Click(View view) {


        String mail1 = textView.getText().toString();
        String text = etNote.getText().toString();

        if (mail1.matches("") || text.matches("")) {
            Toast.makeText(getApplicationContext(), "Tüm Bilgileri Eksiksiz Doldurunuz", Toast.LENGTH_LONG).show();
        } else {
            DatabaseHelper db = new DatabaseHelper(getApplicationContext());
            db.duzenle(mail1, text, Integer.parseInt(noteID));//gönderdiğimiz id li kitabın değperlerini güncelledik.
            db.close();
            Toast.makeText(getApplicationContext(), "Notunuz Başarıyla Düzenlendi.", Toast.LENGTH_LONG).show();
        }
    }
}