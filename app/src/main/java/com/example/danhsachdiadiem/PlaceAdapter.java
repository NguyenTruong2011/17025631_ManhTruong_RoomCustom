package com.example.danhsachdiadiem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {
    List<Place> lstUser;
    Context context;
    LayoutInflater inflater;
    View view;
    ISendPlace sendData;
    public PlaceAdapter(List<Place> data, Context context)
    {
        this.lstUser = data;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public PlaceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.item_rcv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceAdapter.ViewHolder holder, int position) {
        holder.txtId.setText(String.valueOf(position + 1));
        holder.txtPlace.setText(lstUser.get(position).getPlace());
        holder.imgRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData = (ISendPlace) context;
                sendData.sendUser(lstUser.get(position), 1);
            }
        });
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData = (ISendPlace) context;
                sendData.sendUser(lstUser.get(position), 2);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtId, txtPlace;
        ImageView imgRemove, imgEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtPlace = itemView.findViewById(R.id.txtPlaceName);
            imgEdit = itemView.findViewById(R.id.imgEdit);
            imgRemove = itemView.findViewById(R.id.imgDelete);
        }
    }
}