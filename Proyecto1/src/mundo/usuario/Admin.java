package usuario;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import atracciones.Atraccion;
import atracciones.Turno;
import atracciones.AtraccionCultural;
import parque.Parque;


public class Admin extends Usuario {
	private Parque parque;
    public Admin(String userName, String password, String correo, Parque parque) {
        super(userName, password, correo);
        parque = this.parque;
    }

    public void crearNuevaAtraccionCulturalSinTemporada(String nombre, int capacidadMax, int empleadosMin, String ubicacion, int nivel,
    		String fecha_inicial, String fecha_final, String clima, int edadMin) {
        System.out.println("Admin " + super.getUserName() + " está creando una atracción...");
        AtraccionCultural atraccion = new AtraccionCultural(nombre, capacidadMax, empleadosMin, ubicacion, nivel, clima, edadMin);
        parque.agregarAtraccion(atraccion);
        
        //Pendiente de cambios, cambio en parametros del metodo constructor Atraccion (Sara).
    }
    public void crearNuevaAtraccionCulturalDeTemporada(String nombre, int capacidadMax, int empleadosMin, String ubicacion,
            int nivel, LocalDate fechaInicio, LocalDate fechaFinal, String clima, int edadMin) {
        System.out.println("Admin " + super.getUserName() + " está creando una atracción...");
        AtraccionCultural atraccion = new AtraccionCultural(nombre, capacidadMax, empleadosMin, ubicacion, nivel, fechaInicio,
        		fechaFinal, clima, edadMin);
        
    }
    public void crearNuevaAtraccionMecanidaSinTemporada(String nombre, int capacidadMax, int empleadosMin, String ubicacion,
            int nivelExclusividad, String condicionClimatica,
            double alturaMin, double alturaMax, double alturaMinima,
            int pesoMax, String nivelRiesgo, List<String> restricciones) {
    	System.out.println("Admin " + super.getUserName() + " está creando una atracción...");
    	parque.agregarAtraccion(atraccion);
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