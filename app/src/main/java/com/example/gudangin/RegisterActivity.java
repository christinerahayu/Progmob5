package com.example.gudangin;

import android.app.DatePickerDialog;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    Button login, register;
    EditText username, nama, tglahir, tlp, email,alamat, password, passwordConf;
    Spinner jkelamin;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);

        username = (EditText)findViewById(R.id.username);
        nama = (EditText)findViewById(R.id.namaLengkap);
        jkelamin = (Spinner)findViewById(R.id.spJK_Add);
        tglahir = (EditText)findViewById(R.id.txTglLahir_Add);
        tlp = (EditText)findViewById(R.id.tlp);
        alamat = (EditText)findViewById(R.id.alamat);
        email= (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.edtText_password);
        passwordConf = (EditText)findViewById(R.id.edtText_passwordConfRegist);
        login = (Button)findViewById(R.id.btn_loginRegist);
        register = (Button)findViewById(R.id.btn_registerRegist);

        dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        tglahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        //login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });

        //register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername = username.getText().toString();
                String strNama = nama.getText().toString();
                String strJKelamin = jkelamin.getSelectedItem().toString();
                String strtglahir = tglahir.getText().toString();
                String strTlp= tlp.getText().toString();
                String strAlamat= alamat.getText().toString();
                String strEmail= email.getText().toString();
                String strPassword = password.getText().toString();
                String strPasswordConf = passwordConf.getText().toString();
                if (strPassword.equals(strPasswordConf)) {
                    Boolean daftar = db.insertUser(strUsername, strNama, strJKelamin, strtglahir, strTlp, strAlamat, strEmail, strPassword);
                    if (daftar == true) {
                        Toast.makeText(getApplicationContext(), "Daftar Berhasil", Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(loginIntent);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Daftar Gagal", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Password Tidak Cocok", Toast.LENGTH_SHORT).show();
                }
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
                tglahir.setText(dateFormat.format(newDate.getTime()));
            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
