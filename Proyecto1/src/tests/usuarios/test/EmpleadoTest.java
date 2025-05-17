package usuarios.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atracciones.Turno;
import usuario.Empleado;

public class EmpleadoTest {
	private Empleado empleado;
	@BeforeEach
	void setUp() {
		empleado = new Empleado("Prueba_nombre", "Prueba_password", "correo@prueba", "Prueba_rol");
	}
	@Test
	void getCapacitacionesTest() {
		assertTrue(empleado.getCapacitaciones().isEmpty());
	}
	@Test
	void addCapacitacionesTest() {
		empleado.addCapacitaciones("Prueba_capacitacion");
		assertTrue(empleado.getCapacitaciones().contains("Prueba_capacitacion");
	}
	@Test
	void getRolTest() {
		empleado.getRol();
		assertTrue(empleado.getRol().equals("Prueba_rol"));
	}
	@Test
	void setRolTest() {
		empleado.setRol("Prueba_rol_cambiado");
		assertTrue(empleado.getRol().equals("Prueba_rol_cambiado"));
	}
	@Test
	void getTurnosTest() {
		assertTrue(empleado.getTurnos().isEmpty());
	}
	@Test
	void agregarTurnoTest() {
		Turno turno = new Turno("07:00:00", "12:00:00");
		empleado.agregarTurno(turno);
		assertTrue(empleado.getTurnos().contains(turno));
	}
	@Test
	void eliminarTurnTest() {
		Turno turno = new Turno("07:00:00", "12:00:00");
		empleado.agregarTurno(turno);
		empleado.eliminarTurno(turno);
		assertTrue(!empleado.getTurnos().contains(turno));
	}
}
