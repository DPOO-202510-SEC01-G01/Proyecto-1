package tiquetes;

import java.time.LocalDate;
import java.util.Date;

public abstract class Tiquete {
    private Date fechaCompra;
    private boolean usado;
    private int precio;

    public Tiquete(Date fechaCompra, int precio) {
        this.fechaCompra = fechaCompra;
        this.precio = precio;
        this.usado = false;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public boolean isUsado() {
        return usado;
    }

    public void marcarComoUsado() {
        this.usado = true;
    }

    public int getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Tiquete comprado el " + fechaCompra + ", precio: " + precio + ", usado: " + usado;
    }
}