package com.fpoly.VncStore.Login;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.fpoly.VncStore.MainActivity;
import com.fpoly.VncStore.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {
    EditText ed_tendn, ed_mk;
    Button btn_login;
    TextView tv_dk;
    private ProgressDialog dialog;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed_tendn = findViewById(R.id.ed_userName);
        ed_mk = findViewById(R.id.ed_pasWord);
        btn_login = findViewById(R.id.btn_login);
        tv_dk = findViewById(R.id.tv_signUp);
        auth = FirebaseAuth.getInstance();
        tv_dk.setOnClickListener(view -> {
            startActivity(new Intent(this, SignUp.class));
        });
        dialog = new ProgressDialog(this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClicksingin();
            }
        });
    }

    private void onClicksingin() {
        String email = ed_tendn.getText().toString().trim();
        String password = ed_mk.getText().toString().trim();
        dialog.show();
        if (validate() > 0) {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            dialog.dismiss();
                            if (task.isSuccessful()) {
                                startActivity(new Intent(SignIn.this, MainActivity.class));
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(SignIn.this, "Login failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public int validate() {
        int check = 1;
        String checkemail = "[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)";
        if (ed_tendn.getText().length() == 0) {
            Toast.makeText(SignIn.this, "Email không được để trống.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        } else if (!ed_tendn.getText().toString().matches(checkemail)) {
            dialog.dismiss();
            Toast.makeText(SignIn.this, "Email không đúng định dạng.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        } else if (ed_mk.getText().length() == 0) {
            dialog.dismiss();
            Toast.makeText(SignIn.this, "Password không được để trống.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        } else if (ed_mk.getText().length() < 6) {
            dialog.dismiss();
            Toast.makeText(SignIn.this, "Password phải lớn hơn 6 kí tự.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        }
        return check;
    }
}
