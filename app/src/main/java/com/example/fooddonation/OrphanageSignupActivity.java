package com.example.fooddonation;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrphanageSignupActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        setContentView(R.layout.activity_orphanage_signup);
        // Enable the back button
        TextView textView=findViewById(R.id.textView);
        Button btn=findViewById(R.id.button);
        btn.setEnabled(false);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Orphanage Details");


        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#4bb6e8"));
        actionBar.setBackgroundDrawable(colorDrawable);


        CheckBox checkBox=findViewById(R.id.conditions);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (buttonView.isChecked()) {
                btn.setEnabled(true);
            } else {
                btn.setEnabled(false);
            }

        });

        btn.setOnClickListener(v -> {
            final EditText Name = (EditText)findViewById(R.id.et_name);
            String Uname =Name.getText().toString().toLowerCase();
            final EditText OName = (EditText)findViewById(R.id.et_oname);
            String Oname =OName.getText().toString().toLowerCase();
            final EditText Member = (EditText)findViewById(R.id.et_members);
            String member =Member.getText().toString().toLowerCase();
            final EditText Number = (EditText)findViewById(R.id.et_number);
            String number =Number.getText().toString().toLowerCase();
            final EditText Email = (EditText)findViewById(R.id.et_email);
            String email =Email.getText().toString().toLowerCase();
            String email1 = Email.getText().toString().trim();
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            final EditText Password = (EditText)findViewById(R.id.et_password);
            String password =Password.getText().toString().toLowerCase();
            final EditText Cpassword = (EditText)findViewById(R.id.et_cpassword);
            String cpassword =Cpassword.getText().toString().toLowerCase();
            final EditText Address = (EditText)findViewById(R.id.et_Address);
            String address =Address.getText().toString().toLowerCase();
            final EditText City = (EditText)findViewById(R.id.et_city);
            String city =City.getText().toString().toLowerCase();
            final EditText District = (EditText)findViewById(R.id.et_District);
            String district =District.getText().toString().toLowerCase();
            final EditText State = (EditText)findViewById(R.id.et_State);
            String state =State.getText().toString().toLowerCase();
            firebaseDatabase = FirebaseDatabase.getInstance();
            reference = firebaseDatabase.getReference("orphanagers");
            reference.orderByChild("name").equalTo(Uname).addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   if(dataSnapshot.exists())
                   {
                       Name.setError("Name Already Exists");
                   }
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });
            if((TextUtils.isEmpty(Uname))) {
                Name.setError("Field Cnnot be Empty");
            }
            else if(!Uname.matches("[a-zA-Z ]+"))
            {
                Name.setError("Name must be letters not numbers");
            }
            else if((TextUtils.isEmpty(Oname)))
            {
                OName.setError("Field Cnnot be Empty");
            }
            else if(!Oname.matches("[a-zA-Z ]+"))
            {
                OName.setError("Name must be letters not numbers");
            }
            else if((TextUtils.isEmpty(member))) {
                Member.setError("Field Cnnot be Empty");
            }
            else if((TextUtils.isEmpty(number))) {
                Number.setError("Field Cnnot be Empty");
            }
            else if((TextUtils.isEmpty(email))) {
                Email.setError("Field Cnnot be Empty");
            }
            else if(!email1.matches(emailPattern))
            {
                Email.setError("invalid email address");
            }
            else if((TextUtils.isEmpty(password))) {
                Password.setError("Field Cnnot be Empty");
            }
            else if((TextUtils.isEmpty(cpassword))) {
                Cpassword.setError("Field Cnnot be Empty");
            }
            else if (!cpassword.matches(password))
            {
                Cpassword.setError("Cpassword not mactched with password");
            }
            else if((TextUtils.isEmpty(address))) {
                Address.setError("Field Cnnot be Empty");
            }
            else if((TextUtils.isEmpty(city))) {
                City.setError("Field Cnnot be Empty");
            }
            else if(!city.matches("[a-zA-Z ]+"))
            {
                City.setError("Name must be letters not numbers");
            }
            else if((TextUtils.isEmpty(district))) {
                District.setError("Field Cnnot be Empty");
            }
            else if(!district.matches("[a-zA-Z ]+"))
            {
                District.setError("Name must be letters not numbers");
            }
            else if((TextUtils.isEmpty(state))) {
                State.setError("Field Cnnot be Empty");
            }
            else if(!state.matches("[a-zA-Z ]+"))
            {
                State.setError("Name must be letters not numbers");
            }
            else
            {
                signup signup = new signup(Uname,Oname,member,number,email,password,address,city,district,state);
                reference.child(Uname).setValue(signup);
                Toast.makeText(OrphanageSignupActivity.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(OrphanageSignupActivity.this, OrphanageLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrphanageSignupActivity.this, OrphanageLoginActivity.class);
                startActivity(intent);
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