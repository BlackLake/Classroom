package com.example.classroom.classroom;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Fatih on 8.08.2017.
 */

public class OzelAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Kullanici> kullanicilar;
    public OzelAdapter(Activity activity, List<Kullanici> kullanicilar) {

        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        this.kullanicilar = kullanicilar;
    }

    @Override
    public int getCount() {
        return kullanicilar.size();
    }

    @Override
    public Kullanici getItem(int position) {

        return kullanicilar.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;

        satirView = mInflater.inflate(R.layout.satir_ici, null);
        TextView textView =(TextView) satirView.findViewById(R.id.textView2);
        ImageView imageView =(ImageView) satirView.findViewById(R.id.simge);
        textView.setText(kullanicilar.get(position).getAd()+" "+kullanicilar.get(position).getSoyad());
        if (kullanicilar.get(position).getTur().toString().trim().equals("ÖĞRENCİ")){
            imageView.setImageResource(R.drawable.ogrenci);
        }else{
            imageView.setImageResource(R.drawable.akademisyen);
        }


        return satirView;
    }
}
