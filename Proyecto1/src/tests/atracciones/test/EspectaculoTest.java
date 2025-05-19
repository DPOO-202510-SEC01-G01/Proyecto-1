package atracciones.test;

import atracciones.Espectaculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usuario.Empleado;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EspectaculoTest {

    private Empleado empleado1;
    private Empleado empleado2;
    private List<LocalDateTime> horarios;
    private Espectaculo espectaculo;

    @BeforeEach
    public void setUp() {
        empleado1 = new Empleado("Mario", "101","mario@parque.com","rol");
        empleado2 = new Empleado("Lucia", "102","lucia@parque.com","rol");
        horarios = List.of(LocalDateTime.of(2025, Month.JUNE, 15, 17, 0));

        espectaculo = new Espectaculo(
                "Show de Magia",
                "Espectáculo de ilusionismo",
                horarios,
                "Plaza Central",
                false,
                null,
                null,
                "Soleado",
                2,
                new ArrayList<>()
        );
    }

    @Test
    public void testTienePersonalSuficiente() {
        espectaculo.getEmpleadosAsignados().add(empleado1);
        assertFalse(espectaculo.tienePersonalSuficiente());

        espectaculo.getEmpleadosAsignados().add(empleado2);
        assertTrue(espectaculo.tienePersonalSuficiente());
    }

    @Test
    public void testEspectaculoNoDeTemporada_ClimaIncorrecto() {
        espectaculo.getEmpleadosAsignados().add(empleado1);
        espectaculo.getEmpleadosAsignados().add(empleado2);
        boolean operativa = espectaculo.estaOperativa(LocalDate.now(), "Soleado");
        assertFalse(operativa); // clima igual al configurado => no operativa
    }

    @Test
    public void testEspectaculoNoDeTemporada_TodoCorrecto() {
        espectaculo.setCondicionClimatica("Lluvioso");
        espectaculo.getEmpleadosAsignados().add(empleado1);
        espectaculo.getEmpleadosAsignados().add(empleado2);
        boolean operativa = espectaculo.estaOperativa(LocalDate.of(2025, Month.JUNE, 15), "Soleado");
        assertTrue(operativa);
    }

    @Test
    public void testEspectaculoDeTemporada_FueraDeRango() {
        Espectaculo espectaculoTemp = new Espectaculo(
                "Show Invierno",
                "Navidad y luces",
                horarios,
                "Pista de hielo",
                true,
                Month.NOVEMBER,
                Month.FEBRUARY,
                "Soleado",
                1,
                new ArrayList<>()
        );

        espectaculoTemp.getEmpleadosAsignados().add(empleado1);
        boolean operativa = espectaculoTemp.estaOperativa(LocalDate.of(2025, Month.JUNE, 10), "Nublado");
        assertFalse(operativa);
    }

    @Test
    public void testEspectaculoDeTemporada_OperativoCorrecto() {
        Espectaculo espectaculoTemp = new Espectaculo(
                "Festival Verano",
                "Shows musicales",
                horarios,
                "Escenario Principal",
                true,
                Month.JUNE,
                Month.AUGUST,
                "Niebla",
                1,
                new ArrayList<>()
        );

        espectaculoTemp.getEmpleadosAsignados().add(empleado1);
        boolean operativa = espectaculoTemp.estaOperativa(LocalDate.of(2025, Month.JULY, 5), "Despejado");
        assertTrue(operativa);
    }
}
