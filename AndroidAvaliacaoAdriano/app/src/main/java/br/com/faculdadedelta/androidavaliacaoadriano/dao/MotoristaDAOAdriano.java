package br.com.faculdadedelta.androidavaliacaoadriano.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.androidavaliacaoadriano.modelo.MotoristaAdriano;

public class MotoristaDAOAdriano {

    private static List<MotoristaAdriano> listaMotorista = new ArrayList<>();
    private static Long idGerador = 1L;

    public void incluir(MotoristaAdriano motoristaAdriano) {
        motoristaAdriano.setId(idGerador++);
        listaMotorista.add(motoristaAdriano);
    }

    public void excluir(MotoristaAdriano motoristaAdriano) {
        listaMotorista.remove(motoristaAdriano);
    }

    public List<MotoristaAdriano> listar() {
        return listaMotorista;
    }

    public void alterar(MotoristaAdriano motoristaAdriano) {
        for (MotoristaAdriano motoristaAux: listaMotorista) {
            long idMotorista = motoristaAdriano.getId();
            long idMotoristaAux = motoristaAux.getId();
            if (idMotorista == idMotoristaAux) {
                listaMotorista.remove(motoristaAux);
                listaMotorista.add(motoristaAdriano);
                break;
            }
        }
    }
}
