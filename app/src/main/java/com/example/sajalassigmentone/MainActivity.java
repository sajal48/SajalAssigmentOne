package com.example.sajalassigmentone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Student data;
    EditText Reg_Box,Cgpa_Box,Name_Box,Phn_Box;
    Button Save_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        Reg_Box=findViewById(R.id.regno_input);
        Cgpa_Box=findViewById(R.id.cgpa_input);
        Name_Box=findViewById(R.id.name_input);
        Phn_Box=findViewById(R.id.phn_input);
        Save_btn=findViewById(R.id.save_btn);


        Save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data=new Student(Reg_Box.getText().toString(),Name_Box.getText().toString(),Phn_Box.getText().toString(),Double.parseDouble(Cgpa_Box.getText().toString()));
                databaseReference.child("data").setValue(data);
                Intent i = new Intent(getApplicationContext(),Home.class);
                startActivity(i);
            }
        });

    }
}