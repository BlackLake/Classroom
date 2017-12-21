package com.example.classroom.classroom;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VeriTabani {

    private FirebaseDatabase veritabani;
    private DatabaseReference reference;
    private FirebaseAuth giris;
    private FirebaseUser girisYapanKullanici;
    private Context context;
    private Uyarilar uyarilar;
    private LocalVeriTabani localVeriTabani;
    private Kullanici kullanici=new Kullanici();

    public VeriTabani(Context context){
        veritabani=FirebaseDatabase.getInstance();
        reference=veritabani.getReference();
        giris=FirebaseAuth.getInstance();
        this.context=context;
        uyarilar=new Uyarilar(context);
        localVeriTabani=new LocalVeriTabani(context);

    }

    private void ekle(String id,String ad,String soyad,String degisken,String tur){
        reference=veritabani.getReference("kullanicilar");
        Kullanici kullanici=new Kullanici(ad,soyad,degisken,tur);
        kullanici.setId(id);
        reference.child(id).setValue(kullanici);
    }



    public void uyeEkle(final String tur,final String ad,final String soyad,String mail,String sifre,final String degisken){
        uyarilar.uyariBaslat("","İşleminiz devam ediyor. Lütfen bekleyin...");
        giris.createUserWithEmailAndPassword(mail,sifre)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            if (tur=="ÖĞRENCİ"||tur=="AKADEMİSYEN"){
                                ekle(giris.getCurrentUser().getUid(),ad,soyad,degisken,tur);
                                uyarilar.uyariDurdur();
                                uyarilar.kayitBasarili();
                            }else{
                                uyarilar.kayitBasarisiz();
                                uyarilar.uyariDurdur();
                            }
                        }else{
                            uyarilar.kayitBasarisiz();
                            uyarilar.uyariDurdur();
                        }
                    }
                });


    }

    public void girisYap(String mail,String sifre){
        uyarilar.uyariBaslat("","İşleminiz devam ediyor. Lütfen bekleyiniz...");
        giris.signInWithEmailAndPassword(mail,sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    uyarilar.uyariDurdur();
                    girisYapanKullanici();

                    context.startActivity(new Intent(context,Anasayfa.class));
                }else{
                    uyarilar.uyariDurdur();
                    uyarilar.hata("Girdiğiniz bilgilere ait bir kullanıcı bulunamadı!");

                }
            }

        });

    }

    public void girisYapanKullanici(){

        giris=FirebaseAuth.getInstance();
        girisYapanKullanici=giris.getCurrentUser();
        reference=veritabani.getReference("kullanicilar/"+girisYapanKullanici.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                kullanici=dataSnapshot.getValue(Kullanici.class);
                //localVeriTabani.girisYapanKullaniciTut(kullanici);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
    public void tumKullanicilar(){
        girisYapanKullanici=giris.getCurrentUser();
        reference=veritabani.getReference("kullanicilar");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Kullanici kullanici=dataSnapshot1.getValue(Kullanici.class);
                    localVeriTabani.kullaniciEkle(kullanici);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void cikisYap(){
        giris.signOut();
        context.startActivity(new Intent(context,MainActivity.class));

    }

}
