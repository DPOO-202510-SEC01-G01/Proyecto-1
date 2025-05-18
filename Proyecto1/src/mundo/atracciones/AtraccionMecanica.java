package atracciones;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import usuario.Empleado;

public class AtraccionMecanica extends Atraccion {
	
	
	protected  double alturaMin;
	protected  double alturaMax;
	protected  double alturaMinima;
	protected  int pesoMax;
	protected  String nivelRiesgo;
	protected  List<String> restricciones;
	
	

	// Constructor para atracciones NO de temporada
    public AtraccionMecanica(String nombre, int capacidadMax, int empleadosMin, String ubicacion,
                             int nivelExclusividad, String condicionClimatica,
                             double alturaMin, double alturaMax, double alturaMinima,
                             int pesoMax, String nivelRiesgo, List<String> restricciones) {
        super(nombre, capacidadMax, empleadosMin, ubicacion, nivelExclusividad, condicionClimatica);
        this.alturaMin = alturaMin;
        this.alturaMax = alturaMax;
        this.alturaMinima = alturaMinima;
        this.pesoMax = pesoMax;
        this.nivelRiesgo = nivelRiesgo;
        this.restricciones = restricciones;
    }

    // Constructor para atracciones DE temporada
    public AtraccionMecanica(String nombre, int capacidadMax, int empleadosMin, String ubicacion,
                             int nivelExclusividad, Month mesInicio, Month mesFin,
                             String condicionClimatica, double alturaMin, double alturaMax,
                             double alturaMinima, int pesoMax, String nivelRiesgo,
                             List<String> restricciones) {
        super(nombre, capacidadMax, empleadosMin, ubicacion, nivelExclusividad,
              mesInicio, mesFin, condicionClimatica);
        this.alturaMin = alturaMin;
        this.alturaMax = alturaMax;
        this.alturaMinima = alturaMinima;
        this.pesoMax = pesoMax;
        this.nivelRiesgo = nivelRiesgo;
        this.restricciones = restricciones;
    }
	public double getAlturaMin() {
		return alturaMin;
	}
	public void setAlturaMin(double alturaMin) {
		this.alturaMin = alturaMin;
	}
	public double getAlturaMax() {
		return alturaMax;
	}
	public void setAlturaMax(double alturaMax) {
		this.alturaMax = alturaMax;
	}
	public double getAlturaMinima() {
		return alturaMinima;
	}
	public void setAlturaMinima(double alturaMinima) {
		this.alturaMinima = alturaMinima;
	}
	public int getPesoMax() {
		return pesoMax;
	}
	public void setPesoMax(int pesoMax) {
		this.pesoMax = pesoMax;
	}
	public String getNivelRiesgo() {
		return nivelRiesgo;
	}
	public void setNivelRiesgo(String nivelRiesgo) {
		this.nivelRiesgo = nivelRiesgo;
	}
	public List<String> getRestricciones() {
		return restricciones;
	}
	public void setRestricciones(List<String> restricciones) {
		this.restricciones = restricciones;
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


