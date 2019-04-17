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
import com.tuna.assignment.database.KhoanChiDAO;
import com.tuna.assignment.model.KhoanChi;

import java.util.ArrayList;
import java.util.List;

public class KhoanChiAdapter extends BaseAdapter implements Filterable {
    List<KhoanChi> arrKhoanChi;
    List<KhoanChi> arrSortKhoanChi;
    private Filter khoanchiFilter;
    public Activity context;
    public LayoutInflater inflater;
    KhoanChiDAO khoanChiDAO;

    public KhoanChiAdapter(Activity context, List<KhoanChi> arrKhoanChi) {
        super();
        this.context = context;
        this.arrKhoanChi = arrKhoanChi;
        this.arrSortKhoanChi = arrKhoanChi;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        khoanChiDAO = new KhoanChiDAO(context);
    }

    @Override
    public int getCount() {
        return arrKhoanChi.size();
    }

    @Override
    public Object getItem(int position) {
        return arrKhoanChi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        TextView txtMaKhoanChi,txtTenKhoanChi;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_khoanchi, null);
            holder.txtMaKhoanChi = (TextView) convertView.findViewById(R.id.tvSTT);
            holder.txtTenKhoanChi = (TextView) convertView.findViewById(R.id.tvName);
//
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        KhoanChi _entry = (KhoanChi) arrKhoanChi.get(position);
        holder.txtMaKhoanChi.setText(_entry.getMaKhoanChi());
        holder.txtTenKhoanChi.setText(_entry.getTenKhoanChi());
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDatasetKhoanChi(List<KhoanChi> items) {
        this.arrKhoanChi = items;
        notifyDataSetChanged();
    }

    public void resetData() {
        arrKhoanChi = arrSortKhoanChi;
    }

    @Override
    public Filter getFilter() {
        if (khoanchiFilter == null)
            khoanchiFilter = new CustomFilter();
        return khoanchiFilter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortKhoanChi;
                results.count = arrSortKhoanChi.size();
            } else {
                List<KhoanChi> lsKhoanChi = new ArrayList<KhoanChi>();
                for (KhoanChi p : arrKhoanChi) {
                    if (p.getMaKhoanChi().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lsKhoanChi.add(p);
                }
                results.values = lsKhoanChi;
                results.count = lsKhoanChi.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                arrKhoanChi = (List<KhoanChi>) results.values;
                notifyDataSetChanged();
            }
        }

    }
}
