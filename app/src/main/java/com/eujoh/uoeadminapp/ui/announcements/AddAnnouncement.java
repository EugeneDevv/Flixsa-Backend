package com.eujoh.uoeadminapp.ui.announcements;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ContentResolver;
import android.webkit.MimeTypeMap;

import com.eujoh.uoeadminapp.R;
import com.eujoh.uoeadminapp.helper.uploadinfo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.IOException;

public class AddAnnouncement extends AppCompatActivity {

    private Button buttonChooseImage;
    private Button buttonUpload;
    private TextView textViewCancelUpload;
    private EditText editTextItemName;
    private EditText editTextDescription;
    private ImageView imageView;
    Uri imageUri;
    ProgressDialog progressDialog ;

    Dialog dialog;
    Animation animation;

    private String Name;
    private String Description;

    int REQUEST_CAMERA=1, SELECT_FILE=0;
    StorageReference storageReference;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_announcement);

        storageReference = FirebaseStorage.getInstance().getReference("Announcements");
        databaseReference = FirebaseDatabase.getInstance().getReference("Announcements");
        progressDialog = new ProgressDialog(AddAnnouncement.this);
//        dialog = new Dialog(AddAnnouncement.this);
//        dialog.setContentView(R.layout.custom_dialog);
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//        dialog.setCancelable(false);

        buttonChooseImage = findViewById(R.id.choose_pic_btn);
        buttonUpload = findViewById(R.id.lost_upload_btn);
        textViewCancelUpload = findViewById(R.id.lost_cancel_tv);
        editTextItemName = findViewById(R.id.item_name_tv);
        editTextDescription = findViewById(R.id.item_desc_tv);
        imageView = findViewById(R.id.imageView);

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    UploadImage();

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
//                dialog.show();
//                animation = AnimationUtils.loadAnimation(AddAnnouncement.this, R.anim.bottom_anim);
//                dialog.startAnimation(animation);
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Image"), SELECT_FILE);
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
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
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

//        if (resultCode== Activity.RESULT_OK && data != null){
//            if (requestCode==REQUEST_CAMERA){
//                Bundle bundle = data.getExtras();
//                final Bitmap bmp = (Bitmap) bundle.get("data");
//                imageView.setImageBitmap(bmp);
//            }else if (resultCode==SELECT_FILE){
//                imageUri = data.getData();
//                try {
//                    Uri bitmap = MediaStore.Images.Media.getContentUri(imageUri);
//                    imageUri.setIma
//                }
//                imageView.setImageURI(imageUri);
//            }
//        }
        if (requestCode == SELECT_FILE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }


    public void UploadImage() {
        String ItemName = editTextItemName.getText().toString().trim();
        String ItemDescription = editTextDescription.getText().toString().trim();
        if (TextUtils.isEmpty(ItemName)) {
            editTextItemName.setError("Field cannot be empty");
            editTextItemName.requestFocus();
            return;
        }else if (TextUtils.isEmpty(ItemDescription)){
            editTextDescription.setError("Field cannot be empty");
            editTextDescription.requestFocus();
            return;
        }else if (imageUri == null) {
            Toast.makeText(AddAnnouncement.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

            }
            else {

            progressDialog.setTitle("Uploading data...");
            progressDialog.show();
            StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(imageUri));
            storageReference2.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Upload Successful", Toast.LENGTH_LONG).show();
                            @SuppressWarnings("VisibleForTests")
                            uploadinfo imageUploadInfo = new uploadinfo(ItemName, ItemDescription, taskSnapshot.getUploadSessionUri().toString());
                            String ImageUploadId = databaseReference.push().getKey();
                            databaseReference.child(ImageUploadId).setValue(imageUploadInfo);
                            finish();
                        }
                    });

            }
        }
    }
