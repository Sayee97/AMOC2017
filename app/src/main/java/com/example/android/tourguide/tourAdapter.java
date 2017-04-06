package com.example.android.tourguide;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dream on 04-04-2017.
 */

public class tourAdapter extends ArrayAdapter{

    List list = new ArrayList();
    public tourAdapter(Context context,int resource) {
        super(context, resource);
    }


    public void add(info object) {
        list.add(object);
        super.add(object);
    }

    public void deleteRowItem(info Item){
        this.list.remove(Item);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        View row = convertView;
        Holder holder;
        if (row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row =  layoutInflater.inflate(R.layout.list_item,parent,false);
            holder = new Holder();
            holder.address = (TextView) row.findViewById(R.id.address);
            holder.destination = (TextView) row.findViewById(R.id.destination);
            holder.inDate = (TextView) row.findViewById(R.id.inDate);
            holder.outDate = (TextView) row.findViewById(R.id.outDate);
            holder.inTime = (TextView) row.findViewById(R.id.inTime);
            holder.outTime = (TextView) row.findViewById(R.id.outTime);

            row.setTag(holder);
        }
        else {
             holder= (Holder) row.getTag();
        }

        info Info = (info) getItem(position);
        holder.destination.setText(Info.getDestination());
        holder.address.setText(Info.getAddress());
        holder.inTime.setText(Info.getInTime());
        holder.outTime.setText(Info.getOutTime());
        holder.inDate.setText(Info.getInDate());
        holder.outDate.setText(Info.getOutDate());

        return row;
    }

    static class Holder{
        TextView id,destination,address,inDate,outDate,inTime,outTime ;
    }




}
