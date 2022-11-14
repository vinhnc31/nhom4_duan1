package com.fpoly.VncStore.ChucNang;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fpoly.VncStore.Login.SignUp;
import com.fpoly.VncStore.Model.User;
import com.fpoly.VncStore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ThongtinFragment extends Fragment {
    TextView back;
    private EditText ed_ten,ed_sodienthoai,ed_diachi,ed_email;
    private TextView Confirn;
    ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_thongtin, container, false);
        ed_ten=v.findViewById(R.id.tentt);
        ed_sodienthoai=v.findViewById(R.id.sodttt);
        ed_diachi=v.findViewById(R.id.diachitt);
        ed_email=v.findViewById(R.id.emailtt);
        Confirn=v.findViewById(R.id.confirm);
        progressDialog=new ProgressDialog(getActivity());
        back=v.findViewById(R.id.back1);
        back.setOnClickListener(view -> {
            getFragmentManager().popBackStack();
        });
        setShow();
        Show();
        return  v;
    }
    private void Show() {
        Confirn.setOnClickListener(view -> {
            onClickUpdate();
        });
    }

    private void setShow() {
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String userId=user.getUid();
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("User");
        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User u=snapshot.getValue(User.class);
                if (u!=null){
                    ed_ten.setText(user.getDisplayName());
                    ed_email.setText(user.getEmail());
                    ed_email.setEnabled(false);
                    ed_sodienthoai.setText(String.valueOf(u.getSo()));
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
        int phone= Integer.parseInt(ed_sodienthoai.getText().toString().trim());
        String name=ed_ten.getText().toString().trim();
        String email=ed_email.getText().toString().trim();
        progressDialog.show();
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
                        Toast.makeText(getActivity(), "Update thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
    public int validate() {
        int check = 1;
        String phone = "(84|0[3|5|7|8|9])+([0-9]{8})";
        if (ed_ten.getText().length() == 0) {
            Toast.makeText(getActivity(), "Tên không được để trống.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        }  else if (ed_diachi.getText().length() == 0) {
            progressDialog.dismiss();
            Toast.makeText(getActivity(), "Địa chỉ không được để trống.",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        } else if (ed_sodienthoai.getText().length() ==0) {
            progressDialog.dismiss();
            Toast.makeText(getActivity(), "Số điện thoại không được để trống .",
                    Toast.LENGTH_LONG).show();
            return check - 1;
        }  else if (!ed_sodienthoai.getText().toString().matches(phone)) {
            progressDialog.dismiss();
            Toast.makeText(getActivity(), "Số điện thoại không đúng định dạng", Toast.LENGTH_SHORT).show();
            return check - 1;
        }
        else if (ed_sodienthoai.getText().length()>10) {
            progressDialog.dismiss();
            Toast.makeText(getActivity(), "Số điện thoại phải nhỏ hơn 10", Toast.LENGTH_SHORT).show();
            return check - 1;
        }
        return check;
    }
}