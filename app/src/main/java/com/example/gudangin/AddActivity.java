/*
package com.example.gudangin.Home;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gudangin.DatabaseHelper;
import com.example.gudangin.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

//import android.support.v7.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    DatabaseHelper helper;
    EditText KodeBarang, NamaBarang, Harga, Jumlah, TglExp;
    Spinner SpJB;
    long id;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout .activity_add);

        helper = new DatabaseHelper(this);

        id = getIntent().getLongExtra(DatabaseHelper.row_id, 0);

        KodeBarang = (EditText)findViewById(R.id.KodeBarang_Add);
        NamaBarang = (EditText)findViewById(R.id.NamaBarang_Add);
        Harga = (EditText)findViewById(R.id.Harga_Add);
        Jumlah = (EditText)findViewById(R.id.Jumlah_Add);
        TglExp = (EditText)findViewById(R.id.TglExp_Add);
        SpJB = (Spinner)findViewById(R.id.spJB_Add);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        TglExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
    }

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                TglExp.setText(dateFormatter.format(newDate.getTime()));
            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_add:
                String kodeBarang = KodeBarang.getText().toString().trim();
                String namaBarang = NamaBarang.getText().toString().trim();
                String jb = SpJB.getSelectedItem().toString().trim();
                String harga = Harga.getText().toString().trim();
                String jumlah = Jumlah.getText().toString().trim();
                String tglExp = TglExp.getText().toString().trim();

                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.row_kodeBarang, kodeBarang);
                values.put(DatabaseHelper.row_namaBarang, namaBarang);
                values.put(DatabaseHelper.row_jb, jb);
                values.put(DatabaseHelper.row_harga, harga);
                values.put(DatabaseHelper.row_jumlah, jumlah);
                values.put(DatabaseHelper.row_tanggalExp, tglExp);

                if (kodeBarang.equals("") || namaBarang.equals("") || harga.equals("") || jumlah.equals("") || tglExp.equals("")){
                    Toast.makeText(AddActivity.this, "Data Barang Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }else{
                    helper.insertData(values);
                    Toast.makeText(AddActivity.this, "Data Barang Telah Tersimpan", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
        return super.onOptionsItemSelected(item);
    }
}
*/
package com.example.gudangin;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

//import android.support.v7.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    DatabaseHelper helper;
    EditText KodeProduk, NamaProduk, Harga, Jumlah, TglMasuk;
    Spinner SpJP;
    long id;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setTitle("Tambah Data Baru");

        helper = new DatabaseHelper(this);

        id = getIntent().getLongExtra(DatabaseHelper.row_id, 0);

        KodeProduk = (EditText)findViewById(R.id.KodeBarang_Add);
        NamaProduk = (EditText)findViewById(R.id.NamaBarang_Add);
        Harga = (EditText)findViewById(R.id.Harga_Add);
        Jumlah = (EditText)findViewById(R.id.Jumlah_Add);
        TglMasuk = (EditText)findViewById(R.id.TglExp_Add);
        SpJP = (Spinner)findViewById(R.id.spJB_Add);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        TglMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
    }

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                TglMasuk.setText(dateFormatter.format(newDate.getTime()));
            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_add:
                String kodeProduk = KodeProduk.getText().toString().trim();
                String namaProduk = NamaProduk.getText().toString().trim();
                String jp = SpJP.getSelectedItem().toString().trim();
                String harga = Harga.getText().toString().trim();
                String jumlah = Jumlah.getText().toString().trim();
                String tglMasuk = TglMasuk.getText().toString().trim();

                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.row_kodeBarang, kodeProduk);
                values.put(DatabaseHelper.row_namaBarang, namaProduk);
                values.put(DatabaseHelper.row_jb, jp);
                values.put(DatabaseHelper.row_harga, harga);
                values.put(DatabaseHelper.row_jumlah, jumlah);
                values.put(DatabaseHelper.row_tanggalExp, tglMasuk);

                if (kodeProduk.equals("") || namaProduk.equals("") || harga.equals("") || jumlah.equals("") || tglMasuk.equals("")){
                    Toast.makeText(AddActivity.this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else{
                    helper.insertData(values);
                    Toast.makeText(AddActivity.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
        return super.onOptionsItemSelected(item);
    }
}
