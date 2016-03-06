package br.com.caelum.cadastro.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;

public class ListaAlunosActivity extends ActionBarActivity {

    private ListView listaAlunos;
    private List<Aluno> alunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        this.listaAlunos = (ListView)findViewById(R.id.lista_alunos);

        registerForContextMenu(listaAlunos);

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoSelecionado = (Aluno)listaAlunos.getAdapter().getItem(position);
                Intent edicao = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                edicao.putExtra(FormularioActivity.ALUNO_SELECIONADO, alunoSelecionado);
                startActivity(edicao);
            }
        });

        Button botaoAdicionar = (Button)findViewById(R.id.lista_alunos_floating_button);
        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        final Aluno alunoSelecionado = (Aluno) listaAlunos.getAdapter().getItem(info.position);

        MenuItem menuLigar = menu.add("Ligar");

        MenuItem menuSms = menu.add("Enviar SMS");

        MenuItem menuMapa = menu.add("Achar no Mapa");

        MenuItem menuNavegar = menu.add("Navegar no site");

        MenuItem menuDeletar = menu.add("Deletar");

        menuDeletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                new AlertDialog.Builder(ListaAlunosActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Deletar")
                        .setMessage("Deseja mesmo deletar?")
                        .setPositiveButton("Quero", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                                dao.deleta(alunoSelecionado);
                                dao.close();
                                carregaLista();
                            }
                        }).setNegativeButton("Não", null).show();
                return false;
            }
        });
    }

    private void carregaLista(){
        AlunoDAO alunoDAO = new AlunoDAO(this);
        alunos = alunoDAO.getLista();
        alunoDAO.close();

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        listaAlunos.setAdapter(adapter);
    }

}
