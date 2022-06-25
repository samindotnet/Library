package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    EditText txt_title, txt_author, txt_pages;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        txt_title = findViewById(R.id.txtTitle);
        txt_author = findViewById(R.id.txtAuthor);
        txt_pages = findViewById(R.id.txtPages);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(AddActivity.this);
                db.Addbook(txt_title.getText().toString().trim(),
                        txt_author.getText().toString().trim(),
                        Integer.valueOf(txt_pages.getText().toString().trim()));
            }
        });
    }
}