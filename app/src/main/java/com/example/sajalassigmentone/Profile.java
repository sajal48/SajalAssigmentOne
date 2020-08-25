package com.example.sajalassigmentone;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class Profile extends AppCompatActivity {
    public StorageReference ref;
    ImageView imageView;
    Button pic_uploaad;
    private static final int PICK_IMAGE = 20;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        pic_uploaad=findViewById(R.id.dp_up);
        imageView=findViewById(R.id.dp);

        pic_uploaad.setEnabled(false);
        pic_uploaad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setDrawingCacheEnabled(true);
                imageView.buildDrawingCache();
                Bitmap b= imageView.getDrawingCache();
                ByteArrayOutputStream ba= new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.PNG,100,ba);
                imageView.setDrawingCacheEnabled(false);
                byte[] bt = ba.toByteArray();
                String path= "dp.png";
                ref=FirebaseStorage.getInstance().getReference().child("Profile").child(path);
                pic_uploaad.setEnabled(false);
                UploadTask ut =  ref.putBytes(bt);
                ut.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pic_uploaad.setEnabled(true);

                    }
                });
            }
        });

    }

    public void openGallery(View view) {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
            pic_uploaad.setEnabled(true);
        }
    }
}