package lugares.test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usuario.Empleado;
import lugares.Cafeteria;
import lugares.Taquilla;
import lugares.Tienda;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class LugarServicioTest {

	    private Taquilla taquilla;
	    private Tienda tienda;
	    private Cafeteria cafeteria;
	    private Empleado cajero1;
	    private Empleado cajero2;
	    private Empleado cocinero;

	    @BeforeEach
	    void setUp() {
	        cajero1 = new Empleado("Ana", "123","ana@parque.com","Cajero");
	        cajero2 = new Empleado("Luis", "456","luis@parque.com","Cajero");
	        cocinero = new Empleado("Carlos","789","carlos@parque.com", "Cocinero");

	        List<Empleado> empleadosIniciales = new ArrayList<>();
	        taquilla = new Taquilla("Taquilla Central", "Entrada", 1, new ArrayList<>(empleadosIniciales));
	        tienda = new Tienda("Tienda Principal", "Zona A", 2, new ArrayList<>(empleadosIniciales));
	        cafeteria = new Cafeteria("Cafetería Norte", "Zona B", 1, new ArrayList<>(empleadosIniciales), 1);
	    }

	    @Test
	    void testAsignarYDesasignarEmpleado() {
	        taquilla.asignarEmpleado(cajero1);
	        assertEquals(1, taquilla.getEmpleadosAsignados().size());
	        assertTrue(taquilla.getEmpleadosAsignados().contains(cajero1));

	        taquilla.desasignarEmpleado(cajero1);
	        assertTrue(taquilla.getEmpleadosAsignados().isEmpty());
	    }

	    @Test
	    void testNoDuplicarEmpleado() {
	        tienda.asignarEmpleado(cajero1);
	        tienda.asignarEmpleado(cajero1);
	        assertEquals(1, tienda.getEmpleadosAsignados().size());
	    }

	    @Test
	    void testTienePersonalSuficiente_Taquilla() {
	        taquilla.asignarEmpleado(cajero1);
	        assertTrue(taquilla.tienePersonalSuficiente());
	    }

	    @Test
	    void testTienePersonalSuficiente_Tienda() {
	        tienda.asignarEmpleado(cajero1);
	        assertFalse(tienda.tienePersonalSuficiente()); // solo 1 cajero, se requieren 2

	        tienda.asignarEmpleado(cajero2);
	        assertTrue(tienda.tienePersonalSuficiente());
	    }

	    @Test
	    void testTienePersonalSuficiente_Cafeteria() {
	        cafeteria.asignarEmpleado(cajero1);
	        assertFalse(cafeteria.tienePersonalSuficiente()); // falta cocinero

	        cafeteria.asignarEmpleado(cocinero);
	        assertTrue(cafeteria.tienePersonalSuficiente());
	    }
}


