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

public class Fragment_Thu_KhoanThu extends Fragment {
    private FloatingActionButton fabThemKhoanThu;
    private ListView lvKhoanThu;
    private Cursor cursorKhoanThu;
    private SimpleCursorAdapter adapterKhoanThu;
    private DatabaseManager manager;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_thu_khoanthu, container, false);
        fabThemKhoanThu = view.findViewById(R.id.fabThemKhoanThu);
        lvKhoanThu = view.findViewById(R.id.lvKhoanThu);
        manager = new DatabaseManager(getContext());
        fabThemKhoanThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Khoản Thu");
                final View viewDialog = inflater.inflate(R.layout.dialog_khoanthu, null);
                builder.setView(viewDialog);
                builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText edTenKhoanThu = viewDialog.findViewById(R.id.edTenKhoanThu);
                        String tenKhoanThu = edTenKhoanThu.getText().toString().trim();
                        if(tenKhoanThu.equalsIgnoreCase("")){
                            Toast.makeText(getContext(), "Vui lòng điền thông tin!", Toast.LENGTH_SHORT).show();
                        }else{
                            manager.insertKhoanThu(tenKhoanThu);
                        }
                        getKhoanThu();
                    }
                });
                builder.show();
            }
        });
        getKhoanThu();
        return view;
    }

    public void getKhoanThu(){
        cursorKhoanThu = manager.getKhoanThu();
        if(cursorKhoanThu != null){
            adapterKhoanThu = new SimpleCursorAdapter(getContext(), R.layout.item_khoanthu, cursorKhoanThu,
                    new String[]{"_id", "tenKhoanThu"}, new int[]{R.id.tvSTT, R.id.tvName});
            lvKhoanThu.setAdapter(adapterKhoanThu);
        }
    }
}
