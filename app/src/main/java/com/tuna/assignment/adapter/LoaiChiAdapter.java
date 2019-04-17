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
import com.tuna.assignment.database.LoaiChiDAO;
import com.tuna.assignment.model.KhoanChi;
import com.tuna.assignment.model.LoaiChi;

import java.util.ArrayList;
import java.util.List;

public class LoaiChiAdapter extends BaseAdapter implements Filterable {
    List<LoaiChi> arrLoaiChi;
    List<LoaiChi> arrSortLoaiChi;
    private Filter LoaiChiFilter;
    public Activity context;
    public LayoutInflater inflater;
    LoaiChiDAO loaiChiDAO;


    public LoaiChiAdapter(Activity context, List<LoaiChi> arrLoaiChi) {
        super();
        this.context = context;
        this.arrLoaiChi = arrLoaiChi;
        this.arrSortLoaiChi = arrLoaiChi;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        loaiChiDAO = new LoaiChiDAO(context);
    }

    @Override
    public int getCount() {
        return arrLoaiChi.size();
    }

    @Override
    public Object getItem(int position) {
        return arrLoaiChi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        TextView txtMaLoaiChi,txtTenLoaiChi;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_loaichi, null);
            holder.txtMaLoaiChi = (TextView) convertView.findViewById(R.id.tvSTT);
            holder.txtTenLoaiChi = (TextView) convertView.findViewById(R.id.tvName);
//
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        LoaiChi _entry = (LoaiChi) arrLoaiChi.get(position);
        holder.txtMaLoaiChi.setText(_entry.getMaLoaiChi());
        holder.txtTenLoaiChi.setText(_entry.getTenLoaiChi());
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDatasetLoaiChi(List<LoaiChi> items) {
        this.arrLoaiChi = items;
        notifyDataSetChanged();
    }

    public void resetData() {
        arrLoaiChi = arrSortLoaiChi;
    }

    @Override
    public Filter getFilter() {
        if (LoaiChiFilter == null)
            LoaiChiFilter = new CustomFilter();
        return LoaiChiFilter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortLoaiChi;
                results.count = arrSortLoaiChi.size();
            } else {
                List<LoaiChi> lsLoaiChi = new ArrayList<LoaiChi>();
                for (LoaiChi p : arrLoaiChi) {
                    if (p.getMaLoaiChi().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lsLoaiChi.add(p);
                }
                results.values = lsLoaiChi;
                results.count = lsLoaiChi.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                arrLoaiChi = (List<LoaiChi>) results.values;
                notifyDataSetChanged();
            }
        }

    }


}
