package atracciones;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import usuario.Empleado;

public class Espectaculo {
	
	protected String nombre;
	protected String descripcion;
	protected List<LocalDateTime> horarios; // Representa los horarios específicos del espectáculo.
	protected String ubicacionGeneral; // Aunque no es fija, puede tener una ubicación general o posibles ubicaciones.
	protected boolean esDeTemporada;
	protected  Month mesInicioTemporada; 
    protected  Month mesFinTemporada;   
    protected String CondicionClimatica;
    protected  int empleadosMin;
    protected List<Empleado> empleadosAsignados;
    
    
	public Espectaculo(String nombre, String descripcion, List<LocalDateTime> horarios, String ubicacionGeneral,
			boolean esDeTemporada, Month mesInicioTemporada, Month mesFinTemporada,
			String condicionClimatica, int empleadosMin, List<Empleado> empleadosAsignados) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.horarios = horarios;
		this.ubicacionGeneral = ubicacionGeneral;
		this.esDeTemporada = true;
		this.mesInicioTemporada = mesInicioTemporada;
		this.mesFinTemporada = mesFinTemporada;
		CondicionClimatica = condicionClimatica;
		this.empleadosMin = empleadosMin;
		this.empleadosAsignados = new ArrayList<>();
	}
	
	public Espectaculo(String nombre, String descripcion, List<LocalDateTime> horarios, String ubicacionGeneral,
			String condicionClimatica, int empleadosMin, List<Empleado> empleadosAsignados) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.horarios = horarios;
		this.ubicacionGeneral = ubicacionGeneral;
		this.esDeTemporada = false;
		this.mesInicioTemporada = null;
		this.mesFinTemporada = null;
		CondicionClimatica = condicionClimatica;
		this.empleadosMin = empleadosMin;
		this.empleadosAsignados = new ArrayList<>();
	}
	
	

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<LocalDateTime> getHorarios() {
		return horarios;
	}
	public void setHorarios(List<LocalDateTime> horarios) {
		this.horarios = horarios;
	}
	public String getUbicacionGeneral() {
		return ubicacionGeneral;
	}
	public void setUbicacionGeneral(String ubicacionGeneral) {
		this.ubicacionGeneral = ubicacionGeneral;
	}
	public boolean isEsDeTemporada() {
		return esDeTemporada;
	}
	public void setEsDeTemporada(boolean esDeTemporada) {
		this.esDeTemporada = esDeTemporada;
	}
	public Month getmesInicioTemporada() {
		return mesInicioTemporada;
	}
	public void setmesInicioTemporada(Month mesInicioTemporada) {
		this.mesInicioTemporada = mesInicioTemporada;
	}
	public Month getmesFinTemporada() {
		return mesFinTemporada;
	}
	public void setmesFinTemporada(Month mesFinTemporada) {
		this.mesFinTemporada = mesFinTemporada;
	}
	public String getCondicionClimatica() {
		return CondicionClimatica;
	}
	public void setCondicionClimatica(String condicionClimatica) {
		CondicionClimatica = condicionClimatica;
	}
	public int getEmpleadosMin() {
		return empleadosMin;
	}
	public void setEmpleadosMin(int empleadosMin) {
		this.empleadosMin = empleadosMin;
	}
	public List<Empleado> getEmpleadosAsignados() {
		return empleadosAsignados;
	}
	public void setEmpleadosAsignados(List<Empleado> empleadosAsignados) {
		this.empleadosAsignados = empleadosAsignados;
	}
	
	public boolean tienePersonalSuficiente() {
        return this.empleadosAsignados.size() >= this.empleadosMin;
    }

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
