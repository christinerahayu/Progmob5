package com.example.gudangin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String database_name = "db_gudangIn";
    public static final String table_name = "tabel_barang";
    public static final String row_id = "_id";
    public static final String row_kodeBarang = "KodeBarang";
    public static final String row_namaBarang = "NamaBarang";
    public static final String row_jb = "JB";
    public static final String row_harga = "Harga";
    public static final String row_jumlah = "Jumlah";
    public static final String row_tanggalExp = "TanggalExp";

    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, "loginSQLite.db", null, 1);
        db = getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE session(id integer PRIMARY KEY, login text)");
        db.execSQL("CREATE TABLE pengguna(id integer PRIMARY KEY AUTOINCREMENT, username text, password text, namalengkap text, alamat text, telp text)");

        //db.execSQL("CREATE TABLE user(id integer PRIMARY KEY AUTOINCREMENT, username text, nama text, jkelamin text, tglahir text, tlp text, alamat text, email text, password text)");
        db.execSQL("INSERT INTO session(id, login) VALUES(1, 'kosong')");
        String query = "CREATE TABLE " + table_name + "(" + row_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_kodeBarang + " TEXT, " + row_namaBarang + " TEXT, " + row_jb + " TEXT, "
                + row_harga + " TEXT, " + row_jumlah + " TEXT, " + row_tanggalExp + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        db.execSQL("DROP TABLE IF EXISTS session");
        db.execSQL("DROP TABLE IF EXISTS pengguna");
        onCreate(db);
    }
    //Get All SQLite Data
    public Cursor allData(){
        Cursor cur = db.rawQuery("SELECT * FROM " + table_name, null);
        return cur;
    }

    //Get 1 Data By ID
    public Cursor oneData(Long id){
        Cursor cur = db.rawQuery("SELECT * FROM " + table_name + " WHERE " + row_id + "=" + id, null);
        return cur;
    }

    //Insert Data to Database
    public void insertData(ContentValues values){
        db.insert(table_name, null, values);
    }

    //Update Data
    public void updateData(ContentValues values, long id){
        db.update(table_name, values, row_id + "=" + id, null);
    }

    //Delete Data
    public void deleteData(long id){
        db.delete(table_name, row_id + "=" + id, null);
    }
    //check session
    public Boolean checkSession(String sessionValues) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM session WHERE login = ?", new String[]{sessionValues});
        if (cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    //upgrade session
    public Boolean upgradeSession(String sessionValues, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login", sessionValues);
        long update = db.update("session", contentValues, "id="+id, null);
        if (update == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    //check login
    public Boolean checkLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM pengguna WHERE username = ? AND password = ?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }
    //insert user
    public Boolean insertPengguna(String username, String password, String namalengkap, String alamat, String telp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("namalengkap", namalengkap);
        contentValues.put("alamat", alamat);
        contentValues.put("telp", telp);
        long insert = db.insert("pengguna", null, contentValues);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

}
