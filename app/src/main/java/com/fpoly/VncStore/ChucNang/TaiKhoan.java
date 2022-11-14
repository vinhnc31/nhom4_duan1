package com.fpoly.VncStore.ChucNang;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fpoly.VncStore.Login.SignIn;
import com.fpoly.VncStore.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class TaiKhoan extends Fragment {
    public  static final String TAG = TaiKhoan.class.getName();
    TextView user1, emailuser,logout,tttk,doimk;
    ImageView img_anh;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://vncstore-7e127-default-rtdb.firebaseio.com/");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tai_khoan, container, false);
        img_anh = v.findViewById(R.id.img_user);
        user1 = v.findViewById(R.id.tv_user1);
        tttk=v.findViewById(R.id.tttk);
        emailuser = v.findViewById(R.id.tv_emailuser);
        logout = v.findViewById(R.id.logout);
        doimk=v.findViewById(R.id.doimk);
        logout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getActivity(),SignIn.class));
            getActivity().finish();
        });
        tttk.setOnClickListener(view -> {
            Fragment fragment=new ThongtinFragment();
            FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction
                    .setCustomAnimations(R.anim.last,R.anim.pop_last,R.anim.next,R.anim.pop_next)
                    .replace(R.id.chuyen, fragment)
                    .addToBackStack("aaa").commit();

        });
        doimk.setOnClickListener(view -> {
            Fragment fragment=new DoiMatkhauFragment();
            FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction
                    .setCustomAnimations(R.anim.last,R.anim.pop_last,R.anim.next,R.anim.pop_next)
                    .replace(R.id.chuyen, fragment).addToBackStack("aa1a").commit();
        });
        Show();
        return v;
    }
    public void Show(){
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        if (user==null){
            return;
        }
        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();
        user1.setText(name);
        emailuser.setText(email);
        Glide.with(this).load(photoUrl).error(R.drawable.user).into(img_anh);
    }
}