package com.fpoly.VncStore.ChucNang;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class DoiMatkhauFragment extends Fragment {
    EditText ed_passold, ed_pass, ed_repass;
    Button btn_doipass;
    TextView tv_back;
    ProgressDialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_doi_matkhau, container, false);
        ed_passold = v.findViewById(R.id.ed_passold);
        dialog = new ProgressDialog(getActivity());
        ed_pass = v.findViewById(R.id.ed_pass);
        ed_repass = v.findViewById(R.id.ed_repass);
        tv_back =v.findViewById(R.id.back_tk);
        btn_doipass = v.findViewById(R.id.btn_doipass);
        btn_doipass.setOnClickListener(view -> {
            Doipass();
        });
        tv_back.setOnClickListener(view -> {
            getFragmentManager().popBackStack();
        });
        return v;
    }
    private void Doipass() {
        String passold = ed_passold.getText().toString().trim();
        String pass = ed_pass.getText().toString().trim();
        String repass = ed_repass.getText().toString().trim();
        dialog.show();
        if (ed_passold.getText().length() == 0) {
            dialog.dismiss();
            Toast.makeText(getActivity(), "Mật khẩu cũ không được để trống", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ed_pass.getText().length() == 0) {
            dialog.dismiss();
            Toast.makeText(getActivity(), "Mật khẩu mới không được để trống", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ed_repass.getText().length() == 0) {
            dialog.dismiss();
            Toast.makeText(getActivity(), "Vui lòng nhập đủ dữ liệu", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ed_pass.getText().length() < 6) {
            dialog.dismiss();
            Toast.makeText(getActivity(), "Mật khẩu mới phải lớn hơn 6 kí tự ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pass.equals(repass)) {
            dialog.dismiss();
            Toast.makeText(getActivity(), "Nhập lại mật khẩu không đúng", Toast.LENGTH_SHORT).show();
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
                                            Toast.makeText(getActivity(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getActivity(), SignIn.class));
                                            getActivity().finish();
                                        } else {
                                            Dialog dialog = new Dialog(getActivity());
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
                        Toast.makeText(getActivity(), "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getActivity(), "Vui lòng nhập lại Email hoặc Password của bạn", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}