package com.example.fooddonation;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DonarPostActivity extends AppCompatActivity {

    private FloatingActionButton uploadButton;
    private ImageView uploadImage;
    EditText uploadCaption;
    private Uri imageUri;
    final  private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Images");
    final private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar_post);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        ActionBar actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#4bb6e8"));
        actionBar.setBackgroundDrawable(colorDrawable);

        uploadButton = findViewById(R.id.uploadButton);
        uploadCaption = findViewById(R.id.uploadCaption);
        uploadImage = findViewById(R.id.uploadImage);


        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        imageUri = data.getData();
                        uploadImage.setImageURI(imageUri);
                    } else {
                        Toast.makeText(DonarPostActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        uploadImage.setOnClickListener(view -> {
            Intent photoPicker = new Intent();
            photoPicker.setAction(Intent.ACTION_GET_CONTENT);
            photoPicker.setType("image/*");
            activityResultLauncher.launch(photoPicker);
            Toast.makeText(DonarPostActivity.this, "Image Selected Sucessfully", Toast.LENGTH_SHORT).show();
        });

        uploadButton.setOnClickListener(view -> {
            uploadButton.setEnabled(false);
            if (imageUri != null){
                uploadToFirebase(imageUri);
            } else  {
                Toast.makeText(DonarPostActivity.this, "Please select image", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //Outside onCreate
    private void uploadToFirebase(Uri uri){
        String caption = uploadCaption.getText().toString();
        final StorageReference imageReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));

        imageReference.putFile(uri).addOnSuccessListener(taskSnapshot -> imageReference.getDownloadUrl().addOnSuccessListener(uri1 -> {
            post post = new post(uri1.toString(), caption);
            String key = databaseReference.push().getKey();
            assert key != null;
            databaseReference.child(key).setValue(post);

            Toast.makeText(DonarPostActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DonarPostActivity.this, DonarActivity.class);
            startActivity(intent);
            finish();
        }));

    }
    private String getFileExtension(Uri fileUri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(fileUri));
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,DonarActivity.class);
        startActivity(intent);
        finish();

    }
}