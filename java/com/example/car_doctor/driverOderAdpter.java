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
import java.util.jar.JarEntry;

public class driverOderAdpter extends RecyclerView.Adapter<driverOderAdpter.driverViewHolder> {
    private Context context;
    private ArrayList d_id, oder_id,cost_per_unit;
    int position;
    Activity activity;
   driverOderAdpter(Activity activity, Context context, ArrayList d_id, ArrayList oder_id, ArrayList cost_u){
       this.activity = activity;
       this.context = context;
       this.d_id = d_id;
       this.oder_id =oder_id;
       this.cost_per_unit = cost_u;
   }
    @NonNull
    @Override
    public driverOderAdpter.driverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.driveroders, parent, false);
        return new driverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull driverOderAdpter.driverViewHolder holder, final int position) {
        this.position = position;
        holder.id.setText(String.valueOf(d_id.get(position)));
        holder.oder_id.setText(String.valueOf(oder_id.get(position)));
        holder.amt.setText(String.valueOf(cost_per_unit.get(position)));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,updateDriverJobQueueActivity.class);
                i.putExtra("jobId",String.valueOf(d_id.get(position)));
                i.putExtra("oderId",String.valueOf(oder_id.get(position)));
                i.putExtra("unitPrice",String.valueOf(cost_per_unit.get(position)));
                activity.startActivityForResult(i,1);
            }
        });
    }

    @Override
    public int getItemCount() {return d_id.size();
    }

    public class driverViewHolder extends RecyclerView.ViewHolder {
       TextView id,oder_id,amt;
       LinearLayout linearLayout;
        public driverViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.odItemIdD1);
            oder_id = itemView.findViewById(R.id.odOderIdD1);
            amt = itemView.findViewById(R.id.oderqtyD1);
            linearLayout = itemView.findViewById(R.id.mainLayoutDriver2);
        }
    }
}
