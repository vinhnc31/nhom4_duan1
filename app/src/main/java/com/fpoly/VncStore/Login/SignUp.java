package com.fpoly.VncStore.Login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fpoly.VncStore.ChucNang.TaiKhoan;
import com.fpoly.VncStore.MainActivity;
import com.fpoly.VncStore.Model.User;
import com.fpoly.VncStore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    EditText ed_tendn, ed_mk, ed_name, ed_repass, ed_sodt, ed_diachi;
    Button btn_dangki;
    private ProgressDialog progressDialog;
    String email, pass, repass, name, diachi;
    int sodt;
    FirebaseAuth auth;
    TaiKhoan taiKhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ed_tendn = findViewById(R.id.ed_userNamedk);
        ed_name = findViewById(R.id.ed_name);
        ed_sodt = findViewById(R.id.ed_sodt);
        taiKhoan = new TaiKhoan();
        ed_diachi = findViewById(R.id.ed_diachi);
        ed_mk = findViewById(R.id.ed_passWorddk);
        progressDialog = new ProgressDialog(this);
        ed_repass = findViewById(R.id.ed_repassWorddk);
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(SignUp.this, MainActivity.class));
            finish();
        }
        btn_dangki = findViewById(R.id.btn_signUp);
        btn_dangki.setOnClickListener(view -> {
            dangki();
        });
    }

    private void dangki() {
        pass = ed_mk.getText().toString();
        repass = ed_repass.getText().toString();

        if (validate() > 0) {
        name = ed_name.getText().toString();
        email = ed_tendn.getText().toString();
        sodt = Integer.parseInt(ed_sodt.getText().toString());
        diachi = ed_diachi.getText().toString();
        progressDialog.show();

            auth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
                                FirebaseUser user1 = auth.getCurrentUser();
                                User user = new User(sodt, name, email, diachi);
                                reference.child(user1.getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        user1.sendEmailVerification();
                                        startActivity(new Intent(SignUp.this, MainActivity.class));
                                    }
                                });
                                FirebaseUser user2 = FirebaseAuth.getInstance().getCurrentUser();
                                if (user2 == null) {
                                    return;
                                }
                                UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(name).build();
                                user2.updateProfile(profileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                    }
                                });

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignUp.this, "Đăng kí không thành công.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

        }
    }

    public int validate() {
        int check = 1;
        String phone = "(84|0[3|5|7|8|9])+([0-9]{8})";
        String checkemail = "[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)";
        if (ed_tendn.getText().length() == 0) {
            Toast.makeText(SignUp.this, "Email không được để trống.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        } else if (!ed_tendn.getText().toString().matches(checkemail)) {
            progressDialog.dismiss();
            Toast.makeText(SignUp.this, "Email không đúng định dạng.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        } else if (ed_mk.getText().length() == 0) {
            progressDialog.dismiss();
            Toast.makeText(SignUp.this, "Password không được để trống.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        } else if (ed_mk.getText().length() < 6) {
            progressDialog.dismiss();
            Toast.makeText(SignUp.this, "Password phải lớn hơn 6 kí tự.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        } else if (ed_repass.getText().length() == 0) {
            progressDialog.dismiss();
            Toast.makeText(SignUp.this, "RePassword không được để trống.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        } else if (!pass.equals(repass)) {
            progressDialog.dismiss();
            Toast.makeText(SignUp.this, "RePassWord Không trùng với PassWord.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        } else if (ed_diachi.getText().length() == 0) {
            progressDialog.dismiss();
            Toast.makeText(this, "Địa chỉ không được để trống", Toast.LENGTH_SHORT).show();
            return check - 1;
        } else if (ed_sodt.getText().length() == 0) {
            progressDialog.dismiss();
            Toast.makeText(this, "Số điện thoại không được để trống", Toast.LENGTH_SHORT).show();
            return check - 1;
        } else if (!ed_sodt.getText().toString().matches(phone)) {
            progressDialog.dismiss();
            Toast.makeText(this, "Số điện thoại không đúng định dạng", Toast.LENGTH_SHORT).show();
            return check - 1;
        }else if (ed_sodt.getText().length()>10) {
            progressDialog.dismiss();
            Toast.makeText(this, "Số điện thoại phải nhỏ hơn 10", Toast.LENGTH_SHORT).show();
            return check - 1;
        }
        return check;
    }
}