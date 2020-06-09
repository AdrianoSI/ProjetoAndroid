package br.com.faculdadedelta.androidavaliacaoadriano;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.faculdadedelta.androidavaliacaoadriano.adapter.MotoristaAdapterAdriano;
import br.com.faculdadedelta.androidavaliacaoadriano.dao.MotoristaDAOAdriano;
import br.com.faculdadedelta.androidavaliacaoadriano.modelo.MotoristaAdriano;

public class ListaActivity extends AppCompatActivity {
    private ListView lvLista;
    private MotoristaDAOAdriano dao = new MotoristaDAOAdriano();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        lvLista = findViewById(R.id.lvLista);
        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MotoristaAdriano motoristaSelecionado = (MotoristaAdriano) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("motoristaSelecionado",motoristaSelecionado);
                startActivity(intent);
            }
        });

        lvLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                MotoristaAdriano motoristaSelecionado = (MotoristaAdriano) adapterView.getItemAtPosition(i);
                dao.excluir(motoristaSelecionado);
                carregarLista();
                return false;
            }
        });

    }
    private void carregarLista() {
        MotoristaAdapterAdriano adapter = new MotoristaAdapterAdriano(getBaseContext(), dao.listar());
        lvLista.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
    public void novo(View view) {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
    }

}
