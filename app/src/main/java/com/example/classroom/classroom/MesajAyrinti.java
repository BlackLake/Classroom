package com.example.classroom.classroom;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Fatih on 21.12.2017.
 */

public class MesajAyrinti extends Fragment {

    private TextView mesaj;
    private Button gonder;
    private Uyarilar uyarilar;
    private String mesaj_text;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uyarilar=new Uyarilar(getActivity());



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
        mesaj_text=mesaj.getText().toString();
        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(mesaj_text)){

                }else{
                    uyarilar.hata("Boş mesaj!");
                }
            }
        });




    }
}
