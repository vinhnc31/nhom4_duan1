package com.fpoly.VncStore.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fpoly.VncStore.Activity.Loading;
import com.fpoly.VncStore.ChucNang.TaiKhoan;
import com.fpoly.VncStore.Activity.MainActivity;
import com.fpoly.VncStore.Model.User;
import com.fpoly.VncStore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    EditText ed_tendn, ed_mk, ed_name, ed_repass, ed_sodt, ed_diachi;
    Button btn_dangki;
    TextView tv_signin;
    private Loading dialog;
    String email, pass, repass, name, diachi, sodt;
    TextView tv_dk;
    FirebaseAuth auth;
    TaiKhoan taiKhoan;
    TextInputLayout textInputLayout1, textInputLayout2, textInputLayout3, textInputLayout4, textInputLayout5, textInputLayout6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        textInputLayout1 = findViewById(R.id.TextInputLayout3);
        textInputLayout2 = findViewById(R.id.TextInputLayout4);
        textInputLayout3 = findViewById(R.id.TextInputLayout5);
        textInputLayout4 = findViewById(R.id.TextInputLayout6);
        textInputLayout5 = findViewById(R.id.TextInputLayout7);
        textInputLayout6 = findViewById(R.id.TextInputLayout8);
        tv_dk = findViewById(R.id.tvdk);
        ed_tendn = findViewById(R.id.ed_userNamedk);
        ed_name = findViewById(R.id.ed_name);
        findViewById(R.id.back_signup).setOnClickListener(v -> {
            startActivity(new Intent(SignUp.this, SignIn.class));
            overridePendingTransition(R.anim.enter_left_to_right, R.anim.exit_left_to_right);
        });
        ed_sodt = findViewById(R.id.ed_sodt);
        taiKhoan = new TaiKhoan();
        ed_diachi = findViewById(R.id.ed_diachi);
        ed_mk = findViewById(R.id.ed_passWorddk);
        dialog = new Loading(this);
        ed_repass = findViewById(R.id.ed_repassWorddk);
        tv_signin = findViewById(R.id.tv_signin1);
        dialog = new Loading(this);
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(SignUp.this, MainActivity.class));
            finish();
        }
        btn_dangki = findViewById(R.id.btn_signUp);
        btn_dangki.setOnClickListener(view -> {
            dangki();
        });
        tv_signin.setOnClickListener(view -> {
            startActivity(new Intent(SignUp.this, SignIn.class));
            finish();
        });
        Animation();
    }

    private void dangki() {
        pass = ed_mk.getText().toString();
        repass = ed_repass.getText().toString();
        if (validate() > 0) {
            name = ed_name.getText().toString();
            email = ed_tendn.getText().toString();
            sodt = ed_sodt.getText().toString();
            diachi = ed_diachi.getText().toString();
            dialog.showDialog();
            diachi = ed_diachi.getText().toString();
            dialog.showDialog();
            auth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
                                FirebaseUser user1 = auth.getCurrentUser();
                                User user = new User(sodt, name, email, diachi);
                                dialog.dismiss();
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

    private void Animation() {
        tv_dk.setTranslationX(1000);
        textInputLayout1.setTranslationX(1000);
        textInputLayout2.setTranslationX(1000);
        textInputLayout3.setTranslationX(1000);
        textInputLayout4.setTranslationX(1000);
        textInputLayout5.setTranslationX(1000);
        textInputLayout6.setTranslationX(1000);
        btn_dangki.setTranslationX(1000);

        //
        tv_dk.animate().translationX(0).setDuration(900).start();
        textInputLayout1.animate().translationX(0).setDuration(900).setStartDelay(200).start();
        textInputLayout2.animate().translationX(0).setDuration(900).setStartDelay(400).start();
        textInputLayout3.animate().translationX(0).setDuration(900).setStartDelay(600).start();
        textInputLayout4.animate().translationX(0).setDuration(900).setStartDelay(800).start();
        textInputLayout5.animate().translationX(0).setDuration(900).setStartDelay(1000).start();
        textInputLayout6.animate().translationX(0).setDuration(900).setStartDelay(1200).start();
        btn_dangki.animate().translationX(0).setDuration(900).setStartDelay(1600).start();
    }

    public int validate() {
        int check = 1;
        String phone = "(84|0[3|5|7|8|9])+([0-9]{8})";
        String checkemail = "[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)";
        if (ed_name.getText().length() == 0) {
            dialog.dismiss();
            Toast.makeText(SignUp.this, "Tên người dùng không được để trống.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        } else if (ed_tendn.getText().length() == 0) {
            dialog.dismiss();
            Toast.makeText(SignUp.this, "Email không được để trống.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        } else if (!ed_tendn.getText().toString().matches(checkemail)) {
            dialog.dismiss();
            Toast.makeText(SignUp.this, "Email không đúng định dạng.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        } else if (ed_sodt.getText().length() == 0) {
            dialog.dismiss();
            Toast.makeText(this, "Số điện thoại không được để trống", Toast.LENGTH_SHORT).show();
            return check - 1;
        } else if (!ed_sodt.getText().toString().matches(phone)) {
            dialog.dismiss();
            Toast.makeText(this, "Số điện thoại không đúng định dạng", Toast.LENGTH_SHORT).show();
            return check - 1;
        } else if (ed_sodt.getText().length() > 10) {
            dialog.dismiss();
            Toast.makeText(this, "Số điện thoại phải nhỏ hơn 10", Toast.LENGTH_SHORT).show();
            return check - 1;
        } else if (ed_diachi.getText().length() == 0) {
            dialog.dismiss();
            Toast.makeText(this, "Địa chỉ không được để trống", Toast.LENGTH_SHORT).show();
            return check - 1;
        } else if (ed_mk.getText().length() == 0) {
            dialog.dismiss();
            Toast.makeText(SignUp.this, "Password không được để trống.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        } else if (ed_mk.getText().length() < 6) {
            dialog.dismiss();
            Toast.makeText(SignUp.this, "Password phải lớn hơn 6 kí tự.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        } else if (ed_repass.getText().length() == 0) {
            dialog.dismiss();
            Toast.makeText(SignUp.this, "RePassword không được để trống.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        } else if (!pass.equals(repass)) {
            dialog.dismiss();
            Toast.makeText(SignUp.this, "RePassWord Không trùng với PassWord.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        } else if (ed_diachi.getText().length() == 0) {
            dialog.dismiss();
            Toast.makeText(this, "Địa chỉ không được để trống", Toast.LENGTH_SHORT).show();
            return check - 1;
        } else if (ed_sodt.getText().length() == 0) {
            dialog.dismiss();
            Toast.makeText(this, "Số điện thoại không được để trống", Toast.LENGTH_SHORT).show();
            return check - 1;
        } else if (!ed_sodt.getText().toString().matches(phone)) {
            dialog.dismiss();
            Toast.makeText(this, "Số điện thoại không đúng định dạng", Toast.LENGTH_SHORT).show();
            return check - 1;
        } else if (ed_sodt.getText().length() > 10) {
            dialog.dismiss();
            Toast.makeText(this, "Số điện thoại phải nhỏ hơn 10", Toast.LENGTH_SHORT).show();
            return check - 1;
        }
        return check;
    }
}