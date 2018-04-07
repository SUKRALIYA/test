package com.androidtutorialshub.loginregister.FacultyActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.model.Data;

import java.util.List;

/**
 * @author lusifer
 */
public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.Model> {
    List<Data> dataList;
    Context context;


    public FacultyAdapter(List<Data> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;

    }

    @Override
    public Model onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.faculty_item, parent, false);
        return new Model(v);
    }

    @Override
    public void onBindViewHolder(Model holder, int position) {
        holder.tv1.setText(dataList.get(position).getFaName());
        holder.tv2.setText(dataList.get(position).getFaPhone());
        holder.tv3.setText(dataList.get(position).getFaEmail());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class Model extends RecyclerView.ViewHolder{

        TextView tv1, tv2, tv3;
        Button bt;
        public Model(View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
            tv3 = itemView.findViewById(R.id.tv3);
             bt = itemView.findViewById(R.id.bt);
             bt.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent intent = new Intent(context, ViewDetailsActivity.class);
                     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                             context.startActivity(intent);
                             }
                             });
                             }
                             }
                             }
