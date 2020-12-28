package com.example.gudangin;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.example.gudangin.AddActivity;
import com.example.gudangin.CostumCursorAdapter;
import com.example.gudangin.EditActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainnActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    Button logout;
    ListView listView;
    DatabaseHelper helper;
    LayoutInflater inflater;
    View dialogView;
    TextView kodeBarang, namaBarang, jb, harga, jumlah, tanggalExp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainn);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();
        getSupportActionBar().setTitle("Dashboard");

        //DB Login
        helper = new DatabaseHelper(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainnActivity.this, AddActivity.class));
            }
        });

        //DB barang
        helper = new DatabaseHelper(this);
        listView = (ListView)findViewById(R.id.List_data);
        listView.setOnItemClickListener(this);

        //ke Halaman Login
        Boolean checkSession = helper.checkSession("ada");
        if (checkSession == false) {
            Intent loginIntent = new Intent(MainnActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }
    }
    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void setListView(){
        Cursor cursor = helper.allData();
        CostumCursorAdapter customCursorAdapter = new CostumCursorAdapter(this, cursor, 1);
        listView.setAdapter(customCursorAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long x) {
        TextView getId = (TextView)view.findViewById(R.id.listIDbarang);
        final long id = Long.parseLong(getId.getText().toString());
        final Cursor cur = helper.oneData(id);
        cur.moveToFirst();

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainnActivity.this);
        builder.setTitle("Pilih Opsi");

        //Add a list

        String[] options = {"Lihat Data", "Edit Data", "Hapus Data"};
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        Intent iddata = new Intent(MainnActivity.this, ShowDetailActivity.class);
                        iddata.putExtra(DatabaseHelper.row_id, id);
                        startActivity(iddata);

                        /*final AlertDialog.Builder viewData = new AlertDialog.Builder(MainnActivity.this);
                        inflater = getLayoutInflater();
                        dialogView = inflater.inflate(R.layout.view_data, null);
                        viewData.setView(dialogView);
                        viewData.setTitle("--------------Lihat Data Barang------------");

                        kodeBarang = (TextView)dialogView.findViewById(R.id.KodeBarang);
                        namaBarang = (TextView)dialogView.findViewById(R.id.NamaBarang);
                        jb = (TextView)dialogView.findViewById(R.id.JB);
                        harga = (TextView)dialogView.findViewById(R.id.Harga);
                        jumlah = (TextView)dialogView.findViewById(R.id.Jumlah);
                        tanggalExp = (TextView)dialogView.findViewById(R.id.TanggalExp);

                        kodeBarang.setText("     Kode Barang                    : " + cur.getString(cur.getColumnIndex(DatabaseHelper.row_kodeBarang)));
                        jb.setText("     Jenis Barang                    : " + cur.getString(cur.getColumnIndex(DatabaseHelper.row_jb)));
                        namaBarang.setText("     Nama Barang                  : " + cur.getString(cur.getColumnIndex(DatabaseHelper.row_namaBarang)));
                        harga.setText("     Harga                                : Rp" + cur.getString(cur.getColumnIndex(DatabaseHelper.row_harga)));
                        jumlah.setText("     Jumlah                             : " + cur.getString(cur.getColumnIndex(DatabaseHelper.row_jumlah)));
                        tanggalExp.setText("     Tanggal Kadaluarsa      : " + cur.getString(cur.getColumnIndex(DatabaseHelper.row_tanggalExp)));


                        viewData.setPositiveButton("KEMBALI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        viewData.show();*/
                }
                switch (which){
                    case 1:
                        Intent iddata = new Intent(MainnActivity.this, EditActivity.class);
                        iddata.putExtra(DatabaseHelper.row_id, id);
                        startActivity(iddata);
                }
                switch (which){
                    case 2:
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainnActivity.this);
                        builder1.setMessage("Data Barang Ini Akan Dihapus.");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                helper.deleteData(id);
                                Toast.makeText(MainnActivity.this, "Data Baranng Terhapus", Toast.LENGTH_SHORT).show();
                                setListView();
                            }
                        });
                        builder1.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertDialog = builder1.create();
                        alertDialog.show();
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setListView();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
        int id = Item.getItemId();
         if (id == R.id.nav_account){
            startActivity(new Intent(MainnActivity.this, AccountActivity.class));
        }else if (id == R.id.nav_logout){
             Toast.makeText(MainnActivity.this,"Log Out Success !", Toast.LENGTH_SHORT).show();
             Intent intent = new Intent(MainnActivity.this, LoginActivity.class);
             startActivity(intent);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
