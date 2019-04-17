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
import com.tuna.assignment.database.KhoanThuDAO;
import com.tuna.assignment.model.KhoanChi;
import com.tuna.assignment.model.KhoanThu;

import java.util.ArrayList;
import java.util.List;

public class KhoanThuAdapter extends BaseAdapter implements Filterable {
    List<KhoanThu> arrKhoanThu;
    List<KhoanThu> arrSortKhoanThu;
    private Filter khoanThuFilter;
    public Activity context;
    public LayoutInflater inflater;
    KhoanThuDAO khoanThuDAO;


    public KhoanThuAdapter(Activity context, List<KhoanThu> arrKhoanThu) {
        super();
        this.context = context;
        this.arrKhoanThu = arrKhoanThu;
        this.arrSortKhoanThu = arrKhoanThu;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        khoanThuDAO = new KhoanThuDAO(context);
    }

    @Override
    public int getCount() {
        return arrKhoanThu.size();
    }

    @Override
    public Object getItem(int position) {
        return arrKhoanThu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        TextView txtMaKhoanThu,txtTenKhoanThu;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_khoanthu, null);
            holder.txtMaKhoanThu = (TextView) convertView.findViewById(R.id.tvSTT);
            holder.txtTenKhoanThu = (TextView) convertView.findViewById(R.id.tvName);
//
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        KhoanThu _entry = (KhoanThu) arrKhoanThu.get(position);
        holder.txtMaKhoanThu.setText(_entry.getMaKhoanThu());
        holder.txtTenKhoanThu.setText(_entry.getTenKhoanThu());
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDatasetKhoanThu(List<KhoanThu> items) {
        this.arrKhoanThu = items;
        notifyDataSetChanged();
    }

    public void resetData() {
        arrKhoanThu = arrSortKhoanThu;
    }

    @Override
    public Filter getFilter() {
        if (khoanThuFilter == null)
            khoanThuFilter = new CustomFilter();
        return khoanThuFilter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortKhoanThu;
                results.count = arrSortKhoanThu.size();
            } else {
                List<KhoanThu> lsKhoanThu = new ArrayList<KhoanThu>();
                for (KhoanThu p : arrKhoanThu) {
                    if (p.getMaKhoanThu().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lsKhoanThu.add(p);
                }
                results.values = lsKhoanThu;
                results.count = lsKhoanThu.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                arrKhoanThu = (List<KhoanThu>) results.values;
                notifyDataSetChanged();
            }
        }

    }


}
