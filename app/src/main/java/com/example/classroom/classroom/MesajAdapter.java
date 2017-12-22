package com.example.classroom.classroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * Created by alper on 16/07/2017.
 */

public class MesajAdapter extends ArrayAdapter<Mesaj> {

    private FirebaseUser firebaseUser;

    public MesajAdapter(Context context, ArrayList<Mesaj> chatList,FirebaseUser firebaseUser) {
        super(context, 0, chatList);
        this.firebaseUser = firebaseUser;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Mesaj message = getItem(position);
        if (firebaseUser.getEmail().equalsIgnoreCase(message.getGonderenMail())){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_gonderilen_mesaj, parent, false);

            TextView txtMessage = (TextView) convertView.findViewById(R.id.txtMessageRight);
            TextView txtTime = (TextView) convertView.findViewById(R.id.txtTimeRight);
            txtMessage.setText(message.getMesaj());
            txtTime.setText(message.getTarih());

        }else{

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_alinan_mesaj, parent, false);

            TextView txtMessage = (TextView) convertView.findViewById(R.id.txtMessageLeft);
            TextView txtTime = (TextView) convertView.findViewById(R.id.txtTimeLeft);

            txtMessage.setText(message.getMesaj());
            txtTime.setText(message.getTarih());

        }

        return convertView;
    }
}