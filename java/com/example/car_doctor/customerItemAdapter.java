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

public class customerItemAdapter extends RecyclerView.Adapter<customerItemAdapter.ViewHolder> {
    private Context context;
    private ArrayList itid, itname, itbrand, itsize, itweight, itprice;
    int position;
    Activity activity;

    customerItemAdapter(Activity activity,Context context, ArrayList itid, ArrayList itname,
                        ArrayList itbrand, ArrayList itsize, ArrayList itweight, ArrayList itprice) {

        this.activity = activity;
        this.context = context;
        this.itid = itid;
        this.itname = itname;
        this.itbrand = itbrand;
        this.itsize = itsize;
        this.itweight = itweight;
        this.itprice = itprice;
    }
    @NonNull
    @Override
    public customerItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cusomer_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull customerItemAdapter.ViewHolder holder, final int position) {
        this.position = position;
        holder.id.setText(String.valueOf(itid.get(position)));
        holder.itname.setText(String.valueOf(itname.get(position)));
        holder.itbrand.setText(String.valueOf(itbrand.get(position)));
        holder.itsize.setText(String.valueOf(itsize.get(position)));
        holder.itweight.setText(String.valueOf(itweight.get(position)));
        holder.itprice.setText(String.valueOf(itprice.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,CreateOrderActivity.class);
                i.putExtra("id",String.valueOf(itid.get(position)));
                i.putExtra("name",String.valueOf(itname.get(position)));
                i.putExtra("brand",String.valueOf(itbrand.get(position)));
                i.putExtra("size",String.valueOf(itsize.get(position)));
                i.putExtra("weight",String.valueOf(itweight.get(position)));
                i.putExtra("uprice",String.valueOf(itprice.get(position)));
                activity.startActivityForResult(i,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itid.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, itname, itbrand, itsize, itweight, itprice;
        LinearLayout mainLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.etItemId2);
            itname = itemView.findViewById(R.id.etItemName2);
            itbrand = itemView.findViewById(R.id.etBrand2);
            itsize = itemView.findViewById(R.id.etSize2);
            itweight = itemView.findViewById(R.id.etWeight2);
            itprice = itemView.findViewById(R.id.etuprice2);
            mainLayout =itemView.findViewById(R.id.mainLayout2);
        }
    }
}
