package com.example.classroom.classroom;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by blacklake on 12/22/17.
 */

public class GrupAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Grup> gruplar;
    private Activity activity;
    public GrupAdapter(Activity activity, List<Grup> gruplar) {
        this.activity=activity;
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        this.gruplar = gruplar;
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

            }
        });


        return satirView;
    }

}
