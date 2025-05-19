package tiquetes;

import java.util.Date;

public class Temporada extends Tiquete {
    private Date fechaInicio;
    private Date fechaFin;
    private String categoria; // Familiar, Oro, Diamante

    public Temporada(Date fechaCompra, int precio, Date fechaInicio, Date fechaFin, String categoria) {
        super(fechaCompra, precio);
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.categoria = categoria;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Tiquete Temporada [" + categoria + "] - válido de " + fechaInicio + " a " + fechaFin + " - " + super.toString();
    }
}
    