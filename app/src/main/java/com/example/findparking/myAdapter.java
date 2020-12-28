package com.example.findparking;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

    private List<String> myList;
    private int rowLayout;
    private Context mContext;
    String name;
    String username;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView City;
        public Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            City = (TextView) itemView.findViewById(R.id.cityname);
            button = (Button) itemView.findViewById(R.id.citybutton);
        }
    }

    public myAdapter(List<String> myList, int rowLayout, String name, String username, Context context) {
        this.myList = myList;
        this.rowLayout = rowLayout;
        this.mContext = context;
        this.name = name;
        this.username = username;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final String entry = myList.get(i);
        viewHolder.City.setText(entry);
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReservationForm.class);
                intent.putExtra("CityName", entry);
                intent.putExtra("Name", name);
                intent.putExtra("Username", username);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }

}
