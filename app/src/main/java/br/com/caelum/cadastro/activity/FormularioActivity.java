package br.com.caelum.cadastro.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.helper.FormularioHelper;
import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by Wiliam on 03/03/2016.
 */
public class FormularioActivity extends ActionBarActivity {

    private FormularioHelper helper;
    public static final String ALUNO_SELECIONADO = "alunoSelecionado";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        Intent intent = this.getIntent();
        Aluno alunoEditar = (Aluno)intent.getSerializableExtra(FormularioActivity.ALUNO_SELECIONADO);

        if(alunoEditar != null){
            helper.colocarNoFormulario(alunoEditar);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                if(helper.temNome()) {
                    Aluno aluno = helper.pegaAlunoFormulario();

                    AlunoDAO alunoDAO = new AlunoDAO(this);

                    if(aluno.getId() == null) {
                        alunoDAO.inserir(aluno);
                    }else {
                        alunoDAO.alterar(aluno);
                    }
                    alunoDAO.close();
                    finish();
                }else{
                    helper.mostraErro();
                }
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
