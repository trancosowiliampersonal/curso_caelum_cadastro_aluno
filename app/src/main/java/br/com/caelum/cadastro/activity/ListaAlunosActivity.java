package br.com.caelum.cadastro.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
        Intent intentLigar = new Intent(Intent.ACTION_CALL);
        intentLigar.setData(Uri.parse("tel:" + alunoSelecionado.getTelefone()));
        menuLigar.setIntent(intentLigar);

        MenuItem menuSms = menu.add("Enviar SMS");
        Intent intentSms = new Intent(Intent.ACTION_VIEW);
        intentSms.setData(Uri.parse("sms:"+alunoSelecionado.getTelefone()));
        //intentSms.putExtra("sms_body", "Mensagem");
        menuSms.setIntent(intentSms);

        MenuItem menuMapa = menu.add("Achar no Mapa");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        intentMapa.setData(Uri.parse("geo:0,0?z=14&q="+alunoSelecionado.getEndereco()));
        menuMapa.setIntent(intentMapa);

        MenuItem menuNavegar = menu.add("Navegar no site");
        Intent intentNavegar = new Intent(Intent.ACTION_VIEW);
        String site = alunoSelecionado.getSite();
        site = site.startsWith("http://") ? site : "http://" + site;
        intentNavegar.setData(Uri.parse(site));
        menuNavegar.setIntent(intentNavegar);

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
