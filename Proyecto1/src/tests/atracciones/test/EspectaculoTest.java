package atracciones.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usuario.Empleado;
import atracciones.Espectaculo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EspectaculoTest {

    private List<LocalDateTime> horarios;
    private List<Empleado> empleados;

    @BeforeEach
    void setUp() {
        horarios = Arrays.asList(LocalDateTime.of(2025, Month.JUNE, 1, 15, 0));
        empleados = Arrays.asList(new Empleado("Juan","123","juan@parque.com","rol"), new Empleado("Ana","456","ana@parque.com","rol"));
    }

    @Test
    void testConstructorConTemporada() {
        Espectaculo espectaculo = new Espectaculo(
                "Show Mágico",
                "Un show de magia",
                horarios,
                "Zona Norte",
                true,
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 8, 1),
                "Lluvia",
                2,
                empleados
        );

        assertTrue(espectaculo.isEsDeTemporada());
        assertEquals("Show Mágico", espectaculo.getNombre());
        assertEquals("Zona Norte", espectaculo.getUbicacionGeneral());
        assertEquals(2, espectaculo.getEmpleadosMin());
        assertEquals("Lluvia", espectaculo.getCondicionClimatica());
        assertEquals(0, espectaculo.getEmpleadosAsignados().size()); // El constructor inicializa como nueva lista vacía
    }

    @Test
    void testConstructorSinTemporada() {
        Espectaculo espectaculo = new Espectaculo(
                "Show de Robots",
                "Robots en acción",
                horarios,
                "Zona Sur",
                "Viento fuerte",
                1,
                empleados
        );

        assertFalse(espectaculo.isEsDeTemporada());
        assertNull(espectaculo.getInicioTemporada());
        assertNull(espectaculo.getFinTemporada());
    }

    @Test
    void testTienePersonalSuficiente() {
        Espectaculo espectaculo = new Espectaculo(
                "Aventura Musical",
                "Concierto interactivo",
                horarios,
                "Plaza Central",
                "Tormenta",
                2,
                empleados
        );

        // Asignamos dos empleados
        espectaculo.setEmpleadosAsignados(empleados);
        assertTrue(espectaculo.tienePersonalSuficiente());

        
        espectaculo.setEmpleadosAsignados(Arrays.asList(new Empleado("Carlos","789","carlos@parque.com","rol")));
        assertFalse(espectaculo.tienePersonalSuficiente());
    }

    @Test
    void testEstaOperativa_CondicionesValidas() {
        Espectaculo espectaculo = new Espectaculo(
                "Teatro de Sombras",
                "Espectáculo visual",
                horarios,
                "Anfiteatro",
                true,
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 8, 1),
                "Lluvia",
                1,
                empleados
        );

        espectaculo.setEmpleadosAsignados(empleados);
        boolean operativa = espectaculo.estaOperativa(LocalDate.of(2025, 6, 15), "Soleado");
        assertTrue(operativa);
    }

    @Test
    void testEstaOperativa_FueraDeTemporada() {
        Espectaculo espectaculo = new Espectaculo(
                "Circo de Invierno",
                "Show de invierno",
                horarios,
                "Zona Helada",
                true,
                LocalDate.of(2025, 12, 1),
                LocalDate.of(2026, 2, 28),
                "Nieve",
                1,
                empleados
        );

        espectaculo.setEmpleadosAsignados(empleados);
        assertFalse(espectaculo.estaOperativa(LocalDate.of(2025, 10, 15), "Soleado"));
    }

    @Test
    void testEstaOperativa_CondicionClimaticaDesfavorable() {
        Espectaculo espectaculo = new Espectaculo(
                "Acuático Extremo",
                "Show de agua",
                horarios,
                "Piscina",
                false,
                null,
                null,
                "Tormenta",
                1,
                empleados
        );

        espectaculo.setEmpleadosAsignados(empleados);
        assertFalse(espectaculo.estaOperativa(LocalDate.of(2025, 6, 15), "Tormenta"));
    }

    @Test
    void testEstaOperativa_SinPersonal() {
        Espectaculo espectaculo = new Espectaculo(
                "Show de Fuego",
                "Pirotecnia espectacular",
                horarios,
                "Zona Roja",
                false,
                null,
                null,
                "Viento fuerte",
                1,
                empleados
        );

        // No hay empleados asignados
        espectaculo.setEmpleadosAsignados(new ArrayList<>());
        assertFalse(espectaculo.estaOperativa(LocalDate.of(2025, 7, 10), "Soleado"));
    }
}
