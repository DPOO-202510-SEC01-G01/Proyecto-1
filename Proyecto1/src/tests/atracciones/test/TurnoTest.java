package atracciones.test;

import atracciones.Turno;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class TurnoTest {

    @Test
    public void testDuracionCorrecta() {
        Turno turno = new Turno("08:00", "12:30");
        Duration duracion = turno.getDuracion();
        assertEquals(Duration.ofHours(4).plusMinutes(30), duracion);
    }

    @Test
    public void testFormatoInvalido() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Turno("8am", "12:00");
        });

        assertTrue(exception.getMessage().contains("El formato de hora debe ser"));
    }

    @Test
    public void testHoraFinAntesQueInicio() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Turno("15:00", "14:00");
        });

        assertTrue(exception.getMessage().contains("La hora de fin"));
    }

    @Test
    public void testGetHoraInicioYFin() {
        Turno turno = new Turno("09:00", "17:00");
        assertEquals("09:00", turno.getHoraInicio());
        assertEquals("17:00", turno.getHoraFin());
    }
}