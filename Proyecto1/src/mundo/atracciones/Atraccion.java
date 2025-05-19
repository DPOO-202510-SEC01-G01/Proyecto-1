package atracciones;
import java.util.ArrayList;

import java.util.List;
import java.time.LocalDate;


import usuario.Empleado;
import lugares.LugarTrabajo;

public abstract class Atraccion implements LugarTrabajo {
	
	protected String nombre;
    protected  int capacidadMax;
    protected  int empleadosMin;
    protected  String ubicacion;
    protected  int nivelExclusividad; // 0 = Basico, 1=familiar, 2=oro, 3=,diamante
    protected List<Empleado> empleadosAsignados;
    protected  boolean esDeTemporada;
    protected  LocalDate InicioTemporada; 
    protected  LocalDate FinTemporada;   
    protected String CondicionClimatica;
    
    
 
    // Constructor para atracciones NO de temporada
    public Atraccion(String nombre, int capacidadMax, int empleadosMin, String ubicacion, int nivelExclusividad,
                     String condicionClimatica) {
        this.nombre = nombre;
        this.capacidadMax = capacidadMax;
        this.empleadosMin = empleadosMin;
        this.ubicacion = ubicacion;
        this.nivelExclusividad = nivelExclusividad;
        this.CondicionClimatica = condicionClimatica;
        this.esDeTemporada = false;
        this.InicioTemporada = null;
        this.FinTemporada = null;
        this.empleadosAsignados = new ArrayList<>();
    }

    // Constructor para atracciones de temporada 
    public Atraccion(String nombre, int capacidadMax, int empleadosMin, String ubicacion, int nivelExclusividad,
    		LocalDate InicioTemporada, LocalDate FinTemporada, String condicionClimatica) {
        this.nombre = nombre;
        this.capacidadMax = capacidadMax;
        this.empleadosMin = empleadosMin;
        this.ubicacion = ubicacion;
        this.nivelExclusividad = nivelExclusividad;
        this.esDeTemporada = true;
        this.InicioTemporada = InicioTemporada;
        this.FinTemporada = FinTemporada;
        this.CondicionClimatica = condicionClimatica;
        this.empleadosAsignados = new ArrayList<>();
    }


    
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCapacidadMax() {
		return capacidadMax;
	}
	public void setCapacidadMax(int capacidadMax) {
		this.capacidadMax = capacidadMax;
	}

	public int getEmpleadosMin() {
		return empleadosMin;
	}
	public void setEmpleadosMin(int empleadosMin) {
		this.empleadosMin = empleadosMin;
	}

	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public int getNivelExclusividad() {
		return nivelExclusividad;
	}
	public void setNivelExclusividad(int nivelExclusividad) {
		this.nivelExclusividad = nivelExclusividad;
	}

	public List<Empleado> getEmpleadosAsignados() {
		return empleadosAsignados;
	}
	public void setEmpleadosAsignados(List<Empleado> empleadosAsignados) {
		this.empleadosAsignados = empleadosAsignados;
	}

	public boolean isEsDeTemporada() {
		return esDeTemporada;
	}
	public void setEsDeTemporada(boolean esDeTemporada) {
		this.esDeTemporada = esDeTemporada;
	}

	public LocalDate getInicioTemporada() {
		return InicioTemporada;
	}
	public void setInicioTemporada(LocalDate InicioTemporada) {
		this.InicioTemporada = InicioTemporada;
	}

	public LocalDate getFinTemporada() {
		return FinTemporada;
	}
	public void setMesFinTemporada(LocalDate mesFinTemporada) {
		this.FinTemporada = mesFinTemporada;
	}

	public String getCondicionClimatica() {
		return CondicionClimatica;
	}
	public void setCondicionClimatica(String condicionClimatica) {
		CondicionClimatica = condicionClimatica;
	}

	public boolean tienePersonalSuficiente() {
        return this.empleadosAsignados.size() >= this.empleadosMin;
    }


    /**
     * Verifica si la atracción está operativa en una fecha y condiciones climáticas dadas.
     */
	
	
	///
	public boolean estaOperativa(LocalDate fecha, String climaActual) {
        if (esDeTemporada) {
            if (fecha.isBefore(InicioTemporada) || fecha.isAfter(FinTemporada)) {
                return false;
            }
        }

        if (CondicionClimatica != null && CondicionClimatica.equalsIgnoreCase(climaActual)) {
            return false;
        }

        return tienePersonalSuficiente();
    }
}
	