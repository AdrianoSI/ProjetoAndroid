package br.com.faculdadedelta.androidavaliacaoadriano.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.faculdadedelta.androidavaliacaoadriano.R;
import br.com.faculdadedelta.androidavaliacaoadriano.modelo.MotoristaAdriano;

public class MotoristaAdapterAdriano extends BaseAdapter {
    private Context context;
    private List<MotoristaAdriano> listaMotorista;

    public MotoristaAdapterAdriano(Context context, List<MotoristaAdriano> listaMotorista) {
        this.context = context;
        this.listaMotorista = listaMotorista;
    }

    @Override
    public int getCount() {
        return listaMotorista.size();
    }

    @Override
    public Object getItem(int i) {
        return listaMotorista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewRetorno = view.inflate(context, R.layout.item_lista, null);
        MotoristaAdriano motoristaAdriano = (MotoristaAdriano) getItem(i);

        TextView tvMotorista = viewRetorno.findViewById(R.id.tvMotorista);
        tvMotorista.setText("Motorista: " + motoristaAdriano.getMotorista());

        TextView tvCategoria = viewRetorno.findViewById(R.id.tvCategoria);
        tvCategoria.setText("Categoria CNH: " + motoristaAdriano.getCategoria());

        TextView tvDestino = viewRetorno.findViewById(R.id.tvDestino);
        tvDestino.setText("Destino: " + motoristaAdriano.getDestino());

        TextView tvDistancia = viewRetorno.findViewById(R.id.tvDistancia);
        tvDistancia.setText("Distancia: " + motoristaAdriano.getDistancia());

        TextView tvValor = viewRetorno.findViewById(R.id.tvValor);
        tvValor.setText("Valor km: " + motoristaAdriano.getValor());

        SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");

        TextView tvData = viewRetorno.findViewById(R.id.tvData);
        tvData.setText("Data da corrida: " + sfd.format(motoristaAdriano.getData()));

        TextView tvValorTotal = viewRetorno.findViewById(R.id.tvValorTotal);
        tvValorTotal.setText("Valor total: " + motoristaAdriano.getValorTotal());

        return viewRetorno;
    }
}
