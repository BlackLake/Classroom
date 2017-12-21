package com.example.classroom.classroom;

import android.content.Intent;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private ImageView user;
    private EditText kullanici_adi_text,sifre_text;
    private TextInputLayout l_kullanici_adi,l_sifre;
    private Button giris_yap;
    private TextView kayit_ol;
    private EkranBoyutu ekran;
    private Uyarilar uyarilar;
    private InternetKontrolu internetKontrolu;
    private VeriTabani veriTabani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        uyarilar=new Uyarilar(MainActivity.this);
        internetKontrolu=new InternetKontrolu(this);
        veriTabani=new VeriTabani(MainActivity.this);
        ekran=new EkranBoyutu(this);

        try {
            if (internetKontrolu.internetBaglantiKontrol()){

                user=(ImageView)findViewById(R.id.user);
                ViewGroup.LayoutParams params=user.getLayoutParams();
                params.height=ekran.getYukseklik()/4;
                params.width=ekran.getGenislik()/3;
                user.setLayoutParams(params);

                giris_yap=(Button) findViewById(R.id.giris_yap);
                ekran.ekrana_ayarla(giris_yap,(ekran.getGenislik()/10)*8);

                kayit_ol=(TextView)findViewById(R.id.kayit_ol);
                ekran.ekrana_ayarla(kayit_ol,(ekran.getGenislik()/10)*7);

               /* l_kullanici_adi=(TextInputLayout)findViewById(R.id.kullanici_adi);
                l_sifre=(TextInputLayout)findViewById(R.id.sifre);
                ekran.ekrana_ayarla(l_kullanici_adi,(ekran.getGenislik()/10)*8);
                ekran.ekrana_ayarla(l_sifre,(ekran.getGenislik()/10)*8);
*/
                kullanici_adi_text=(EditText)findViewById(R.id.kullanici_adi_text);
                sifre_text=(EditText)findViewById(R.id.sifre_text);

                giris_yap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (bosAlanKontrol(kullanici_adi_text.getText().toString().trim(),sifre_text.getText().toString().trim())){
                            veriTabani.girisYap(kullanici_adi_text.getText().toString().trim(),sifre_text.getText().toString().trim());
                            kullanici_adi_text.setText("");
                            sifre_text.setText("");
                        }else{
                            uyarilar.hata("Boş Alan var!");
                        }
                    }
                });
                kayit_ol.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this,UyeOl.class));
                        finish();

                    }
                });
                ekran.ekrana_ayarla(kullanici_adi_text,(ekran.getGenislik()/10)*8);
                ekran.ekrana_ayarla(sifre_text,(ekran.getGenislik()/10)*8);

            }
        }catch (Exception e){
            uyarilar.uygulamaKapat();
        }



    }

    private boolean bosAlanKontrol(String mail,String sifre){
        if (TextUtils.isEmpty(mail)||TextUtils.isEmpty(sifre))
            return false;
        return true;
    }


}
