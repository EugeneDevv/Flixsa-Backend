package com.eujoh.uoeadminapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonChooseImage;
    private Button buttonUpload;
    private TextView textViewCancelUpload;
    private EditText editTextItemName;
    private EditText editTextDescription;
    private ImageView imageView;
    private Uri imageUri;
    private ProgressBar progressBar;

    Dialog dialog;
    Animation animation;

    private String Name;
    private String Description;

    Integer REQUEST_CAMERA=1, SELECT_FILE=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        buttonChooseImage = findViewById(R.id.choose_pic_btn);
        buttonUpload = findViewById(R.id.lost_upload_btn);
        textViewCancelUpload = findViewById(R.id.lost_cancel_tv);
        editTextItemName = findViewById(R.id.item_name_tv);
        editTextDescription = findViewById(R.id.item_desc_tv);
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.lost_progress_bar);

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    uploadFile();

            }
        });

        textViewCancelUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buttonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bottom_anim);
//                dialog.setAnimation(animation);
            }
        });
    }

    private void uploadFile() {
        Name = editTextItemName.getText().toString().trim();
        Description = editTextDescription.getText().toString().trim();

        if (Name != "" && Description != ""){

    } else {
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }


}

    public void openCamera(View view) {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
        dialog.dismiss();
    }

    public void cancelDialog(View view) {
        dialog.dismiss();
    }

    public void openGallery(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent.createChooser(intent, "Select File"), SELECT_FILE);
        dialog.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode== Activity.RESULT_OK){
            if (requestCode==REQUEST_CAMERA){
                Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");
                imageView.setImageBitmap(bmp);
            }else if (resultCode==SELECT_FILE){
                imageUri = data.getData();
                imageView.setImageURI(imageUri);
            }
        }
    }
}