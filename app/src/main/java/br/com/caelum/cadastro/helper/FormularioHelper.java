package br.com.caelum.cadastro.helper;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.activity.FormularioActivity;
import br.com.caelum.cadastro.modelo.Aluno;

public class FormularioHelper {
    private Activity activity;

    private Aluno aluno;

    private ImageView foto;
    private Button fotoButton;
    private EditText nome;
    private EditText telefone;
    private EditText endereco;
    private EditText site;
    private RatingBar nota;

    public FormularioHelper(FormularioActivity activity) {
        this.activity = activity;

        this.aluno = new Aluno();

        this.foto = (ImageView)activity.findViewById(R.id.formulario_foto);
        this.fotoButton = (Button)activity.findViewById(R.id.formulario_foto_button);
        this.nome = (EditText)activity.findViewById(R.id.formulario_nome);
        this.telefone = (EditText)activity.findViewById(R.id.formulario_telefone);
        this.endereco = (EditText)activity.findViewById(R.id.formulario_endereco);
        this.site = (EditText)activity.findViewById(R.id.formulario_site);
        this.nota = (RatingBar)activity.findViewById(R.id.formulario_nota);
    }

    public Aluno pegaAlunoFormulario(){
        aluno.setNome(nome.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setNota(nota.getRating());

        return aluno;
    }

    public boolean temNome() {
        return !nome.getText().toString().isEmpty();
    }

    public void mostraErro() {
        nome.setError("Compo nome não pode ser vazio");
    }

    public void colocarNoFormulario(Aluno aluno) {
        this.aluno = aluno;
        nome.setText(aluno.getNome());
        telefone.setText(aluno.getTelefone());
        endereco.setText(aluno.getEndereco());
        site.setText(aluno.getSite());
        nota.setRating(aluno.getNota());

    public Button getFotoButton(){
        return fotoButton;
    }

    }
}
