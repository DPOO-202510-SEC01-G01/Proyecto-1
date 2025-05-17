package usuario;
import java.time.LocalDate;

import atracciones.Atraccion;
import atracciones.Turno;
import parque.Parque;

public class Admin extends Usuario {
	private Parque parque;
    public Admin(String userName, String password, String correo, Parque parque) {
        super(userName, password, correo);
        parque = this.parque;
    }

    public void crearNuevaAtraccion(String nombre, int capacidadMax, int empleadosMin, String ubicacion, int nivel,
    		boolean esDeTemporada, String fecha_inicial, String fecha_final, String clima) {
        System.out.println("Admin " + super.getUserName() + " está creando una atracción...");
        Atraccion atraccion = new Atraccion(nombre, capacidadMax, empleadosMin, ubicacion, nivel, true, LocalDate.parse(fecha_inicial), 
        		LocalDate.parse(fecha_final), clima);
        
        //Pendiente de cambios, cambio en parametros del metodo constructor Atraccion (Sara).
    }
    public void crearNuevaAtraccion(String nombre, int capacidadMax, int empleadosMin, String ubicacion, int nivel, String clima) {
        System.out.println("Admin " + super.getUserName() + " está creando una atracción...");
        Atraccion atraccion = new Atraccion(nombre, capacidadMax, empleadosMin, ubicacion, nivel, false, clima);
        
    }

    public void asignarTurnoAEmpleado(Empleado empleado, Turno turno /*, ParqueDiversiones parque*/) {
        System.out.println("Admin " + super.getUserName() + " asignando turno a " + super.getUserName());
        empleado.agregarTurno(turno);
    }

    public void cambiarRolEmpleado(Empleado empleado, String rol) {
         System.out.println("Admin " + empleado.getUserName() + " modificando rol de " + empleado.getUserName());
         empleado.setRol(rol);
    }

    public String toString() {
        return "Admin [User: " + super.getUserName() + ", Correo: " + super.getUserName() + "]";
    }
}