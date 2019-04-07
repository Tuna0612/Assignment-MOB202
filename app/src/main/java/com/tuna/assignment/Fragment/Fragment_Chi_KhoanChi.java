package com.tuna.assignment.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.tuna.assignment.R;
import com.tuna.assignment.database.DatabaseManager;

public class Fragment_Chi_KhoanChi extends Fragment {
    private FloatingActionButton fabThemKhoanChi;
    private ListView lvKhoanChi;
    private Cursor cursorKhoanChi;
    private SimpleCursorAdapter adapterKhoanChi;
    private DatabaseManager manager;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chi_khoanchi, container, false);
        fabThemKhoanChi = view.findViewById(R.id.fabThemKhoanChi);
        lvKhoanChi = view.findViewById(R.id.lvKhoanChi);
        manager = new DatabaseManager(getContext());
        fabThemKhoanChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Khoản Chi");
                final View viewDialog = inflater.inflate(R.layout.dialog_khoanchi, null);
                builder.setView(viewDialog);
                builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText edTenKhoanChi = viewDialog.findViewById(R.id.edTenKhoanChi);
                        String tenKhoanChi = edTenKhoanChi.getText().toString().trim();
                        if(tenKhoanChi.equalsIgnoreCase("")){
                            Toast.makeText(getContext(), "Vui lòng điền thông tin!", Toast.LENGTH_SHORT).show();
                        }else{
                            manager.insertKhoanChi(tenKhoanChi);
                        }
                        getKhoanChi();
                    }
                });
                builder.show();
            }
        });
        getKhoanChi();
        return view;
    }

    public void getKhoanChi(){
        cursorKhoanChi = manager.getKhoanChi();
        if(cursorKhoanChi != null){
            adapterKhoanChi = new SimpleCursorAdapter(getContext(), R.layout.item_khoanchi, cursorKhoanChi,
                    new String[]{"_id", "tenKhoanChi"}, new int[]{R.id.tvSTT, R.id.tvName});
            lvKhoanChi.setAdapter(adapterKhoanChi);
        }
    }
}
