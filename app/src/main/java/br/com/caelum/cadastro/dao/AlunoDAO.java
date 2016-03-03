package br.com.caelum.cadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.caelum.cadastro.modelo.Aluno;

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

    public void inserir(Aluno aluno){
        ContentValues cv = alunoToContentValues(aluno);
        aluno.setId(getWritableDatabase().insert(TABLE, null, cv));
    }

    private ContentValues alunoToContentValues(Aluno aluno){
        ContentValues cv = new ContentValues();

        cv.put("nome", aluno.getNome());
        cv.put("telefone", aluno.getTelefone());
        cv.put("endereco", aluno.getEndereco());
        cv.put("site", aluno.getSite());
        cv.put("nota", aluno.getNota());

        return cv;
    }
}
