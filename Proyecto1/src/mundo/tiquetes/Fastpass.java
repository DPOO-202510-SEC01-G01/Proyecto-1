package tiquetes;

import java.util.Date;


public class Fastpass extends Tiquete {
    private Date fechaUso;

    public Fastpass(Date fechaCompra, int precio, Date fechaUso) {
        super(fechaCompra, precio);
        this.fechaUso = fechaUso;
    }

    public Date getFechaUso() {
        return fechaUso;
    }

    @Override
    public String toString() {
        return "FastPass para " + fechaUso + " - " + super.toString();
    }
}
  