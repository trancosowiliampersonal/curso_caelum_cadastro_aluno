package br.com.caelum.cadastro.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class AlunoDAO extends AbstractDAO{

    private static final String TABLE = "ALUNO";

    public AlunoDAO(Context context){
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s =  " CREATE TABLE " + TABLE + "(" +
                " id INTEGER PRIMARY KEY, " +
                " nome TEXT NOT NULL, " +
                " telefone TEXT, " +
                " endereco TEXT, " +
                " site TEXT, " +
                " nota REAL)";

        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
