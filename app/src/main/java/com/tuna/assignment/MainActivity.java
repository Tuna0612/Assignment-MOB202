package com.tuna.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.tuna.assignment.Fragment.Fragment_Chi;
import com.tuna.assignment.Fragment.Fragment_Thu;
import com.tuna.assignment.Fragment.ReportFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolBar;
    private DrawerLayout drawerLayout;
    private NavigationView nvMain;
    private Fragment_Thu fragment_thu;
    private Fragment_Chi fragment_chi;
    private ReportFragment fragment_thongKe;

     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment_thongKe = new ReportFragment();
         fragment_thu = new Fragment_Thu();
         fragment_chi = new Fragment_Chi();
         toolBar = findViewById(R.id.toolBar);
         setSupportActionBar(toolBar);
//         toolBar.setTitleMarginStart(200);
//         toolBar.setTitle("Thống Kê");
//         toolBar.getTitle();
//         toolBar.setLogo(R.drawable.thongke_icon);
         drawerLayout = findViewById(R.id.drawer_layout);
         nvMain = findViewById(R.id.nav_view);
         toolBar.setNavigationIcon(R.drawable.navigation_icon);
         toolBar.setNavigationOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 drawerLayout.openDrawer(Gravity.START);
             }
         });

         hienThiManHinhThu();
         nvMain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 switch (item.getItemId()){
                     case R.id.nav_in:
                         toolBar.setTitle("Quản lý khoản thu");
                         hienThiManHinhThu();
                         break;
                     case R.id.nav_out:
                         toolBar.setTitle("Quản lý khoản chi");
                         hienThiManHinhChi();
                         break;
                     case R.id.nav_report:
                         toolBar.setTitle("Thống Kê");
                         hienThiManHinhThongKe();
                         break;
                     case R.id.nav_introduce:
                         Toast.makeText(MainActivity.this, "Mời bạn quay lại sau", Toast.LENGTH_SHORT).show();
                         break;
                     case R.id.nav_exit:
                         finish();
                         break;
                 }
                 drawerLayout.closeDrawer(Gravity.START);
                 return false;
             }
         });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void hienThiManHinhThongKe(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(fragment_thu.isAdded()){
            ft.hide(fragment_thu);
        }
        if(fragment_chi.isAdded()){
            ft.hide(fragment_chi);
        }
        if(fragment_thongKe.isAdded()){
            ft.show(fragment_thongKe);
        }else{
            ft.add(R.id.framLayout, fragment_thongKe);
        }
        ft.commit();
    }

    public void hienThiManHinhThu(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(fragment_chi.isAdded()){
            ft.hide(fragment_chi);
        }
        if(fragment_thongKe.isAdded()){
            ft.hide(fragment_thongKe);
        }
        if(fragment_thu.isAdded()){
            ft.show(fragment_thu);
        }else{
            ft.add(R.id.framLayout, fragment_thu);
        }
        ft.commit();
    }

    public void hienThiManHinhChi(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(fragment_thongKe.isAdded()){
            ft.hide(fragment_thongKe);
        }
        if(fragment_thu.isAdded()){
            ft.hide(fragment_thu);
        }
        if(fragment_chi.isAdded()){
            ft.show(fragment_chi);
        }else{
            ft.add(R.id.framLayout, fragment_chi);
        }
        ft.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
