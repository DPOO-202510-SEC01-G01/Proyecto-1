package tiquetes;

import java.util.Date;

public class Individual extends Tiquete {
    private String atraccion; // Nombre o ID de la atracción

    public Individual(Date fechaCompra, int precio, String atraccion) {
        super(fechaCompra, precio);
        this.atraccion = atraccion;
    }

    public String getAtraccion() {
        return atraccion;
    }

    @Override
    public String toString() {
        return "Tiquete Individual para " + atraccion + " - " + super.toString();
    }
}
     