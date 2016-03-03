package br.com.caelum.cadastro.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.caelum.cadastro.modelo.Aluno;

public class AlunoDAO extends AbstractDAO{

    public AlunoDAO(Context context){
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
