package atracciones;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import usuario.Empleado;

public class Espectaculo {
	
	protected String nombre;
	protected String descripcion;
	protected List<LocalDateTime> horarios; // Representa los horarios específicos del espectáculo.
	protected String ubicacionGeneral; // Aunque no es fija, puede tener una ubicación general o posibles ubicaciones.
	protected boolean esDeTemporada;
	protected LocalDate InicioTemporada; 
    protected LocalDate FinTemporada;   
    protected String CondicionClimatica;
    protected  int empleadosMin;
    protected List<Empleado> empleadosAsignados;
    
    
	public Espectaculo(String nombre, String descripcion, List<LocalDateTime> horarios, String ubicacionGeneral,
			boolean esDeTemporada, LocalDate InicioTemporada, LocalDate FinTemporada,
			String condicionClimatica, int empleadosMin, List<Empleado> empleadosAsignados) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.horarios = horarios;
		this.ubicacionGeneral = ubicacionGeneral;
		this.esDeTemporada = true;
		this.InicioTemporada = InicioTemporada;
		this.FinTemporada = FinTemporada;
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
		this.InicioTemporada = null;
		this.FinTemporada = null;
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
	public LocalDate getInicioTemporada() {
		return InicioTemporada;
	}
	public void setInicioTemporada(LocalDate InicioTemporada) {
		this.InicioTemporada = InicioTemporada;
	}
	public LocalDate getFinTemporada() {
		return FinTemporada;
	}
	public void setFinTemporada(LocalDate FinTemporada) {
		this.FinTemporada = FinTemporada;
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
	

