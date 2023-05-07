package com.example.fooddonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DonarLoginActivity extends AppCompatActivity {

    TextView textView;
    EditText name;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar_login);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        textView=findViewById(R.id.textView);
        name=findViewById(R.id.et_name);
        password=findViewById(R.id.et_password);
        Button btn=findViewById(R.id.button);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login");

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#4bb6e8"));
        actionBar.setBackgroundDrawable(colorDrawable);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonarLoginActivity.this,DonarSignupActivity.class);
                startActivity(intent);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Uname=name.getText().toString().toLowerCase();
                String Password=password.getText().toString().toLowerCase();
                checkuser();

            }
        });
    }
    private void checkuser() {
        String Uname = name.getText().toString().toLowerCase().trim();
        String Password = password.getText().toString().toLowerCase().trim();
        if (TextUtils.isEmpty(Uname)) {
            name.setError("Name Cannot be Empty");
        } else if (!Uname.matches("[a-zA-Z ]+")) {
            name.setError("Name must be letters not numbers");
        } else if (TextUtils.isEmpty(Password)) {
            password.setError("Name Cannot be Empty");
        }
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("donar");
        databaseReference.orderByChild("name").equalTo(Uname).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String dbpassword = snapshot.child(Uname).child("password").getValue(String.class);
                    assert dbpassword != null;
                    if (dbpassword.equals(Password)) {
                        String dbname = snapshot.child(Uname).child("name").getValue(String.class);
                        String dbnumber = snapshot.child(Uname).child("number").getValue(String.class);
                        String dbemail = snapshot.child(Uname).child("email").getValue(String.class);

                        Intent intent = new Intent(DonarLoginActivity.this, DonarProfileActivity.class);
                        intent.putExtra("name", dbname);
                        intent.putExtra("number", dbnumber);
                        intent.putExtra("email", dbemail);
                        intent.putExtra("password", dbpassword);
                        startActivity(intent);
                        Intent intent1 = new Intent(DonarLoginActivity.this, DonarActivity.class);
                        startActivity(intent1);
                        finish();
                    } else {
                        password.setError("Invalid password");
                    }
                } else {
                    name.setError("User Doesnot Exist");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

        @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}