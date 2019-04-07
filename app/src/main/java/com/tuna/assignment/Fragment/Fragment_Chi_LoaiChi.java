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

public class Fragment_Chi_LoaiChi extends Fragment {
    private FloatingActionButton fabThemLoaiChi;
    private ListView lvLoaiChi;
    private Cursor cursorLoaiChi;
    private SimpleCursorAdapter adapterLoaiChi;
    private DatabaseManager manager;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chi_loaichi, container, false);
        fabThemLoaiChi = view.findViewById(R.id.fabThemLoaiChi);
        lvLoaiChi = view.findViewById(R.id.lvLoaiChi);
        manager = new DatabaseManager(getContext());
        fabThemLoaiChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Loại Chi");
                final View viewDialog = inflater.inflate(R.layout.dialog_loaichi, null);
                builder.setView(viewDialog);
                builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText edTenLoaiChi = viewDialog.findViewById(R.id.edTenLoaiChi);
                        String tenLoaiChi = edTenLoaiChi.getText().toString().trim();
                        if(tenLoaiChi.equalsIgnoreCase("")){
                            Toast.makeText(getContext(), "Vui lòng điền thông tin!", Toast.LENGTH_SHORT).show();
                        }else{
                            manager.insertLoaiChi(tenLoaiChi);
                        }
                        getLoaiChi();
                    }
                });
                builder.show();
            }
        });
        getLoaiChi();
        return view;
    }

    public void getLoaiChi(){
        cursorLoaiChi = manager.getLoaiChi();
        if(cursorLoaiChi != null){
            adapterLoaiChi = new SimpleCursorAdapter(getContext(), R.layout.item_loaichi, cursorLoaiChi,
                    new String[]{"_id", "tenLoaiChi"}, new int[]{R.id.tvSTT, R.id.tvName});
            lvLoaiChi.setAdapter(adapterLoaiChi);
        }
    }
}
