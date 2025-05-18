package atracciones;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.Month;

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
    protected  Month mesInicioTemporada; 
    protected  Month mesFinTemporada;   
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
        this.mesInicioTemporada = null;
        this.mesFinTemporada = null;
        this.empleadosAsignados = new ArrayList<>();
    }

    // Constructor para atracciones de temporada 
    public Atraccion(String nombre, int capacidadMax, int empleadosMin, String ubicacion, int nivelExclusividad,
                     Month mesInicioTemporada, Month mesFinTemporada, String condicionClimatica) {
        this.nombre = nombre;
        this.capacidadMax = capacidadMax;
        this.empleadosMin = empleadosMin;
        this.ubicacion = ubicacion;
        this.nivelExclusividad = nivelExclusividad;
        this.esDeTemporada = true;
        this.mesInicioTemporada = mesInicioTemporada;
        this.mesFinTemporada = mesFinTemporada;
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

	public Month getMesInicioTemporada() {
		return mesInicioTemporada;
	}
	public void setMesInicioTemporada(Month mesInicioTemporada) {
		this.mesInicioTemporada = mesInicioTemporada;
	}

	public Month getMesFinTemporada() {
		return mesFinTemporada;
	}
	public void setMesFinTemporada(Month mesFinTemporada) {
		this.mesFinTemporada = mesFinTemporada;
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
    public boolean estaOperativa(LocalDate fecha, String climaActual) {
        //  Verifica temporada
        if (esDeTemporada) {
        	Month mesActual = fecha.getMonth();

            // Temporada dentro de un mismo año
            if (mesInicioTemporada.compareTo(mesFinTemporada) <= 0) {
                if (mesActual.compareTo(mesInicioTemporada) < 0 || mesActual.compareTo(mesFinTemporada) > 0) {
                    return false;
                }
            } else {
                // Temporada que cruza el cambio de año (ej: noviembre a febrero)
                if (mesActual.compareTo(mesFinTemporada) > 0 && mesActual.compareTo(mesInicioTemporada) < 0) {
                    return false;
                }
            }
        }
        //  Verifica clima
        if (CondicionClimatica == climaActual ) {
            return false; 
        }
        //  Verifica personal (si tiene suficientes empleados asignados para hoy)
        
        if (!tienePersonalSuficiente()) {
             // System.out.println("Atracción " + nombre + " no operativa por falta de personal.");
            return false;
        }

        return true; 
    
    }
}