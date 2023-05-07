package com.example.fooddonation;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class OrphanageDataActivity extends AppCompatActivity {

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

    Button btn;
    String key="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orphanage_data);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        ActionBar actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#4bb6e8"));
        assert actionBar != null;
        actionBar.setBackgroundDrawable(colorDrawable);
        btn=findViewById(R.id.button);
        name = findViewById(R.id.name);
        oname = findViewById(R.id.oname);
        members = findViewById(R.id.members);
        number = findViewById(R.id.number);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        district = findViewById(R.id.district);
        state = findViewById(R.id.state);
        Intent intent=getIntent();

        String name1 = intent.getStringExtra("name").toUpperCase();
        String oname1 = intent.getStringExtra("oname").toUpperCase();
        String members1 = intent.getStringExtra("member").toUpperCase();
        String number1 = intent.getStringExtra("number").toUpperCase();
        String email1 = intent.getStringExtra("email").toUpperCase();
        String address1 = intent.getStringExtra("address").toUpperCase();
        String city1 = intent.getStringExtra("city");
        String district1 = intent.getStringExtra("district").toUpperCase();
        String state1 = intent.getStringExtra("state").toUpperCase();
        key= intent.getStringExtra("key");


        name.setText(name1);
        oname.setText(oname1);
        members.setText(members1);
        number.setText(number1);
        email.setText(email1);
        address.setText(address1);
        city.setText(city1);
        district.setText(district1);
        state.setText(state1);

        actionBar.setTitle("Welcome " + name1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+number1));
                startActivity(callIntent);
            }
        });
    }
}