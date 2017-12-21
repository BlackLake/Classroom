package com.example.classroom.classroom;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Fatih on 20.12.2017.
 */

@SuppressLint("ValidFragment")
public class Mesajlar extends Fragment{

    private OzelAdapter adapter;
    private List<String> kullanici_adlari;
    private List<Kullanici> kullanicilar;
    private VeriTabani veriTabani;
    private LocalVeriTabani localVeriTabani;
    private ListView listView;
    private Activity context;

    @SuppressLint("ValidFragment")
    public Mesajlar(Activity context){
        this.context=context;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        veriTabani=new VeriTabani(context);
        localVeriTabani=new LocalVeriTabani(context);
        veriTabani.tumKullanicilar();






    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_mesajlar,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton floatingActionButton=(FloatingActionButton)view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment=new MesajYazilacakKisiler(context);
                MainActivity.tempFragment=fragment;
                if (fragment!=null){
                    FragmentTransaction transaction=getFragmentManager().beginTransaction();
                    transaction.replace(Mesajlar.this.getId(), fragment);
                    transaction.commit();
                }
            }
        });

    }
}
