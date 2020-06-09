package br.com.faculdadedelta.androidavaliacaoadriano.modelo;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class MotoristaAdriano implements Serializable {
    private Long id;
    private String motorista;
    private String categoria;
    private String destino;
    private int distancia;
    private double valor;
    private Date data;


    public MotoristaAdriano() {
    }

    public MotoristaAdriano(Long id, String motorista, String categoria, String destino, int distancia, double valor, Date data) {
        this.id = id;
        this.motorista = motorista;
        this.categoria = categoria;
        this.destino = destino;
        this.distancia = distancia;
        this.valor = valor;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotorista() {
        return motorista;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValorTotal() {
      //  double valorSoma = valor * distancia;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataValid = null;
        try {
            dataValid = sdf.parse("01/12/2020");
        } catch (ParseException e) {
            e.printStackTrace();
        }


       double valorTotal = distancia * valor ;

        if ( valorTotal >= 600) {
            valorTotal = valorTotal - (valorTotal * 0.025);
        }
       else if (data.after(dataValid)) {
            valorTotal = valorTotal - (valorTotal * 0.001);
        }

        return valorTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MotoristaAdriano that = (MotoristaAdriano) o;
        return id.equals(that.id);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
