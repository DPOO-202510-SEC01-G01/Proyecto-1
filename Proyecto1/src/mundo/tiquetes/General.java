package tiquetes;

import java.time.LocalDate;

public class General extends Tiquete {
    private String tipo; // Básico, Familiar, Oro, Diamante
    private int nivel;

    public General(LocalDate fecha, int precio, String tipo, int nivel) {
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
    @Override
    public String toString() {
        return "Tiquete General [" + tipo + "] - " + super.toString();
    }
}