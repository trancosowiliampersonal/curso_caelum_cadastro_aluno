package br.com.caelum.cadastro.olamundo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.caelum.cadastro.R;

/**
 * Created by Wiliam on 02/03/2016.
 */
public class OlaMundoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ola_mundo);
        Log.i("CICLO DE VIDA", "onCreate");

        Button copiar = (Button)findViewById(R.id.botao);
        copiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText campo = (EditText)findViewById(R.id.seu_campo);
                String valorCampo = campo.getText().toString();
                TextView label = (TextView)findViewById(R.id.campo_exibir);
                label.setText(valorCampo);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("CICLO DE VIDA", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("CICLO DE VIDA", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("CICLO DE VIDA", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("CICLO DE VIDA", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("CICLO DE VIDA", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("CICLO DE VIDA", "onRestart");
    }
}
