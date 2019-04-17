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

import com.tuna.assignment.Activity.EditKhoanChiActivity;
import com.tuna.assignment.R;
import com.tuna.assignment.adapter.KhoanChiAdapter;
import com.tuna.assignment.database.KhoanChiDAO;
import com.tuna.assignment.model.KhoanChi;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Chi_KhoanChi extends Fragment {
    CardView fabThemKhoanChi;
    private ListView lvKhoanChi;
    public static List<KhoanChi> dsKhoanChi = new ArrayList<>();
    private EditText edMaKhoanChi, edTenKhoanChi;
    KhoanChiDAO khoanChiDAO;
    KhoanChiAdapter adapter = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chi_khoanchi, container, false);
        fabThemKhoanChi = view.findViewById(R.id.fabThemKhoanChi);
        lvKhoanChi = view.findViewById(R.id.lvKhoanChi);
        khoanChiDAO = new KhoanChiDAO(getContext());
        registerForContextMenu(lvKhoanChi);
        dsKhoanChi = khoanChiDAO.getAllKhoanChi();
        adapter = new KhoanChiAdapter(getActivity(), dsKhoanChi);

        lvKhoanChi.setAdapter(adapter);
        fabThemKhoanChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                final View viewDialog = inflater.inflate(R.layout.dialog_khoanchi, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Khoản Chi");
                builder.setView(viewDialog);

                edMaKhoanChi = viewDialog.findViewById(R.id.edMaKhoanChi);
                edTenKhoanChi = viewDialog.findViewById(R.id.edTenKhoanChi);
                builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        khoanChiDAO = new KhoanChiDAO(getContext());
                        try {
                            KhoanChi khoanChi = new KhoanChi(edMaKhoanChi.getText().toString(),edTenKhoanChi.getText().toString());
                            if(khoanChiDAO.insertKhoanChi(khoanChi)>0){
                                Toast.makeText(getContext(), getString(R.string.alertsuccessfully), Toast.LENGTH_SHORT).show();
                                dsKhoanChi.clear();
                                dsKhoanChi = khoanChiDAO.getAllKhoanChi();
                                adapter.changeDatasetKhoanChi(khoanChiDAO.getAllKhoanChi());
                                dialog.dismiss();
                            }else {
                                edMaKhoanChi.setError(getString(R.string.error));
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
        dsKhoanChi.clear();
        dsKhoanChi = khoanChiDAO.getAllKhoanChi();
        adapter.changeDatasetKhoanChi(khoanChiDAO.getAllKhoanChi());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.menu_lvkhoanchi, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_khoan_chi:
                AdapterView.AdapterContextMenuInfo menuinfo1 = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                int position_khoan_chi = menuinfo1.position;
                Intent intent = new Intent(getContext(), EditKhoanChiActivity.class);
                Bundle b_khoan_chi = new Bundle();
                b_khoan_chi.putString("ID_khoan_chi", dsKhoanChi.get(position_khoan_chi).getMaKhoanChi());
                b_khoan_chi.putString("NAME_khoan_chi", dsKhoanChi.get(position_khoan_chi).getTenKhoanChi());

                intent.putExtras(b_khoan_chi);
                startActivity(intent);
                break;

            case R.id.delete_khoan_chi:
                AdapterView.AdapterContextMenuInfo menuinfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                int poistion = menuinfo.position;
                khoanChiDAO.deleteKhoanChiByID(dsKhoanChi.get(poistion).getMaKhoanChi());
                dsKhoanChi.remove(poistion);
                dsKhoanChi.clear();
                dsKhoanChi = khoanChiDAO.getAllKhoanChi();
                adapter.changeDatasetKhoanChi(khoanChiDAO.getAllKhoanChi());
                break;
        }
        return super.onContextItemSelected(item);
    }
}
