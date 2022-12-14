package com.fpoly.VncStore.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fpoly.VncStore.Login.SignIn;
import com.fpoly.VncStore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DoiMatKhau extends AppCompatActivity {
    EditText ed_passold, ed_pass, ed_repass;
    Button btn_doipass;
    TextView tv_back;
    Loading dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        ed_passold = findViewById(R.id.ed_passold);
        dialog = new Loading(this);
        ed_pass = findViewById(R.id.ed_pass);
        ed_repass = findViewById(R.id.ed_repass);
        tv_back =findViewById(R.id.back_tk);
        btn_doipass = findViewById(R.id.btn_doipass);
        btn_doipass.setOnClickListener(view -> {
            Doipass();
        });
        tv_back.setOnClickListener(view -> {
            super.onBackPressed();
           overridePendingTransition(R.anim.enter_left_to_right,R.anim.exit_left_to_right);
        });
    }
    private void Doipass() {
        String passold = ed_passold.getText().toString().trim();
        String pass = ed_pass.getText().toString().trim();
        String repass = ed_repass.getText().toString().trim();
        dialog.showDialog();
        if (ed_passold.getText().length() == 0) {
            dialog.dismiss();
            Toast.makeText(this, "M???t kh???u c?? kh??ng ???????c ????? tr???ng", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ed_pass.getText().length() == 0) {
            dialog.dismiss();
            Toast.makeText(this, "M???t kh???u m???i kh??ng ???????c ????? tr???ng", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ed_repass.getText().length() == 0) {
            dialog.dismiss();
            Toast.makeText(this, "Vui l??ng nh???p ????? d??? li???u", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ed_pass.getText().length() < 6) {
            dialog.dismiss();
            Toast.makeText(this, "M???t kh???u m???i ph???i l???n h??n 6 k?? t??? ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pass.equals(repass)) {
            dialog.dismiss();
            Toast.makeText(this, "Nh???p l???i m???t kh???u kh??ng ????ng", Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential authCredential = EmailAuthProvider.getCredential(user.getEmail(), passold);
        user.reauthenticate(authCredential).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        user.updatePassword(pass)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            dialog.dismiss();
                                            Toast.makeText(DoiMatKhau.this, "?????i m???t kh???u th??nh c??ng", Toast.LENGTH_SHORT).show();
                                            FirebaseAuth.getInstance().signOut();
                                        } else {
                                            Dialog dialog = new Dialog(DoiMatKhau.this);
                                            dialog.setContentView(R.layout.dialog_xacnhan);
                                            EditText ed_email = dialog.findViewById(R.id.ed_emailxn);
                                            EditText ed_pass1 = dialog.findViewById(R.id.ed_passxn);
                                            Button btn = dialog.findViewById(R.id.btn_xacnhan1);
                                            btn.setOnClickListener(view -> {
                                                String email = ed_email.getText().toString();
                                                String pass = ed_pass1.getText().toString();
                                                xacnhan(email, pass);
                                            });
                                            dialog.show();
                                        }
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        dialog.dismiss();
                        Toast.makeText(DoiMatKhau.this, "M???t kh???u c?? kh??ng ????ng", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void xacnhan(String email, String Pass) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential credential = EmailAuthProvider
                .getCredential(email, Pass);
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Doipass();
                        } else {
                            Toast.makeText(DoiMatKhau.this, "Vui l??ng nh???p l???i Email ho???c Password c???a b???n", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}