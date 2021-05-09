package com.example.car_doctor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OderAdapter extends RecyclerView.Adapter<OderAdapter.OderViewHolder> {
    private Context context;
    private ArrayList itmId,oderIdn,qty;
    int position;
    Activity activity;
    OderAdapter(Activity activity,Context context, ArrayList odId, ArrayList iId, ArrayList qty){
        this.activity = activity;
        this.context = context;
        this.itmId = iId;
        this.oderIdn = odId;
        this.qty = qty;
    }
    @NonNull
    @Override
    public OderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.alloders, parent, false);
        return new OderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OderViewHolder holder, final int position) {
        this.position = position;
        holder.oderId.setText(String.valueOf(oderIdn.get(position)));
        holder.ItemId.setText(String.valueOf(itmId.get(position)));
        holder.Qty.setText(String.valueOf(qty.get(position)));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,UpdateOderDetailsActivity.class);
               i.putExtra("odrId",String.valueOf(oderIdn.get(position)));
                i.putExtra("itemId",String.valueOf(itmId.get(position)));
                i.putExtra("qty",String.valueOf(qty.get(position)));

               //i.putExtra("name",String.valueOf(oderIdn.get(position)));
                activity.startActivityForResult(i,1);
            }
        });
    }

    @Override
    public int getItemCount(){return oderIdn.size();}

    public static class OderViewHolder extends RecyclerView.ViewHolder {
        TextView oderId,ItemId,Qty;
        LinearLayout linearLayout;
        public OderViewHolder(@NonNull View itemView) {
            super(itemView);
            oderId = itemView.findViewById(R.id.odOderId);
            ItemId = itemView.findViewById(R.id.odItemId);
            Qty = itemView.findViewById(R.id.oderqty);
            linearLayout = itemView.findViewById(R.id.mainLayoutOder);
        }
    }
}
