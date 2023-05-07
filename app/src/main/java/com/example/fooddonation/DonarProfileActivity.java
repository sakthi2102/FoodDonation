package com.example.fooddonation;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DonarProfileActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    TextView name;

    TextView number;
    TextView email;
    TextView password;
    FloatingActionButton fab;
    private GridView gridView;
    private ArrayList<post> dataList;
    private ProfileAdapter adapter;
    final private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Images");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar_profile);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        ActionBar actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#4bb6e8"));
        actionBar.setBackgroundDrawable(colorDrawable);

        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        Intent intent = getIntent();

        String name1 = intent.getStringExtra("name").toUpperCase();
        String number1 = intent.getStringExtra("number").toUpperCase();
        String email1 = intent.getStringExtra("email").toUpperCase();
        String password1 = intent.getStringExtra("password").toUpperCase();


        name.setText(name1);
        number.setText(number1);
        email.setText(email1);
        password.setText(password1);


        actionBar.setTitle("Welcome " + name1);

        gridView = findViewById(R.id.gridView);
        dataList = new ArrayList<>();
        adapter = new ProfileAdapter(this, dataList);
        gridView.setAdapter(adapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    post post = dataSnapshot.getValue(post.class);
                    dataList.add(post);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DonarActivity.class);
        startActivity(intent);
        finish();

    }
}