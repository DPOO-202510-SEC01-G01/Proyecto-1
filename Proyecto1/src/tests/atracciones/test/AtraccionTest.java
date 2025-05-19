package atracciones.test;


import atracciones.AtraccionCultural;
import atracciones.AtraccionMecanica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usuario.Empleado;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AtraccionTest {

    private AtraccionCultural cultural;
    private AtraccionMecanica mecanica;
    private Empleado empleado1;
    private Empleado empleado2;

    @BeforeEach
    public void setUp() {
        cultural = new AtraccionCultural(
                "Museo", 20, 2, "Zona A", 1, "Soleado", 12
        );

        mecanica = new AtraccionMecanica(
                "Montaña Rusa", 15, 1, "Zona B", 2, "Lluvioso",
                1.2, 2.0, 1.1, 100, "Alto",
                new ArrayList<>()
        );

        empleado1 = new Empleado("Ana", "123","ana@parque.com","rol");
        empleado2 = new Empleado("Luis", "456","luis@parque.com","rol");
    }

    @Test
    public void testAsignarEmpleado() {
        cultural.asignarEmpleado(empleado1);
        assertEquals(1, cultural.getEmpleadosAsignados().size());
        assertTrue(cultural.getEmpleadosAsignados().contains(empleado1));
    }

    @Test
    public void testDesasignarEmpleado() {
        cultural.asignarEmpleado(empleado1);
        cultural.desasignarEmpleado(empleado1);
        assertFalse(cultural.getEmpleadosAsignados().contains(empleado1));
    }

    @Test
    public void testTienePersonalSuficiente() {
        cultural.asignarEmpleado(empleado1);
        assertFalse(cultural.tienePersonalSuficiente());

        cultural.asignarEmpleado(empleado2);
        assertTrue(cultural.tienePersonalSuficiente());
    }

    @Test
    public void testAtraccionNoDeTemporada_EstaOperativa() {
        mecanica.asignarEmpleado(empleado1);
        boolean operativa = mecanica.estaOperativa(LocalDate.now(), "Nublado");
        assertTrue(operativa);  // Solo falla si el clima es igual al configurado (Lluvioso)
    }

    @Test
    public void testAtraccionNoDeTemporada_ClimaIncompatible() {
        mecanica.asignarEmpleado(empleado1);
        boolean operativa = mecanica.estaOperativa(LocalDate.now(), "Lluvioso");
        assertFalse(operativa);
    }

    @Test
    public void testAtraccionDeTemporada_FueraDeTemporada() {
        // noviembre a febrero es una temporada que cruza el año
        AtraccionCultural culturalTemp = new AtraccionCultural(
                "Teatro", 10, 1, "Zona C", 1,
                Month.NOVEMBER, Month.FEBRUARY, "Soleado", 10
        );

        culturalTemp.asignarEmpleado(empleado1);
        // Fecha fuera de temporada: junio
        boolean operativa = culturalTemp.estaOperativa(LocalDate.of(2025, Month.JUNE, 10), "Nublado");
        assertFalse(operativa);
    }

    @Test
    public void testAtraccionDeTemporada_DentroDeTemporadaYTodoCorrecto() {
        AtraccionCultural culturalTemp = new AtraccionCultural(
                "Teatro", 10, 1, "Zona C", 1,
                Month.MARCH, Month.JULY, "Soleado", 10
        );

        culturalTemp.asignarEmpleado(empleado1);
        boolean operativa = culturalTemp.estaOperativa(LocalDate.of(2025, Month.APRIL, 15), "Nublado");
        assertTrue(operativa);
    }

    @Test
    public void testAtraccionDeTemporada_SinPersonal() {
        AtraccionCultural culturalTemp = new AtraccionCultural(
                "Teatro", 10, 1, "Zona C", 1,
                Month.JANUARY, Month.DECEMBER, "Soleado", 10
        );

        // No se asignan empleados
        boolean operativa = culturalTemp.estaOperativa(LocalDate.now(), "Nublado");
        assertFalse(operativa);
    }
}