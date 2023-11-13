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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DonarSignupActivity extends AppCompatActivity {
    TextView textView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar_signup);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        textView=findViewById(R.id.textView);
        Button btn=findViewById(R.id.button);
        btn.setEnabled(false);

        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#4bb6e8"));
        actionBar.setBackgroundDrawable(colorDrawable);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonarSignupActivity.this, DonarLoginActivity.class);
                startActivity(intent);
            }
        });
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
            String name =Name.getText().toString().toLowerCase();
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
            firebaseDatabase = FirebaseDatabase.getInstance();
            reference = firebaseDatabase.getReference("donar");
            reference.orderByChild("name").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
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
            if((TextUtils.isEmpty(name))) {
                Name.setError("Field Cnnot be Empty");
            }
            else if(!name.matches("[a-zA-Z ]+"))
            {
                Name.setError("Name must be letters not numbers");
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
            else
            {
                signupdonar signupdonar = new signupdonar(name,number,email,password);
                reference.child(name).setValue(signupdonar);
                Toast.makeText(DonarSignupActivity.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(DonarSignupActivity.this, DonarLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}