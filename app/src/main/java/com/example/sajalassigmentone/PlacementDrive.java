package com.example.sajalassigmentone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class PlacementDrive extends AppCompatActivity {
    ListView lv;
    TextView name,reg,phn,cgpa,drive;
    ImageView img;
    String[] company={"Amazon","IBM","Microsoft","Samsung","Amazon","IBM","Microsoft","Samsung"};
    customlist cl;
    DatabaseReference db;
    StorageReference sd;
    Student data;
    Double cg;

    Integer[] imgid={R.drawable.amazon,R.drawable.ibm,R.drawable.microsoft,R.drawable.samsung,R.drawable.amazon,R.drawable.ibm,R.drawable.microsoft,R.drawable.samsung};

    @Override
    protected void onStart() {

        super.onStart();
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/sajalassignmentone.appspot.com/o/Profile%2Fdp.png?alt=media&token=7c604560-da02-49f3-9d97-31c8ac647af8").into(img);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_drive);

        lv=findViewById(R.id.lview);
        name=findViewById(R.id.name_received);
        phn=findViewById(R.id.phn_received);
        reg=findViewById(R.id.reg_received);
        cgpa=findViewById(R.id.cgpa_received);
        img=findViewById(R.id.img_received);
        drive=findViewById(R.id.up_drive);
        cl = new customlist(PlacementDrive.this,company,imgid);
        drive.setVisibility(View.INVISIBLE);

        sd= FirebaseStorage.getInstance().getReference();
        db= FirebaseDatabase.getInstance().getReference().child("data");
        db.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data = dataSnapshot.getValue(Student.class);
                name.setText("Name : "+ data.name);
                phn.setText("Phn no : "+data.phn);
                reg.setText("Reg no : "+data.reg);
                cg=data.cgp;
                cgpa.setText("CGPA : "+cg.toString());
                if (cg>6.6)
                {
                    lv.setAdapter(cl);
                    drive.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}