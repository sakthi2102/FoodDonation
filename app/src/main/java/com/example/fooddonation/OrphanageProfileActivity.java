package com.example.fooddonation;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

public class OrphanageProfileActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    TextView name;
    TextView oname;
    TextView members;
    TextView number;
    TextView email;
    TextView password;
    TextView address;
    TextView city;
    TextView district;
    TextView state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orphanage_profile);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        ActionBar actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#4bb6e8"));
        actionBar.setBackgroundDrawable(colorDrawable);

        name = findViewById(R.id.name);
        oname = findViewById(R.id.oname);
        members = findViewById(R.id.members);
        number = findViewById(R.id.number);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        district = findViewById(R.id.district);
        state = findViewById(R.id.state);

        Intent intent = getIntent();

        String name1 = intent.getStringExtra("name").toUpperCase();
        String oname1 = intent.getStringExtra("oname").toUpperCase();
        String members1 = intent.getStringExtra("member").toUpperCase();
        String number1 = intent.getStringExtra("number").toUpperCase();
        String email1 = intent.getStringExtra("email").toUpperCase();
        String password1 = intent.getStringExtra("password").toUpperCase();
        String address1 = intent.getStringExtra("address").toUpperCase();
        String city1 = intent.getStringExtra("city");
        String district1 = intent.getStringExtra("district").toUpperCase();
        String state1 = intent.getStringExtra("state").toUpperCase();

        name.setText(name1);
        oname.setText(oname1);
        members.setText(members1);
        number.setText(number1);
        email.setText(email1);
        password.setText(password1);
        address.setText(address1);
        city.setText(city1);
        district.setText(district1);
        state.setText(state1);

        actionBar.setTitle("Welcome " + name1);


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, OrphanageActivity.class);
        startActivity(intent);
        finish();

    }
}