package persistencia;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import atracciones.Atraccion;
import atracciones.AtraccionCultural;
import atracciones.AtraccionMecanica;

public class GuardadorAtracciones {

    public static void guardarAtracciones(List<Atraccion> atracciones) {
        String ruta = ConstantesPersistencia.RUTA_ATRACCIONES;

        try (FileWriter writer = new FileWriter(ruta)) {
            writer.write("[\n");

            for (int i = 0; i < atracciones.size(); i++) {
                Atraccion atraccion = atracciones.get(i);
                String json = convertirAtraccionAJSON(atraccion);
                writer.write(json);

                if (i < atracciones.size() - 1) {
                    writer.write(",\n");
                }
            }

            writer.write("\n]");
        } catch (IOException e) {
            System.out.println("Error al guardar atracciones: " + e.getMessage());
        }
    }

    private static String convertirAtraccionAJSON(Atraccion atraccion) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        sb.append("\"tipo\":\"").append(atraccion.getClass().getSimpleName()).append("\",");
        sb.append("\"nombre\":\"").append(atraccion.getNombre()).append("\",");
        sb.append("\"capacidadMax\":").append(atraccion.getCapacidadMax()).append(",");
        sb.append("\"empleadosMin\":").append(atraccion.getEmpleadosMin()).append(",");
        sb.append("\"ubicacion\":\"").append(atraccion.getUbicacion()).append("\",");
        sb.append("\"nivelExclusividad\":").append(atraccion.getNivelExclusividad()).append(",");
        sb.append("\"condicionClimatica\":\"").append(atraccion.getCondicionClimatica()).append("\",");

        if (atraccion.isEsDeTemporada()) {
            sb.append("\"mesInicio\":\"").append(atraccion.getMesInicioTemporada().name()).append("\",");
            sb.append("\"mesFin\":\"").append(atraccion.getMesFinTemporada().name()).append("\",");
        }

        if (atraccion instanceof AtraccionCultural) {
            AtraccionCultural ac = (AtraccionCultural) atraccion;
            sb.append("\"edadMin\":").append(ac.getEdadMin());
        } else if (atraccion instanceof AtraccionMecanica) {
            AtraccionMecanica am = (AtraccionMecanica) atraccion;
            sb.append("\"alturaMin\":").append(am.getAlturaMin()).append(",");
            sb.append("\"alturaMax\":").append(am.getAlturaMax()).append(",");
            sb.append("\"alturaMinima\":").append(am.getAlturaMinima()).append(",");
            sb.append("\"pesoMax\":").append(am.getPesoMax()).append(",");
            sb.append("\"nivelRiesgo\":\"").append(am.getNivelRiesgo()).append("\",");

            // Guardar restricciones como lista JSON
            List<String> restricciones = am.getRestricciones();
            sb.append("\"restricciones\":[");
            for (int j = 0; j < restricciones.size(); j++) {
                sb.append("\"").append(restricciones.get(j)).append("\"");
                if (j < restricciones.size() - 1) sb.append(",");
            }
            sb.append("]");
        }

        sb.append("}");
        return sb.toString();
    }
}

