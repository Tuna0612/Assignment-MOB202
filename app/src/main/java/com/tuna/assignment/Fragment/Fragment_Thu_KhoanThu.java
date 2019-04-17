package com.tuna.assignment.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.tuna.assignment.Activity.EditKhoanChiActivity;
import com.tuna.assignment.Activity.EditKhoanThuActivity;
import com.tuna.assignment.R;
import com.tuna.assignment.adapter.KhoanChiAdapter;
import com.tuna.assignment.adapter.KhoanThuAdapter;
import com.tuna.assignment.database.DatabaseManager;
import com.tuna.assignment.database.KhoanChiDAO;
import com.tuna.assignment.database.KhoanThuDAO;
import com.tuna.assignment.model.KhoanChi;
import com.tuna.assignment.model.KhoanThu;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Thu_KhoanThu extends Fragment {
    private CardView fabThemKhoanThu;
    private ListView lvKhoanThu;
    public static List<KhoanThu> dsKhoanThu = new ArrayList<>();
    private EditText edMaKhoanThu, edTenKhoanThu;
    KhoanThuDAO khoanThuDAO;
    KhoanThuAdapter adapter = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_thu_khoanthu, container, false);
        fabThemKhoanThu = view.findViewById(R.id.fabThemKhoanThu);
        lvKhoanThu = view.findViewById(R.id.lvKhoanThu);
        khoanThuDAO = new KhoanThuDAO(getContext());
        registerForContextMenu(lvKhoanThu);
        dsKhoanThu = khoanThuDAO.getAllKhoanThu();
        adapter = new KhoanThuAdapter(getActivity(), dsKhoanThu);

        lvKhoanThu.setAdapter(adapter);
        fabThemKhoanThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Khoản Thu");
                final View viewDialog = inflater.inflate(R.layout.dialog_khoanthu, null);
                builder.setView(viewDialog);

                edMaKhoanThu = viewDialog.findViewById(R.id.edMaKhoanThu);
                edTenKhoanThu = viewDialog.findViewById(R.id.edTenKhoanThu);

                builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        khoanThuDAO = new KhoanThuDAO(getContext());
                        try {
                            KhoanThu khoanThu = new KhoanThu(edMaKhoanThu.getText().toString(),edTenKhoanThu.getText().toString());
                            if(khoanThuDAO.insertKhoanThu(khoanThu)>0){
                                Toast.makeText(getContext(), getString(R.string.alertsuccessfully), Toast.LENGTH_SHORT).show();
                                dsKhoanThu.clear();
                                dsKhoanThu = khoanThuDAO.getAllKhoanThu();
                                adapter.changeDatasetKhoanThu(khoanThuDAO.getAllKhoanThu());
                                dialog.dismiss();
                            }else {
                                edMaKhoanThu.setError(getString(R.string.error));
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
        dsKhoanThu.clear();
        dsKhoanThu = khoanThuDAO.getAllKhoanThu();
        adapter.changeDatasetKhoanThu(khoanThuDAO.getAllKhoanThu());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.menu_lvkhoanthu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_khoan_thu:
                AdapterView.AdapterContextMenuInfo menuinfo1 = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                int position_khoan_chi = menuinfo1.position;
                Intent intent = new Intent(getContext(), EditKhoanThuActivity.class);
                Bundle b = new Bundle();
                b.putString("ID", dsKhoanThu.get(position_khoan_chi).getMaKhoanThu());
                b.putString("NAME", dsKhoanThu.get(position_khoan_chi).getTenKhoanThu());

                intent.putExtras(b);
                startActivity(intent);
                break;

            case R.id.delete_khoan_thu:
                AdapterView.AdapterContextMenuInfo menuinfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                int poistion = menuinfo.position;
                khoanThuDAO.deleteKhoanThuByID(dsKhoanThu.get(poistion).getMaKhoanThu());
                dsKhoanThu.remove(poistion);
                dsKhoanThu.clear();
                dsKhoanThu = khoanThuDAO.getAllKhoanThu();
                adapter.changeDatasetKhoanThu(khoanThuDAO.getAllKhoanThu());
                break;
        }
        return super.onContextItemSelected(item);
    }

}
