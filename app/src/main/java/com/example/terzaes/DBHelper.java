package com.example.terzaes;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CalcolatriceDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Operazioni";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NUMERO1 = "numero1";
    private static final String COLUMN_NUMERO2 = "numero2";
    private static final String COLUMN_OPERAZIONE = "operazione";
    private static final String COLUMN_RISULTATO = "risultato";

    //costruttore
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creo la tabella nel database
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NUMERO1 + " INTEGER,"
                + COLUMN_NUMERO2 + " INTEGER,"
                + COLUMN_OPERAZIONE + " TEXT,"
                + COLUMN_RISULTATO + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    //elimino la tabella per poi farne una nuova
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    
    @SuppressLint("Range")
    public int getUltimoRisultato() {
    SQLiteDatabase db = getReadableDatabase();
    int ultimoRisultato = 0;

    String query = "SELECT " + COLUMN_RISULTATO +
                   " FROM " + TABLE_NAME +
                   " ORDER BY " + COLUMN_ID + " DESC LIMIT 1";

    Cursor cursor = db.rawQuery(query, null);

    if (cursor.moveToFirst()) {
        ultimoRisultato = cursor.getInt(cursor.getColumnIndex(COLUMN_RISULTATO));
    }

    cursor.close();
    db.close();

    return ultimoRisultato;
}

    
    public long salvaOper(int n1, int n2, String oper, int ris) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NUMERO1, n1);
        values.put(COLUMN_NUMERO2, n2);
        values.put(COLUMN_OPERAZIONE, oper);
        values.put(COLUMN_RISULTATO, ris);
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }
    
    
}
