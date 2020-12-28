package com.example.gudangin;

import android.app.DatePickerDialog;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    Button login, register;
    EditText username_tv, password_tv, passwordConf_tv, namalengkap_tv, tlp_tv, alamat_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);

        username_tv = (EditText)findViewById(R.id.username);
        namalengkap_tv = (EditText)findViewById(R.id.namaLengkap);
        tlp_tv = (EditText)findViewById(R.id.tlp);
        alamat_tv = (EditText)findViewById(R.id.alamat);
        password_tv = (EditText)findViewById(R.id.edtText_passwordRegist);
        passwordConf_tv = (EditText)findViewById(R.id.edtText_passwordConfRegist);
        login = (Button)findViewById(R.id.btn_loginRegist);
        register = (Button)findViewById(R.id.btn_registerRegist);

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
                String username = username_tv.getText().toString();
                String password = password_tv.getText().toString();
                String namalengkap = namalengkap_tv.getText().toString();
                String alamat = alamat_tv.getText().toString();
                String telp = tlp_tv.getText().toString();
                String PasswordConf = passwordConf_tv.getText().toString();
                if (password.equals(PasswordConf)) {
                    Boolean data = username.equals("") || password.equals("") || namalengkap.equals("") || alamat.equals("") || telp.equals("");
                    Boolean daftar = db.insertPengguna(username, password, namalengkap, alamat, telp);
                    if (data == true ) {
                        Toast.makeText(getApplicationContext(), "Daftar Gagal", Toast.LENGTH_SHORT).show();
                    }
                    else if (data == false||daftar == true ) {
                        Toast.makeText(getApplicationContext(), "Daftar Berhasil", Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(loginIntent);
                        finish();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Password Tidak Cocok", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
