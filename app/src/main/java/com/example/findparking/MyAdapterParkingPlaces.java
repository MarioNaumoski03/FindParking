package com.example.findparking;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class MyAdapterParkingPlaces extends RecyclerView.Adapter<MyAdapterParkingPlaces.ParkingViewHolder> {

    private List<String> myList;
    private int rowLayout;
    private Context mContext;
    private String selectedTimeSlot;
    private String selectedDate;
    private String username;
    private String name;
    private DBHelper database;

    public class ParkingViewHolder extends RecyclerView.ViewHolder {
        public TextView parkingName;
        public Button button;
        public TextView freePlaces;
        public TextView takenPlaces;

        public ParkingViewHolder(View itemView) {
            super(itemView);
            parkingName = (TextView) itemView.findViewById(R.id.parkingcityname);
            button = (Button) itemView.findViewById(R.id.parkingcitybutton);
            freePlaces = (TextView) itemView.findViewById(R.id.freeplaces);
            takenPlaces = (TextView) itemView.findViewById(R.id.takenplaces);
        }
    }

    public MyAdapterParkingPlaces(List<String> myList, int rowLayout, String selectedDate, String selectedTimeslot, String username, String name, DBHelper database, Context context) {
        this.myList = myList;
        this.rowLayout = rowLayout;
        this.mContext = context;
        this.selectedDate = selectedDate;
        this.selectedTimeSlot = selectedTimeslot;
        this.username = username;
        this.name = name;
        this.database = database;
    }

    @Override
    public ParkingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ParkingViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ParkingViewHolder viewHolder, int i) {
        final String entry = myList.get(i);
        int totalSpaces = database.getTotalSpaces(entry);
        final int freeSpaces = totalSpaces - database.getNumberOfReservations(selectedDate, selectedTimeSlot, entry);
        int takenSpaces = totalSpaces - freeSpaces;


        String takenS = String.valueOf(takenSpaces);
        String freeS = String.valueOf(freeSpaces);

        viewHolder.parkingName.setText(entry);
        viewHolder.takenPlaces.setText(takenS);
        viewHolder.freePlaces.setText(freeS);

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (freeSpaces != 0) {
                   // database.insertIntoReservations(username, entry, selectedDate, selectedTimeSlot);
                    Intent intent = new Intent(v.getContext(), ConfirmMessage.class);
                    intent.putExtra("Ime", entry);
                    intent.putExtra("Korisnichko", username);
                    intent.putExtra("Datum", selectedDate);
                    intent.putExtra("Vreme", selectedTimeSlot);
                    intent.putExtra("Name", name);
                    v.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }

}
