package com.example.dane;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        Phone phone = mPhoneList.get(position);
        holder.producer.setText(phone.getMProducer());
        holder.model.setText(phone.getMModel());
        //holder.phone = phone;
//        holder.phoneRow.setOnClickListener(view -> {
//            Context context = view.getContext();
//            if (context instanceof MainActivity) {
//                ((MainActivity) context).startUpdateActivity(phone);
//            } else {
//                System.out.println("PHONELISTADAPTER onBINDVIEWHOLER context error");
//            }
//        });

    }

    @Override
    public int getItemCount(){
        if(mPhoneList != null)
            return mPhoneList.size();
        return 0;
    }

    public void setPhoneList(List<Phone> phoneList){
        this.mPhoneList = phoneList;
        notifyDataSetChanged();
    }

    public class PhoneViewHolder extends RecyclerView.ViewHolder {
        private final TextView producer;
        private final TextView model;

        public PhoneViewHolder(@NonNull View view) {
            super(view);
            producer = itemView.findViewById(R.id.producer);
            model = itemView.findViewById(R.id.model);
        }

    }

}
