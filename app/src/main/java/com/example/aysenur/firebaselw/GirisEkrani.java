package com.example.aysenur.firebaselw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GirisEkrani extends AppCompatActivity {


    private EditText sehirgir, ilcegir, mekangir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sehirgir = (EditText) findViewById(R.id.sehirgir);
        ilcegir = (EditText) findViewById(R.id.ilcegir);
        mekangir = (EditText) findViewById(R.id.mekan);
    }
       public void tiklendi(View v){
        if(v.getId()==R.id.button2) {
            Intent intent = new Intent(GirisEkrani.this, KonumlariGoster.class);


            String sehir = sehirgir.getText().toString();
            String ilce = ilcegir.getText().toString();
            String mekan = mekangir.getText().toString();
            sehirekle(sehir, ilce, mekan);

            startActivity(intent);

            }
            else if(v.getId()==R.id.btnKonum){
            Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
            startActivity(intent);
        }


    }


    private void sehirekle(String sehir, String ilceler, String mekan) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("sehirler");
        Sehirler bilgilerigir = new Sehirler();
        bilgilerigir.ilceler = ilceler;
        bilgilerigir.mekan = mekan;
        mDatabase.push().child(sehir).setValue(bilgilerigir);


    }

}
