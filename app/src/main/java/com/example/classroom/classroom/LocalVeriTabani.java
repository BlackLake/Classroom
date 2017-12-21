package com.example.classroom.classroom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fatih on 18.12.2017.
 */

public class LocalVeriTabani extends SQLiteOpenHelper{

    private static final String DATABASE_NAME="classroom.db";

    private static final String TABLE_KULLANICILAR="kullanicilar";
    private static final String TABLE_GIRIS_YAPAN="giris_yapan";


    private static final String SUTUN_KULLANICI_ID="kullanici_id";
    private static final String SUTUN_KULLANICI_ADI="kullanici_adi";
    private static final String SUTUN_KULLANICI_SOYADI="kullanici_soyadi";
    private static final String SUTUN_KULLANICI_TUR="kullanici_tur";
    private static final String SUTUN_KULLANICI_DEGISKEN="kullanici_degisken";


    private String table_kullanicilar="CREATE TABLE "+TABLE_KULLANICILAR+"("
            +SUTUN_KULLANICI_ID+" TEXT NOT NULL PRIMARY KEY,"
            +SUTUN_KULLANICI_ADI+" TEXT NOT NULL,"
            +SUTUN_KULLANICI_SOYADI+" TEXT NOT NULL,"
            +SUTUN_KULLANICI_TUR+" TEXT NOT NULL,"
            +SUTUN_KULLANICI_DEGISKEN+" TEXT NOT NULL"+
            ")";

    private String table_giris_yapan_kullanici="CREATE TABLE "+TABLE_GIRIS_YAPAN+"("
            +SUTUN_KULLANICI_ID+" TEXT NOT NULL,"
            +SUTUN_KULLANICI_ADI+" TEXT NOT NULL,"
            +SUTUN_KULLANICI_SOYADI+" TEXT NOT NULL,"
            +SUTUN_KULLANICI_TUR+" TEXT NOT NULL,"
            +SUTUN_KULLANICI_DEGISKEN+" TEXT NOT NULL"+
            ")";


    public LocalVeriTabani(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(table_kullanicilar);
        database.execSQL(table_giris_yapan_kullanici);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL("DROP TABLE IF EXISTS "+TABLE_KULLANICILAR);
        database.execSQL("DROP TABLE IF EXISTS "+TABLE_GIRIS_YAPAN);
        onCreate(database);
    }

    public void kullaniciEkle(Kullanici kullanici){
        try {
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues values=new ContentValues();
            values.put(SUTUN_KULLANICI_ID,kullanici.getId());
            values.put(SUTUN_KULLANICI_ADI,kullanici.getAd());
            values.put(SUTUN_KULLANICI_SOYADI,kullanici.getSoyad());
            values.put(SUTUN_KULLANICI_TUR,kullanici.getTur());
            values.put(SUTUN_KULLANICI_DEGISKEN,kullanici.getDegisken());
            db.insert(TABLE_KULLANICILAR,null,values);
            db.close();
        }catch (Exception e){

        }


    }


    public List<Kullanici> tumKullanicilariGetir(){
        List<Kullanici> kullanicilar=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        String[] sutunlar={SUTUN_KULLANICI_ID,SUTUN_KULLANICI_ADI,SUTUN_KULLANICI_SOYADI,SUTUN_KULLANICI_TUR,SUTUN_KULLANICI_DEGISKEN};
        Cursor cursor=db.query(TABLE_KULLANICILAR,sutunlar,null,null,null,null,null);
        while (cursor.moveToNext()){
            Kullanici kullanici=new Kullanici();
            kullanici.setId(cursor.getString(cursor.getColumnIndex(SUTUN_KULLANICI_ID)));
            kullanici.setAd(cursor.getString(cursor.getColumnIndex(SUTUN_KULLANICI_ADI)));
            kullanici.setSoyad(cursor.getString(cursor.getColumnIndex(SUTUN_KULLANICI_SOYADI)));
            kullanici.setTur(cursor.getString(cursor.getColumnIndex(SUTUN_KULLANICI_TUR)));
            kullanici.setDegisken(cursor.getString(cursor.getColumnIndex(SUTUN_KULLANICI_DEGISKEN)));
            kullanicilar.add(kullanici);

        }
        return kullanicilar;
    }

    public void girisYapanKullaniciTut(String id,String ad,String soyad,String tur,String degisken){
        SQLiteDatabase db=this.getWritableDatabase();
        girisYapanTabloBosalt();
        ContentValues values=new ContentValues();
        values.put(SUTUN_KULLANICI_ID,id);
        values.put(SUTUN_KULLANICI_ADI,ad);
        values.put(SUTUN_KULLANICI_SOYADI,soyad);
        values.put(SUTUN_KULLANICI_TUR,tur);
        values.put(SUTUN_KULLANICI_DEGISKEN,degisken);
        db.insert(TABLE_GIRIS_YAPAN,null,values);
        db.close();
    }
    public Kullanici girisYapanKullanici(){
        Kullanici kullanici=new Kullanici();
        SQLiteDatabase db=this.getReadableDatabase();
        String[] sutunlar={SUTUN_KULLANICI_ID,SUTUN_KULLANICI_ADI,SUTUN_KULLANICI_SOYADI,SUTUN_KULLANICI_TUR,SUTUN_KULLANICI_DEGISKEN};
        Cursor cursor=db.query(TABLE_GIRIS_YAPAN,sutunlar,null,null,null,null,null);
        while (cursor.moveToNext()){
            kullanici.setId(cursor.getString(cursor.getColumnIndex(SUTUN_KULLANICI_ID)));
            kullanici.setAd(cursor.getString(cursor.getColumnIndex(SUTUN_KULLANICI_ADI)));
            kullanici.setSoyad(cursor.getString(cursor.getColumnIndex(SUTUN_KULLANICI_SOYADI)));
            kullanici.setTur(cursor.getString(cursor.getColumnIndex(SUTUN_KULLANICI_TUR)));
            kullanici.setDegisken(cursor.getString(cursor.getColumnIndex(SUTUN_KULLANICI_DEGISKEN)));

        }
        db.close();
        return kullanici;
    }
    private void girisYapanTabloBosalt(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DROP TABLE "+TABLE_GIRIS_YAPAN);
        db.execSQL(table_giris_yapan_kullanici);
    }


}
