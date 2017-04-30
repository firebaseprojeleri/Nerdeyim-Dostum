package com.example.aysenur.firebaselw;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Aysenur on 19.04.2017.
 */

public class KonumlariGoster extends AppCompatActivity implements KonumlarAdapter.ISehirSelectedListener {
    public RecyclerView lwveriler;

    ArrayList<Sehirler> alSehirler = null;
    KonumlarAdapter adapterSehirler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konumlar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(KonumlariGoster.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        alSehirler = new ArrayList<>();
        adapterSehirler = new KonumlarAdapter(alSehirler, this);

        lwveriler = (RecyclerView) findViewById(R.id.lwkonumlar);
        lwveriler.setHasFixedSize(true);
        lwveriler.setItemAnimator(new DefaultItemAnimator());
        lwveriler.setLayoutManager(layoutManager);
        lwveriler.setAdapter(adapterSehirler);
        getDatabase();
    }

    private void getDatabase() {

        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = fb.getReference().child("sehirler");

        dbRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot dataSnapshot1 : noteDataSnapshot.getChildren()) {
                        Sehirler city = new Sehirler();
                        city.sehirAdi = dataSnapshot1.child("ilceler").getValue().toString();
                        city.mekan = dataSnapshot1.child("mekan").getValue().toString();
                        alSehirler.add(city);
                    }
                }

                adapterSehirler.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    @Override
    public void onPosition(int position) {
        alSehirler.get(position).getIlceler();
    }
/* bellekte yer kaplamaması için */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapterSehirler.onDestroy();
    }
}



