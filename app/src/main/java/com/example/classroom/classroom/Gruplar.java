package com.example.classroom.classroom;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Fatih on 21.12.2017.
 */

public class Gruplar extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_gruplar,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.gruplarFab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new GrupEkle();
                MainActivity.tempFragment = fragment;
                if (fragment != null) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(Gruplar.this.getId(), fragment);
                    transaction.commit();
                }
            }
        });
    }

}
