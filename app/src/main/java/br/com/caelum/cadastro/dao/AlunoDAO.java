package br.com.caelum.cadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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

    public List<Aluno> getLista(){
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = null;
        List<Aluno> alunos = null;

        try{
            c = db.rawQuery("SELECT * FROM " + TABLE + ";", null);
            alunos = cursorToAlunos(c);
        }catch (Exception e){
            Log.e("Exception", "getLista");
        }finally {
            if(c != null){
                c.close();
            }
        }

        return alunos;
    }

    public void deleta(Aluno aluno){
        String[] args = {aluno.getId() + ""};
        getWritableDatabase().delete(TABLE, "id = ?", args);
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

    private List<Aluno> cursorToAlunos(Cursor c){
        List<Aluno> alunos = new ArrayList<Aluno>();

        Aluno aluno;
        while(c.moveToNext()){
            aluno = new Aluno();

            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setNota(c.getFloat(c.getColumnIndex("nota")));


            alunos.add(aluno);
        }

        return alunos;
    }
}
