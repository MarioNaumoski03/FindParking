package com.example.findparking;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.findparking.ConfirmMessage;
import com.example.findparking.DBHelper;

import java.util.List;

public class MyAdapterReservations extends RecyclerView.Adapter<MyAdapterReservations.ReservationsViewHolder> {
    private List<List<String>> myList;
    private int rowLayout;
    private Context mContext;
    private String username;
    private String name;
   public DBHelper database;

    public class ReservationsViewHolder extends RecyclerView.ViewHolder {
        public TextView parkingName;
        public TextView cityName;
        public TextView dateTime;
        public Button details;

        public ReservationsViewHolder(View itemView) {
            super(itemView);
            parkingName = (TextView) itemView.findViewById(R.id.parkingnamereservations);
            cityName = (TextView) itemView.findViewById(R.id.citynamereservations);
            dateTime = (TextView) itemView.findViewById(R.id.dateandtime);
            details = (Button) itemView.findViewById(R.id.buttonreservations);
        }
    }

    public MyAdapterReservations(List<List<String>> myList, int rowLayout, String username, String name, DBHelper database, Context context) {
        this.myList = myList;
        this.rowLayout = rowLayout;
        this.mContext = context;
        this.username = username;
        this.database = database;
        this.name = name;
    }

    public ReservationsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ReservationsViewHolder(v);
    }

    public void onBindViewHolder(ReservationsViewHolder viewHolder, int i) {
        final List<String> entry = myList.get(i);
       // final float lat = database.getLat(entry.get(0));
        //final float lng = database.getLong(entry.get(0));
        viewHolder.parkingName.setText(entry.get(0));
        viewHolder.dateTime.setText(entry.get(1) + "\n" + entry.get(2));
        viewHolder.cityName.setText(database.getCity(entry.get(0)));
        viewHolder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ConfirmMessage.class);
                intent.putExtra("Name", entry.get(0));
                intent.putExtra("Username", username);
                intent.putExtra("Date", entry.get(1));
                intent.putExtra("Time", entry.get(2));
                //intent.putExtra("Lat", lat);
               // intent.putExtra("Long", lng);
                intent.putExtra("Name", name);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }

}