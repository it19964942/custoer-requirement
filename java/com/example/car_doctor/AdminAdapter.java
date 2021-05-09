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

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.adminViewHolder> {
    private Context context;
    private ArrayList pId,utype,year,month,amt;
    int position;
    Activity activity;
    AdminAdapter(Activity activity,Context context, ArrayList pId,ArrayList utype, ArrayList year, ArrayList month,ArrayList amount){
        this.activity = activity;
        this.context = context;
        this.pId = pId;
        this.utype = utype;
        this.year = year;
        this.month = month;
        this.amt = amount;
    }
    @NonNull
    @Override
    public AdminAdapter.adminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.admin_pannal, parent, false);
        return new adminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminAdapter.adminViewHolder holder, final int position) {
        this.position = position;
        holder.id.setText(String.valueOf(pId.get(position)));
        holder.type.setText(String.valueOf(utype.get(position)));
        holder.u_year.setText(String.valueOf(year.get(position)));
        holder.u_month.setText(String.valueOf(month.get(position)));
        holder.u_amt.setText(String.valueOf(amt.get(position)));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,updateAdminPaymentDetailsActivity.class);
                i.putExtra("id",String.valueOf(pId.get(position)));
                i.putExtra("type",String.valueOf(utype.get(position)));
                i.putExtra("year",String.valueOf(year.get(position)));
                i.putExtra("month",String.valueOf(month.get(position)));
                i.putExtra("amount",String.valueOf(amt.get(position)));
                activity.startActivityForResult(i,1);
            }
        });
    }

    @Override
    public int getItemCount(){return pId.size();}

    public class adminViewHolder extends RecyclerView.ViewHolder {
        TextView id,type,u_year,u_month,u_amt;
        LinearLayout linearLayout;
        public adminViewHolder(@NonNull View itemView) {
            super(itemView);
            id =itemView.findViewById(R.id.lblAId);
            type =itemView.findViewById(R.id.lblUserType);
            u_year =itemView.findViewById(R.id.lblYear);
            u_month =itemView.findViewById(R.id.lblMonth);
            u_amt =itemView.findViewById(R.id.lblTotAmt);
            linearLayout =itemView.findViewById(R.id.mainLayoutAD);
        }
    }
}
