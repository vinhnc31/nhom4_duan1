package com.fpoly.VncStore.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fpoly.VncStore.Model.User;
import com.fpoly.VncStore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ThongTin extends AppCompatActivity {
    TextView back;
    private EditText ed_ten,ed_sodienthoai,ed_diachi,ed_email;
    private TextView Confirn;
    Loading progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);
        ed_ten=findViewById(R.id.tentt);
        ed_sodienthoai=findViewById(R.id.sodttt);
        ed_diachi=findViewById(R.id.diachitt);
        ed_email=findViewById(R.id.emailtt);
        Confirn=findViewById(R.id.confirm);
        progressDialog=new Loading(this);
        back=findViewById(R.id.back1);
        back.setOnClickListener(view -> {
            super.onBackPressed();
            overridePendingTransition(R.anim.enter_left_to_right,R.anim.exit_left_to_right);
        });
        setShow();
        Show();
    }
    private void Show() {
        Confirn.setOnClickListener(view -> {
            onClickUpdate();
        });
    }
    private void setShow() {
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String userId=user.getUid();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("User");
        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User u=snapshot.getValue(User.class);
                if (u!=null){
                    ed_ten.setText(user.getDisplayName());
                    ed_email.setText(user.getEmail());
                    ed_email.setEnabled(false);
                    ed_sodienthoai.setText(u.getSo());
                    ed_diachi.setText(u.getDiachi());
                }else {
                    return;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void onClickUpdate() {
        String address=ed_diachi.getText().toString().trim();
        String phone= ed_sodienthoai.getText().toString().trim();
        String name=ed_ten.getText().toString().trim();
        String email=ed_email.getText().toString().trim();
        progressDialog.showDialog();
        if (validate()>0){
            User u = new User(phone, name, email, address);
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String userID=user.getUid();
            DatabaseReference reference=FirebaseDatabase.getInstance().getReference("User");
            reference.child(userID).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .build();

                        user.updateProfile(profileUpdates);
                        Toast.makeText(ThongTin.this, "Update thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
    public int validate() {
        int check = 1;
        String phone = "(84|0[3|5|7|8|9])+([0-9]{8})";
        if (ed_ten.getText().length() == 0) {
            Toast.makeText(ThongTin.this, "Tên không được để trống.",
                    Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
            return check - 1;
        }  else if (ed_diachi.getText().length() == 0) {
            progressDialog.dismiss();
            Toast.makeText(ThongTin.this, "Địa chỉ không được để trống.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        } else if (ed_sodienthoai.getText().length() ==0) {
            progressDialog.dismiss();
            Toast.makeText(ThongTin.this, "Số điện thoại không được để trống .",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        }  else if (!ed_sodienthoai.getText().toString().matches(phone)) {
            progressDialog.dismiss();
            Toast.makeText(ThongTin.this, "Số điện thoại không đúng định dạng", Toast.LENGTH_SHORT).show();
            return check - 1;
        }
        else if (ed_sodienthoai.getText().length()>10) {
            progressDialog.dismiss();
            Toast.makeText(ThongTin.this, "Số điện thoại phải nhỏ hơn 10", Toast.LENGTH_SHORT).show();
            return check - 1;
        }
        return check;
    }
}