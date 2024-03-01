package com.example.first_expereience;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.first_expereience.studentManageSystem.StudentInfo;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.InnerHolder>{

    //RecycleView适配器代码
    private ArrayList<StudentInfo.Student>students;
    public Adapter(ArrayList<StudentInfo.Student>students){
        this.students=students;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new
                InnerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_1,parent,false));
    }
    @Override public void onBindViewHolder(@NonNull InnerHolder holder, int i) {
        holder.tvName.setText(students.get(i).getName());
        holder.tvGender.setText(students.get(i).getGender());
        holder.tvGrade.setText(students.get(i).getGrade());
        holder.tvLocation.setText(students.get(i).getLocation());
    }
    //返回总共要生产多少个View的数量
    @Override
    public int getItemCount() {
        return students.size();
    }
    public static class InnerHolder extends RecyclerView.ViewHolder {

        //找到item里面的View
        TextView tvID;
        TextView tvName;
        TextView tvGender;
        TextView tvGrade;
        TextView tvLocation;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvGender=(TextView)itemView.findViewById(R.id.tvGender);
            tvGrade = (TextView) itemView.findViewById(R.id.tvGrade);
            tvLocation = (TextView) itemView.findViewById(R.id.tvLocation);
        }
        //对InnerHolder中初始化好的控件进行数据绑定

    }

}
