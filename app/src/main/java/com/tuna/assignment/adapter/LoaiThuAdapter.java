package com.tuna.assignment.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.tuna.assignment.R;
import com.tuna.assignment.database.LoaiThuDAO;
import com.tuna.assignment.model.LoaiChi;
import com.tuna.assignment.model.LoaiThu;

import java.util.ArrayList;
import java.util.List;

public class LoaiThuAdapter extends BaseAdapter implements Filterable {
    List<LoaiThu> arrLoaiThu;
    List<LoaiThu> arrSortLoaiThu;
    private Filter LoaiThuFilter;
    public Activity context;
    public LayoutInflater inflater;
    LoaiThuDAO loaiThuDAO;


    public LoaiThuAdapter(Activity context, List<LoaiThu> arrLoaiThu) {
        super();
        this.context = context;
        this.arrLoaiThu = arrLoaiThu;
        this.arrSortLoaiThu = arrLoaiThu;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        loaiThuDAO = new LoaiThuDAO(context);
    }

    @Override
    public int getCount() {
        return arrLoaiThu.size();
    }

    @Override
    public Object getItem(int position) {
        return arrLoaiThu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        TextView txtMaLoaiThu,txtTenLoaiThu;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_loaithu, null);
            holder.txtMaLoaiThu = (TextView) convertView.findViewById(R.id.tvSTT);
            holder.txtTenLoaiThu = (TextView) convertView.findViewById(R.id.tvName);
//
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        LoaiThu _entry = (LoaiThu) arrLoaiThu.get(position);
        holder.txtMaLoaiThu.setText(_entry.getMaLoaiThu());
        holder.txtTenLoaiThu.setText(_entry.getTenLoaiThu());
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDatasetLoaiThu(List<LoaiThu> items) {
        this.arrLoaiThu = items;
        notifyDataSetChanged();
    }

    public void resetData() {
        arrLoaiThu = arrSortLoaiThu;
    }

    @Override
    public Filter getFilter() {
        if (LoaiThuFilter == null)
            LoaiThuFilter = new CustomFilter();
        return LoaiThuFilter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortLoaiThu;
                results.count = arrSortLoaiThu.size();
            } else {
                List<LoaiThu> lsLoaiThu = new ArrayList<LoaiThu>();
                for (LoaiThu p : arrLoaiThu) {
                    if (p.getMaLoaiThu().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lsLoaiThu.add(p);
                }
                results.values = lsLoaiThu;
                results.count = lsLoaiThu.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                arrLoaiThu = (List<LoaiThu>) results.values;
                notifyDataSetChanged();
            }
        }

    }


}
