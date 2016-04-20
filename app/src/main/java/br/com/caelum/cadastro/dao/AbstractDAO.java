package br.com.caelum.cadastro.dao;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class AbstractDAO extends SQLiteOpenHelper {
    private static final String NAME = "CadastroCaelum";
    private static final int VERSION = 2;

    public AbstractDAO(Context context) {
        super(context, NAME, null, VERSION);
    }
}
