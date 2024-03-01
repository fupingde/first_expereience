package com.example.first_expereience;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.first_expereience.studentManageSystem.Sayings;

import java.util.ArrayList;

public class VPAdapter extends RecyclerView.Adapter<VPAdapter.InnerHolder>{
    //RecycleView适配器代码
private ArrayList<Sayings> sayings;
    public VPAdapter(ArrayList<Sayings> sayings){
        this.sayings=sayings;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new
                InnerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_2,parent,false));
    }
    @Override public void onBindViewHolder(@NonNull InnerHolder holder, int i) {
        holder.textView.setText(sayings.get(i).getSayings());
    }
    //返回总共要生产多少个View的数量
    @Override
    public int getItemCount() {
        return sayings.size();
    }
    public static class InnerHolder extends RecyclerView.ViewHolder {

        //找到item里面的View
        private TextView textView;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvSaying);

        }
    }

}
