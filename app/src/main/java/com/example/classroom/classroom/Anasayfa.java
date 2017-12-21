package com.example.classroom.classroom;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Anasayfa extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBar;
    private TextView ad,mail;
    private ImageView profil;
    private LocalVeriTabani localVeriTabani;
    private Kullanici girisYapanKullanici;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private VeriTabani veriTabani;
    private NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);

        localVeriTabani=new LocalVeriTabani(Anasayfa.this);
        veriTabani=new VeriTabani(Anasayfa.this);

        girisYapanKullanici=localVeriTabani.girisYapanKullanici();

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        actionBar = new ActionBarDrawerToggle(Anasayfa.this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(actionBar);
        actionBar.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Anasayfa.this.setTitle(item.getTitle());
                Fragment fragment=null;
                switch (item.getItemId()){
                    case R.id.cikis_yap:
                        veriTabani.cikisYap();
                        break;
                    case R.id.mesajlar:
                        fragment=new Mesajlar(Anasayfa.this);
                        break;

                }
                if (fragment!=null){
                    FragmentTransaction transaction=getFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_frame, fragment);
                    transaction.commit();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        View view=navigationView.getHeaderView(0);
        ad=(TextView)view.findViewById(R.id.ad);
        ad.setText(girisYapanKullanici.getAd()+" "+girisYapanKullanici.getSoyad());

        mail=(TextView)view.findViewById(R.id.eposta);
        mail.setText(user.getEmail());

        profil=(ImageView)view.findViewById(R.id.profil_resmi);
        if (girisYapanKullanici.getTur().equals("ÖĞRENCİ")){
            profil.setImageResource(R.drawable.ogrenci);
        }else{
            profil.setImageResource(R.drawable.akademisyen);
        }


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBar.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }


}
