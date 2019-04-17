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

import com.tuna.assignment.Activity.EditKhoanThuActivity;
import com.tuna.assignment.Activity.EditLoaiThuActivity;
import com.tuna.assignment.R;
import com.tuna.assignment.adapter.KhoanThuAdapter;
import com.tuna.assignment.adapter.LoaiThuAdapter;
import com.tuna.assignment.database.DatabaseManager;
import com.tuna.assignment.database.KhoanThuDAO;
import com.tuna.assignment.database.LoaiThuDAO;
import com.tuna.assignment.model.KhoanThu;
import com.tuna.assignment.model.LoaiThu;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Thu_LoaiThu extends Fragment {
    private CardView fabThemLoaiThu;
    private ListView lvLoaiThu;
    public static List<LoaiThu> dsLoaiThu = new ArrayList<>();
    private EditText edMaLoaiThu, edTenLoaiThu;
    LoaiThuDAO loaiThuDAO;
    LoaiThuAdapter adapter = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu_loaithu, container, false);
        fabThemLoaiThu = view.findViewById(R.id.fabThemLoaiThu);
        lvLoaiThu = view.findViewById(R.id.lvLoaiThu);
        loaiThuDAO = new LoaiThuDAO(getContext());
        registerForContextMenu(lvLoaiThu);
        dsLoaiThu = loaiThuDAO.getAllLoaiThu();
        adapter = new LoaiThuAdapter(getActivity(), dsLoaiThu);

        lvLoaiThu.setAdapter(adapter);
        fabThemLoaiThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View viewDialog = inflater.inflate(R.layout.dialog_loaithu, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Loại Thu");
                builder.setView(viewDialog);

                edMaLoaiThu = viewDialog.findViewById(R.id.edMaLoaiThu);
                edTenLoaiThu = viewDialog.findViewById(R.id.edTenLoaiThu);
                builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loaiThuDAO = new LoaiThuDAO(getContext());
                        try {
                            LoaiThu loaiThu = new LoaiThu(edMaLoaiThu.getText().toString(),edTenLoaiThu.getText().toString());
                            if(loaiThuDAO.insertLoaiThu(loaiThu)>0){
                                Toast.makeText(getContext(), getString(R.string.alertsuccessfully), Toast.LENGTH_SHORT).show();
                                dsLoaiThu.clear();
                                dsLoaiThu = loaiThuDAO.getAllLoaiThu();
                                adapter.changeDatasetLoaiThu(loaiThuDAO.getAllLoaiThu());
                                dialog.dismiss();
                            }else {
                                edMaLoaiThu.setError(getString(R.string.error));
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
        dsLoaiThu.clear();
        dsLoaiThu = loaiThuDAO.getAllLoaiThu();
        adapter.changeDatasetLoaiThu(loaiThuDAO.getAllLoaiThu());
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
                Intent intent = new Intent(getContext(), EditLoaiThuActivity.class);
                Bundle b = new Bundle();
                b.putString("ID", dsLoaiThu.get(position_khoan_chi).getMaLoaiThu());
                b.putString("NAME", dsLoaiThu.get(position_khoan_chi).getTenLoaiThu());

                intent.putExtras(b);
                startActivity(intent);
                break;

            case R.id.delete_khoan_thu:
                AdapterView.AdapterContextMenuInfo menuinfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                int poistion = menuinfo.position;
                loaiThuDAO.deleteLoaiThuByID(dsLoaiThu.get(poistion).getMaLoaiThu());
                dsLoaiThu.remove(poistion);
                dsLoaiThu.clear();
                dsLoaiThu = loaiThuDAO.getAllLoaiThu();
                adapter.changeDatasetLoaiThu(loaiThuDAO.getAllLoaiThu());
                break;
        }
        return super.onContextItemSelected(item);
    }
}
