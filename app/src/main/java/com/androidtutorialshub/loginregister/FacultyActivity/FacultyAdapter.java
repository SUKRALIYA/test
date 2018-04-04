package com.androidtutorialshub.loginregister.FacultyActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.model.Data;

import java.util.List;

/**
 * @author lusifer
 */
public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.Model> {
    List<Data> dataList;


    public FacultyAdapter(List<Data> dataList) {
        this.dataList = dataList;
    }

    @Override
    public Model onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.faculty_item, parent, false);
        return new Model(v);
    }

    @Override
    public void onBindViewHolder(Model holder, int position) {
        holder.tv.setText(dataList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class Model extends RecyclerView.ViewHolder{

        TextView tv;
        public Model(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
