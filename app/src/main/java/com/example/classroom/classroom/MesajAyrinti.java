package com.example.classroom.classroom;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Fatih on 21.12.2017.
 */

public class MesajAyrinti extends Fragment {

    private TextView mesaj;
    private Button gonder;
    private Uyarilar uyarilar;
    private String mesaj_text;
    private LocalVeriTabani localVeriTabani;
    private VeriTabani veriTabani;
    private ListView listView;
    private ArrayList<Mesaj> mesajList=new ArrayList<>();
    private FirebaseDatabase database;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uyarilar=new Uyarilar(getActivity());
        localVeriTabani=new LocalVeriTabani(getActivity());
        veriTabani=new VeriTabani(getActivity());


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_mesaj_ayrinti,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mesaj=(TextView)view.findViewById(R.id.mesaj);
        gonder=(Button)view.findViewById(R.id.gonder);

        listView=(ListView)view.findViewById(R.id.mesajlar_listesi);
        final MesajAdapter adapter=new MesajAdapter(getActivity(),mesajList,FirebaseAuth.getInstance().getCurrentUser());

        getActivity().setTitle(localVeriTabani.mesajGonderilecekKisi().getAd()+" "+localVeriTabani.mesajGonderilecekKisi().getSoyad());
        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mesaj_text=mesaj.getText().toString();
                if (!TextUtils.isEmpty(mesaj_text)){
                    SimpleDateFormat bicim=new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
                    Date tarih=new Date();
                    String zaman=bicim.format(tarih);
                    Mesaj yeniMesaj=new Mesaj(
                            FirebaseAuth.getInstance().getCurrentUser().getUid(),
                            localVeriTabani.mesajGonderilecekKisi().getId(),
                            zaman,
                            mesaj_text);
                    veriTabani.mesajEkle(yeniMesaj);
                    adapter.notifyDataSetChanged();
                    mesaj.setText("");
                }else{
                    uyarilar.hata("Boş mesaj!");
                }
            }
        });




    }
}
