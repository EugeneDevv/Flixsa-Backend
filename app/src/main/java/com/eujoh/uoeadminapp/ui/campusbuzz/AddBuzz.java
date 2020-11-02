package com.eujoh.uoeadminapp.ui.campusbuzz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eujoh.uoeadminapp.R;
import com.eujoh.uoeadminapp.helper.uploadinfo;
import com.eujoh.uoeadminapp.ui.announcements.AddAnnouncement;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddBuzz extends AppCompatActivity {
    private Button buttonChooseImage;
    private Button buttonUpload;
    private TextView textViewCancelUpload;
    private EditText editTextItemName;
    private EditText editTextDescription;
    private ImageView imageView;
    Uri imageUri;
    ProgressDialog progressDialog;

    Dialog dialog;

    private String Name;
    private String Description;

    int REQUEST_CAMERA = 1, SELECT_FILE = 0;
    StorageReference storageReference;
    DatabaseReference databaseReference;

    private StorageTask mUploadtask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_buzz);

        storageReference = FirebaseStorage.getInstance().getReference("CampusBuzz");
        databaseReference = FirebaseDatabase.getInstance().getReference("CampusBuzz");

        progressDialog = new ProgressDialog(AddBuzz.this);
        buttonChooseImage = findViewById(R.id.choose_pic_btn);
        buttonUpload = findViewById(R.id.lost_upload_btn);
        textViewCancelUpload = findViewById(R.id.lost_cancel_tv);
        editTextItemName = findViewById(R.id.item_name_tv);
        editTextDescription = findViewById(R.id.item_desc_tv);
        imageView = findViewById(R.id.imageView);

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUploadtask != null && mUploadtask.isInProgress()){
                    Toast.makeText(AddBuzz.this, "Upload in progress...", Toast.LENGTH_SHORT).show();
                } else {
                    UploadFiles();
                }
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
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), SELECT_FILE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_FILE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            imageUri = data.getData();

            Picasso.get().load(imageUri).into(imageView);
        }
    }
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }


    public void UploadFiles() {
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
            Toast.makeText(AddBuzz.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();
        }
        else {
            progressDialog.setTitle("Uploading data...");
            progressDialog.show();
            StorageReference fileRefence = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(imageUri));
            mUploadtask = fileRefence.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Upload Successful", Toast.LENGTH_LONG).show();
                            @SuppressWarnings("VisibleForTests")
                            uploadinfo imageUploadInfo = new uploadinfo(ItemName, ItemDescription, taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
                            String ImageUploadId = databaseReference.push().getKey();
                            databaseReference.child(ImageUploadId).setValue(imageUploadInfo);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }
    }
}