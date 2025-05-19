package tiquetes;

import java.time.LocalDate;
import java.util.Date;

public class General extends Tiquete {
    private String tipo; // Básico, Familiar, Oro, Diamante
    private int nivel;

    public General(Date fecha, int precio, String tipo, int nivel) {
        super(fecha, precio);
        this.tipo = tipo;
        this.nivel = nivel;
    }

    public String getTipo() {
        return tipo;
    }
    
    public int getNivel() {
    	return nivel;
    }
    
    

    @Override
    public String toString() {
        return "Tiquete General [" + tipo + "] - " + super.toString();
    }
}