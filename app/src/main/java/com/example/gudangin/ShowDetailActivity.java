package com.example.gudangin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class ShowDetailActivity extends AppCompatActivity {
    DatabaseHelper helper;
    TextView KodeBarang, NamaBarang, Harga, Jumlah, TglExp, JB;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        getSupportActionBar().setTitle("Detail Data");

        helper = new DatabaseHelper(this);

        id = getIntent().getLongExtra(DatabaseHelper.row_id, 0);

        KodeBarang = (TextView) findViewById(R.id.showKode);
        NamaBarang = (TextView) findViewById(R.id.showNama);
        Harga = (TextView) findViewById(R.id.showHarga);
        Jumlah = (TextView) findViewById(R.id.showJumlah);
        TglExp = (TextView) findViewById(R.id.showTgl);
        JB = (TextView)findViewById(R.id.showJB);
        getData();
    }
    private void getData(){
        Cursor cursor = helper.oneData(id);
        if(cursor.moveToFirst()){
            String kodeBarang = cursor.getString(cursor.getColumnIndex(DatabaseHelper.row_kodeBarang));
            String namaBarang = cursor.getString(cursor.getColumnIndex(DatabaseHelper.row_namaBarang));
            String jb = cursor.getString(cursor.getColumnIndex(DatabaseHelper.row_jb));
            String harga = cursor.getString(cursor.getColumnIndex(DatabaseHelper.row_harga));
            String jumlah = cursor.getString(cursor.getColumnIndex(DatabaseHelper.row_jumlah));
            String tglExp = cursor.getString(cursor.getColumnIndex(DatabaseHelper.row_tanggalExp));

            KodeBarang.setText(kodeBarang);
            NamaBarang.setText(namaBarang);
            JB.setText(jb);
            Harga.setText(harga);
            Jumlah.setText(jumlah);
            TglExp.setText(tglExp);
        }
    }

}