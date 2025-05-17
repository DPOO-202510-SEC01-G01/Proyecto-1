package usuarios.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.util.ArrayList;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atracciones.Turno;
import parque.Parque;
import usuario.Admin;
import usuario.Empleado;

public class AdminTest{
	private Admin admin;
	private Empleado empleado;
	private Turno turno;
	private Parque parque;
	@BeforeEach
	void setUp() {
		parque = new Parque();
		admin = new Admin("Administrador","12345","admin@prueba", parque);
		ArrayList<String> calificaciones = new ArrayList<String>(); 
		empleado = new Empleado("Empleado","12345","empleado@prueba","Cocina");
		LocalTime inicio = LocalTime.parse("07:00:00");
		LocalTime fin = LocalTime.parse("12:00:00");
		turno = new Turno(inicio, fin);		
	}
	@Test
	void agregarTurnoTest() {
		admin.asignarTurnoAEmpleado(empleado,turno);
		assertTrue(empleado.getTurnos().contains(turno));
	}
	@Test 
	void modificarInformacionEmpleadoTest() {
		admin.cambiarRolEmpleado(empleado, "Prueba_Rol");
		assertTrue(empleado.getRol().contentEquals("Prueba_Rol"));
	}
	@Test 
	void crearNuevaAtraccionSinTemporadaTest() {
		admin.crearNuevaAtraccion("Prueba_atraccion_sin_temporada", 2, 1, "Prueba_ubicacion", 2, "Prueba_clima");
		assertTrue(parque.existeAtraccion("Prueba_atraccion_no_temporada"));
	}
	@Test
	void crearNuevaAtraccionTemporadaTest() {
		admin.crearNuevaAtraccion("Prueba_atraccion_temporada", 2, 1, "Prueba_ubicacion", 2, true, "2024-01-01", "2024-12-31", "Prueba_clima");
		assertTrue(parque.existeAtraccion("Prueba_atraccion_temporada"));
	}
	
}