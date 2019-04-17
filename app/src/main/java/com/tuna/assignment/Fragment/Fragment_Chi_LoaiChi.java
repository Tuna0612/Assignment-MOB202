package com.tuna.assignment.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.tuna.assignment.Activity.EditLoaiChiActivity;
import com.tuna.assignment.R;
import com.tuna.assignment.adapter.LoaiChiAdapter;
import com.tuna.assignment.database.LoaiChiDAO;
import com.tuna.assignment.model.LoaiChi;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Chi_LoaiChi extends Fragment {
    private CardView fabThemLoaiChi;
    private ListView lvLoaiChi;
    public static List<LoaiChi> dsLoaiChi = new ArrayList<>();
    private EditText edMaLoaiChi, edTenLoaiChi;
    LoaiChiDAO loaiChiDAO;
    LoaiChiAdapter adapter = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chi_loaichi, container, false);
        fabThemLoaiChi = view.findViewById(R.id.fabThemLoaiChi);
        lvLoaiChi = view.findViewById(R.id.lvLoaiChi);
        loaiChiDAO = new LoaiChiDAO(getContext());
        registerForContextMenu(lvLoaiChi);
        dsLoaiChi = loaiChiDAO.getAllLoaiChi();
        adapter = new LoaiChiAdapter(getActivity(), dsLoaiChi);

        lvLoaiChi.setAdapter(adapter);

        fabThemLoaiChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View viewDialog = inflater.inflate(R.layout.dialog_loaichi, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Loại Chi");
                builder.setView(viewDialog);

                edMaLoaiChi = viewDialog.findViewById(R.id.edMaLoaiChi);
                edTenLoaiChi = viewDialog.findViewById(R.id.edTenLoaiChi);

                builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loaiChiDAO = new LoaiChiDAO(getContext());
                        try {
                            LoaiChi loaiChi = new LoaiChi(edMaLoaiChi.getText().toString(),edTenLoaiChi.getText().toString());
                            if(loaiChiDAO.insertLoaiChi(loaiChi)>0){
                                Toast.makeText(getContext(), getString(R.string.alertsuccessfully), Toast.LENGTH_SHORT).show();
                                dsLoaiChi.clear();
                                dsLoaiChi = loaiChiDAO.getAllLoaiChi();
                                adapter.changeDatasetLoaiChi(loaiChiDAO.getAllLoaiChi());
                                dialog.dismiss();
                            }else {
                                edMaLoaiChi.setError(getString(R.string.error));
                            }
                        }catch (Exception ex){
                            Log.e("Error",ex.toString());
                        }
                    }
                });
                builder.show();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        dsLoaiChi.clear();
        dsLoaiChi = loaiChiDAO.getAllLoaiChi();
        adapter.changeDatasetLoaiChi(loaiChiDAO.getAllLoaiChi());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.menu_lvloaichi, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_loai_chi:
                AdapterView.AdapterContextMenuInfo menuinfo1 = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                int poistion2 = menuinfo1.position;
                Intent intent = new Intent(getContext(), EditLoaiChiActivity.class);
                Bundle b = new Bundle();
                b.putString("ID", dsLoaiChi.get(poistion2).getMaLoaiChi());
                b.putString("NAME", dsLoaiChi.get(poistion2).getTenLoaiChi());

                intent.putExtras(b);
                startActivity(intent);
                break;

            case R.id.delete_loai_chi:
                AdapterView.AdapterContextMenuInfo menuinfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                int poistion = menuinfo.position;
                loaiChiDAO.deleteLoaiChiByID(dsLoaiChi.get(poistion).getMaLoaiChi());
                dsLoaiChi.remove(poistion);
                dsLoaiChi.clear();
                dsLoaiChi = loaiChiDAO.getAllLoaiChi();
                adapter.changeDatasetLoaiChi(loaiChiDAO.getAllLoaiChi());
                break;
        }
        return super.onContextItemSelected(item);
    }
}
