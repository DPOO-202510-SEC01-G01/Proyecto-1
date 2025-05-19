package atracciones;


import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import usuario.Empleado;

public class AtraccionCultural extends Atraccion {
	
	protected int edadMin;

	// Constructor para atracciones NO de temporada
    public AtraccionCultural(String nombre, int capacidadMax, int empleadosMin, String ubicacion,
                             int nivelExclusividad, String condicionClimatica, int edadMin) {
        super(nombre, capacidadMax, empleadosMin, ubicacion, nivelExclusividad, condicionClimatica);
        this.edadMin = edadMin;
    }

    // Constructor para atracciones DE temporada
    public AtraccionCultural(String nombre, int capacidadMax, int empleadosMin, String ubicacion,
                             int nivelExclusividad, LocalDate Inicio, LocalDate Fin,
                             String condicionClimatica, int edadMin) {
        super(nombre, capacidadMax, empleadosMin, ubicacion, nivelExclusividad,
              Inicio, Fin, condicionClimatica);
        this.edadMin = edadMin;
    }
	public int getEdadMin() {
		return edadMin;
	}
	public void setEdadMin(int edadMin) {
		this.edadMin = edadMin;
	}
	
	 public void asignarEmpleado(Empleado empleado) {
	        if (empleado != null && !this.empleadosAsignados.contains(empleado)) {
	           this.empleadosAsignados.add(empleado);
	       }
	   }

	   
	   public void desasignarEmpleado(Empleado empleado) {
	        if (empleado != null) {
	           this.empleadosAsignados.remove(empleado);
	       }
	   }
	    

}
