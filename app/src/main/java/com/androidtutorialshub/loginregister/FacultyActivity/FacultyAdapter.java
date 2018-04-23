package com.androidtutorialshub.loginregister.FacultyActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.model.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lusifer
 */
public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.Model> implements Filterable {
    List<Data> dataList;
    List<Data> mFilteredList;
    Context context;

    public FacultyAdapter(List<Data> dataList, Context context) {
        this.dataList = dataList;
        this.mFilteredList = dataList;
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
        holder.facName.setText(mFilteredList.get(position).getFaName());
        holder.facPhone.setText(mFilteredList.get(position).getFaPhone());
        holder.facEmail.setText(mFilteredList.get(position).getFaEmail());
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    public class Model extends RecyclerView.ViewHolder {

        TextView facName, facPhone, facEmail;
        Button bt;

        public Model(View itemView) {
            super(itemView);
            facName = itemView.findViewById(R.id.facname);
            facPhone = itemView.findViewById(R.id.facphone);
            facEmail = itemView.findViewById(R.id.facemail);
            bt = itemView.findViewById(R.id.facbtn);

            bt.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ViewDetailsActivity.class);
                    intent.putExtra(ViewDetailsActivity.DATA, dataList.get(getAdapterPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = dataList;
                } else {

                    ArrayList<Data> filteredList = new ArrayList<>();

                    for (Data androidVersion : dataList) {

                        if (androidVersion.getFaName().toLowerCase().contains(charString) ||
                                androidVersion.getFaPhone().toLowerCase().contains(charString) ||
                                androidVersion.getFaEmail().toLowerCase().contains(charString)) {

                            filteredList.add(androidVersion);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Data>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
