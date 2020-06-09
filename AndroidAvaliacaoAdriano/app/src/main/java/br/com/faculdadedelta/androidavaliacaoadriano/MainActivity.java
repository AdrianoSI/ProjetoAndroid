package br.com.faculdadedelta.androidavaliacaoadriano;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.faculdadedelta.androidavaliacaoadriano.dao.MotoristaDAOAdriano;
import br.com.faculdadedelta.androidavaliacaoadriano.modelo.MotoristaAdriano;

public class MainActivity extends AppCompatActivity {

    private EditText etMotorista;
    private EditText etCategoria;
    private EditText etDestino;
    private EditText etDistancia;
    private EditText etValor;
    private EditText etData;

    private MotoristaDAOAdriano dao = new MotoristaDAOAdriano();
    private MotoristaAdriano motoristaAdriano = new MotoristaAdriano();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etMotorista = findViewById(R.id.etMotorista);
        etCategoria = findViewById(R.id.etCategoria);
        etDestino = findViewById(R.id.etDestino);
        etDistancia = findViewById(R.id.etDistancia);
        etData = findViewById(R.id.etData);
        etValor = findViewById(R.id.etValor);


        Intent intent = getIntent();

        if (intent != null) {
            MotoristaAdriano motoristaSelecionado = (MotoristaAdriano) intent.getSerializableExtra("motoristaSelecionado");
            if (motoristaSelecionado != null) {
                poplularFormulario(motoristaSelecionado);
            }
        }
    }

    private void popularModelo() {
        motoristaAdriano.setMotorista(etMotorista.getText().toString());
        motoristaAdriano.setCategoria(etCategoria.getText().toString());
        motoristaAdriano.setDestino(etDestino.getText().toString());
        motoristaAdriano.setDistancia(Integer.parseInt(etDistancia.getText().toString()));

        motoristaAdriano.setValor(Double.parseDouble(etValor.getText().toString()));

        try {
            motoristaAdriano.setData(sdf.parse(etData.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void poplularFormulario(MotoristaAdriano motoristaSelecionado) {
        etMotorista.setText(motoristaSelecionado.getMotorista());
        etCategoria.setText(motoristaSelecionado.getCategoria());
        etDestino.setText(motoristaSelecionado.getDestino());
        etDistancia.setText(String.valueOf(motoristaSelecionado.getDistancia()));
        etValor.setText(String.valueOf(motoristaSelecionado.getValor()));
        etData.setText(sdf.format(motoristaSelecionado.getData()));
        motoristaAdriano.setId(motoristaSelecionado.getId());
    }

    private String validarCampos() {
        String mensagemRetorno = "";
        if (etMotorista.getText().toString().isEmpty()) {
            mensagemRetorno += "O campo motorista é obrigatorio!";
        }
        if (etCategoria.getText().toString().isEmpty()) {
            mensagemRetorno += "\nO campo categoria é obrigatório!";
        }
        if (etDestino.getText().toString().isEmpty()) {
            mensagemRetorno += "\nO campo destino é obrigatorio!";
        }
        if (etDistancia.getText().toString().isEmpty()) {
            mensagemRetorno += "\nO campo distância é obrigatorio!";
        }
        if (etData.getText().toString().isEmpty()) {
            mensagemRetorno += "\nO campo data é obrigatorio!";
        }
        if (etValor.getText().toString().isEmpty()) {
            mensagemRetorno += "\nO campo valor é obrigatorio!";
        }
        return mensagemRetorno;
    }

    public void salvar(View view) {
        String mensagemErro = validarCampos();
        if (mensagemErro.isEmpty()) {
            popularModelo();
            if (motoristaAdriano.getId() == null) {
                dao.incluir(motoristaAdriano);
                limparCampos();
                Toast.makeText(getBaseContext(),
                        "Inclusão realizada com sucesso!", Toast.LENGTH_LONG).show();
            } else {
                dao.alterar(motoristaAdriano);
                limparCampos();
                Toast.makeText(getBaseContext(),
                        "Alteração realizada com sucesso!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getBaseContext(), mensagemErro, Toast.LENGTH_LONG).show();
        }
    }

    public void limpar(View view) {
        limparCampos();
    }

    public void listar(View view) {
    finish();
    }

    public void limparCampos() {
        etMotorista.setText("");
        etCategoria.setText("");
        etDestino.setText("");
        etDistancia.setText("");
        etData.setText("");
        etValor.setText("");
        motoristaAdriano = new MotoristaAdriano();
    }
}
