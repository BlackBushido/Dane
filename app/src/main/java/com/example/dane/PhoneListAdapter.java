package com.example.dane;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhoneListAdapter extends RecyclerView.Adapter<PhoneListAdapter.PhoneViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<Phone> mPhoneList;

    public PhoneListAdapter(Context context){
        mLayoutInflater = LayoutInflater.from(context);
        this.mPhoneList = null;
    }

    @NonNull
    @Override
    public PhoneListAdapter.PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View wiersz = mLayoutInflater.inflate(R.layout.phone_view, parent, false);
        return new PhoneViewHolder(wiersz);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHolder holder, int position) {

        if(holder.getAdapterPosition() == 0){
            holder.producer.setText(R.string.producer);
            holder.model.setText(R.string.model);

        }else{
            Phone phone = mPhoneList.get(position - 1);
            holder.producer.setText(phone.getMProducer());
            holder.model.setText(phone.getMModel());
            holder.phone = phone;

            holder.phoneRow.setOnClickListener(view -> {
                Context context = view.getContext();
                if(context instanceof MainActivity){
                    ((MainActivity) context).startUpdateActivity(phone);
                }else{
                    System.out.println("Error in OnBindViewHolder");
                }
            });
        }

    }

    @Override
    public int getItemCount(){
        if(mPhoneList != null)
            return mPhoneList.size() + 1;
        return 0;
    }

    public void setPhoneList(List<Phone> phoneList){
        this.mPhoneList = phoneList;
        notifyDataSetChanged();
    }

    public class PhoneViewHolder extends RecyclerView.ViewHolder {
        LinearLayout phoneRow;
        TextView producer;
        TextView model;
        Phone phone;

        public PhoneViewHolder(@NonNull View view) {
            super(view);
            producer = itemView.findViewById(R.id.producer);
            model = itemView.findViewById(R.id.model);
            phoneRow = itemView.findViewById(R.id.phoneRow);
            phone = null;
        }

    }

}
