package com.example.classroom.classroom;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Fatih on 21.12.2017.
 */

@SuppressLint("ValidFragment")
public class Gruplar extends Fragment{

    Kullanici kullanici;
    LocalVeriTabani localVeriTabani;
    private GrupAdapter grupAdapter;
    private List<Grup> gruplar;
    private ListView listView;
    private Activity context;
    private VeriTabani veriTabani;

    @SuppressLint("ValidFragment")
    public Gruplar(Activity context) {
        this.context = context;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        veriTabani=new VeriTabani(context);
        localVeriTabani=new LocalVeriTabani(context);
        veriTabani.tumGrupDetay();
        gruplar=localVeriTabani.kullaniciGruplariniGetir();

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_gruplar,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView=(ListView)view.findViewById(R.id.grupList);
        grupAdapter=new GrupAdapter(context,gruplar,"gir");
        listView.setAdapter(grupAdapter);


        localVeriTabani= new LocalVeriTabani(getActivity());
        kullanici=localVeriTabani.girisYapanKullanici();
        Log.v("MyActivity",kullanici.getTur());


        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.gruplarFab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (kullanici.getTur().equals("AKADEMİSYEN"))
                {
                    Fragment fragment = new GrupEkle();
                    MainActivity.tempFragment = fragment;
                    if (fragment != null) {
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(Gruplar.this.getId(), fragment);
                        transaction.commit();
                    }
                }

                if (kullanici.getTur().equals("ÖĞRENCİ"))
                {
                    Fragment fragment = new TumGruplar(getActivity());
                    MainActivity.tempFragment = fragment;
                    if (fragment != null) {
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(Gruplar.this.getId(), fragment);
                        transaction.commit();
                    }
                }

            }
        });

    }

}
