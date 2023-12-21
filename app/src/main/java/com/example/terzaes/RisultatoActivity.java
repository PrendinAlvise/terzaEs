package com.example.terzaes.;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RisultatoActivity extends AppCompatActivity {

    private TextView txtRisultato;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risultato);
        txtRisultato = findViewById(R.id.txtRisultato);

        com.example.terzaes.DBHelper dbHelper = new com.example.terzaes.DBHelper(this);
        int ultimoRisultato = dbHelper.getUltimoRis();
        txtRisultato.setText("Ultimo risultato: " + ultimoRisultato);
    }

    public void tornaAllaMainActivity(View view) {
        finish();
    }
}
