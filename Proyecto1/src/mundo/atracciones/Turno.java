package atracciones;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Turno {

    protected final String horaInicio;   
    protected final String horaFin;

    public Turno(String horaInicio, String horaFin) {
        
        try {
            LocalTime inicio = LocalTime.parse(horaInicio);
            LocalTime fin = LocalTime.parse(horaFin);

            if (!fin.isAfter(inicio)) {
                throw new IllegalArgumentException("La hora de fin (" + horaFin + ") debe ser posterior a la hora de inicio (" + horaInicio + ")");
            }

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("El formato de hora debe ser HH:mm en formato ISO (ej: 08:00, 17:30)", e);
        }

        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public Duration getDuracion() {
        LocalTime inicio = LocalTime.parse(horaInicio);
        LocalTime fin = LocalTime.parse(horaFin);
        return Duration.between(inicio, fin);
    }
}
