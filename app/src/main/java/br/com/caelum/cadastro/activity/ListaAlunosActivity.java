package br.com.caelum.cadastro.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import br.com.caelum.cadastro.R;

public class ListaAlunosActivity extends ActionBarActivity {

    ListView listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        final String[] alunos = {"Anderson", "Filipe", "Guilherme"};

        this.listaAlunos = (ListView)findViewById(R.id.lista_alunos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alunos);
        listaAlunos.setAdapter(adapter);

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mensagem = "Posicao Selecionada: " + position;
                Toast.makeText(ListaAlunosActivity.this, mensagem, Toast.LENGTH_SHORT).show();
            }
        });

        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String mensagem = (String)parent.getItemAtPosition(position);
                mensagem = "Clique longo: " + mensagem;


                Toast.makeText(ListaAlunosActivity.this, mensagem, Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

}
