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

public class Fragment_Thu_LoaiThu extends Fragment {
    private FloatingActionButton fabThemLoaiThu;
    private ListView lvLoaiThu;
    private Cursor cursorLoaiThu;
    private SimpleCursorAdapter adapterLoaiThu;
    private DatabaseManager manager;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu_loaithu, container, false);
        fabThemLoaiThu = view.findViewById(R.id.fabThemLoaiThu);
        lvLoaiThu = view.findViewById(R.id.lvLoaiThu);
        manager = new DatabaseManager(getContext());
        fabThemLoaiThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Loại Thu");
                final View viewDialog = inflater.inflate(R.layout.dialog_loaithu, null);
                builder.setView(viewDialog);
                builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText edTenLoaiThu = viewDialog.findViewById(R.id.edTenLoaiThu);
                        String tenLoaiThu = edTenLoaiThu.getText().toString().trim();
                        if(tenLoaiThu.equalsIgnoreCase("")){
                            Toast.makeText(getContext(), "Vui lòng điền thông tin!", Toast.LENGTH_SHORT).show();
                        }else{
                            manager.insertLoaiThu(tenLoaiThu);
                        }
                        getLoaiThu();
                    }
                });
                builder.show();
            }
        });
        getLoaiThu();
        return view;
    }

    public void getLoaiThu(){
        cursorLoaiThu = manager.getLoaiThu();
        if(cursorLoaiThu != null){
            adapterLoaiThu = new SimpleCursorAdapter(getContext(), R.layout.item_loaithu, cursorLoaiThu,
                    new String[]{"_id", "tenLoaiThu"}, new int[]{R.id.tvSTT, R.id.tvName});
            lvLoaiThu.setAdapter(adapterLoaiThu);
        }
    }
}
