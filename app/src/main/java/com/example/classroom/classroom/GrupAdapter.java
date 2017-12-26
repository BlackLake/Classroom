package com.example.classroom.classroom;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

/**
 * Created by blacklake on 12/22/17.
 */

public class GrupAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Grup> gruplar;
    private Activity activity;
    private LocalVeriTabani localVeriTabani;
    private VeriTabani veriTabani;
    private String fonk;//gir - kaydol


    public GrupAdapter(Activity activity, List<Grup> gruplar, String fonk) {
        this.activity=activity;
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        this.gruplar = gruplar;
        this.fonk=fonk;
        localVeriTabani=new LocalVeriTabani(activity.getApplicationContext());
        veriTabani=new VeriTabani(activity.getApplicationContext());
    }

    @Override
    public int getCount() {
        return gruplar.size();
    }

    @Override
    public Grup getItem(int position) {

        return gruplar.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View satirView;

        satirView = mInflater.inflate(R.layout.satir_ici, null);
        TextView textView =(TextView) satirView.findViewById(R.id.textView2);
        ImageView imageView =(ImageView) satirView.findViewById(R.id.simge);
        textView.setText(gruplar.get(position).getAd());
        imageView.setImageResource(R.drawable.ic_group_black_24dp);


        satirView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (fonk.equals("kaydol"))
                {
                    Toast.makeText(activity, gruplar.get(position).getAd(), Toast.LENGTH_SHORT).show();
                    localVeriTabani.girilecekGrupTabloBosalt();
                    localVeriTabani.girilecekGrupEkle(gruplar.get(position));
                    veriTabani.grupKayit(localVeriTabani.girilecekGrup());

                    /*
                    Fragment fragment = new Gruplar(activity);
                    MainActivity.tempFragment = fragment;
                    if (fragment != null) {
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(getId(), fragment);
                        transaction.commit();
                    }*/

                }
                if (fonk.equals("gir"))
                {/*
                    Fragment fragment = new GrupIcerik();
                    MainActivity.tempFragment = fragment;
                    if (fragment != null) {
                        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
                        transaction.replace(fragment.getId(), fragment);
                        transaction.commit();
                    }*/
                }

            }
        });

        return satirView;
    }

}
