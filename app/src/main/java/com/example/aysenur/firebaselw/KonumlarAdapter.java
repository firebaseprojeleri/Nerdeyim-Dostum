package com.example.aysenur.firebaselw;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class KonumlarAdapter extends RecyclerView.Adapter<KonumlarAdapter.SehirHolder> {

    private ArrayList<Sehirler> konumlarList;

    private ISehirSelectedListener listener = null;

    public interface ISehirSelectedListener {
        void onPosition(int position);
    }

    public KonumlarAdapter(ArrayList<Sehirler> konumlar, ISehirSelectedListener listener) {
        konumlarList = konumlar;
        this.listener = listener;
    }


    @Override
    public SehirHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.satirgorunum,parent, false);
        return new SehirHolder(view);
    }

    @Override
    public void onBindViewHolder(SehirHolder holder, final int position) {
        Sehirler konumlar = konumlarList.get(position);

        holder.tvIlceAdi.setText(konumlar.getIlceler());
        holder.tvMekanAdi.setText(konumlar.getMekan());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onPosition(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return konumlarList.size();
    }

    class SehirHolder extends RecyclerView.ViewHolder {

        public TextView tvIlceAdi, tvMekanAdi;
        public LinearLayout content;

        public SehirHolder(View itemView) {
            super(itemView);
            content = (LinearLayout) itemView.findViewById(R.id.satirgorunum_content);
            tvIlceAdi =
                    (TextView) itemView.findViewById(R.id.tvIlceAdi);
            tvMekanAdi =
                    (TextView) itemView.findViewById(R.id.tvMekanAdi);
        }
    }
/* bellekte adapterin yer almaması için */
    public void onDestroy() {
        listener = null;
    }
}