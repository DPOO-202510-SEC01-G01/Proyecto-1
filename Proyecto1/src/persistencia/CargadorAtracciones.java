package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import atracciones.Atraccion;
import atracciones.AtraccionCultural;
import atracciones.AtraccionMecanica;

public class CargadorAtracciones {

    public static List<Atraccion> cargarAtracciones(String rutaArchivo) {
        List<Atraccion> atracciones = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            StringBuilder contenido = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea.trim());
            }

            String json = contenido.toString();

            // Quitar corchetes del array JSON
            if (json.startsWith("[")) json = json.substring(1);
            if (json.endsWith("]")) json = json.substring(0, json.length() - 1);

            // Separar objetos por "},{" (evita conflictos con llaves internas)
            String[] objetos = json.split("(?<=\\}),\\s*(?=\\{)");

            for (String obj : objetos) {
                Atraccion atraccion = parseAtraccionDesdeJson(obj);
                if (atraccion != null) {
                    atracciones.add(atraccion);
                }
            }

        } catch (IOException e) {
            System.out.println("Error leyendo archivo: " + e.getMessage());
        }

        return atracciones;
    }

    private static Atraccion parseAtraccionDesdeJson(String json) {
        Map<String, String> datos = UtilJSON.parsearObjetoJSON(json);

        try {
            String tipo = datos.get("tipo");
            String nombre = datos.get("nombre");
            int capacidadMax = Integer.parseInt(datos.get("capacidadMax"));
            int empleadosMin = Integer.parseInt(datos.get("empleadosMin"));
            String ubicacion = datos.get("ubicacion");
            int nivelExclusividad = Integer.parseInt(datos.get("nivelExclusividad"));
            boolean esDeTemporada = Boolean.parseBoolean(datos.get("esDeTemporada"));
            String condicionClimatica = datos.get("condicionClimatica");

            if ("cultural".equalsIgnoreCase(tipo)) {
                int edadMin = Integer.parseInt(datos.get("edadMin"));
                if (esDeTemporada) {
                    Month mesInicio = Month.valueOf(datos.get("mesInicioTemporada").toUpperCase());
                    Month mesFin = Month.valueOf(datos.get("mesFinTemporada").toUpperCase());
                    return new AtraccionCultural(nombre, capacidadMax, empleadosMin, ubicacion,
                            nivelExclusividad, mesInicio, mesFin, condicionClimatica, edadMin);
                } else {
                    return new AtraccionCultural(nombre, capacidadMax, empleadosMin, ubicacion,
                            nivelExclusividad, condicionClimatica, edadMin);
                }
            } else if ("mecanica".equalsIgnoreCase(tipo)) {
                double alturaMin = Double.parseDouble(datos.get("alturaMin"));
                double alturaMax = Double.parseDouble(datos.get("alturaMax"));
                double alturaMinima = Double.parseDouble(datos.get("alturaMinima"));
                int pesoMax = Integer.parseInt(datos.get("pesoMax"));
                String nivelRiesgo = datos.get("nivelRiesgo");

                List<String> restricciones = new ArrayList<>();
                if (datos.containsKey("restricciones")) {
                    String restriccionesStr = datos.get("restricciones");
                    restriccionesStr = restriccionesStr.replace("[", "").replace("]", "");
                    restricciones = Arrays.asList(restriccionesStr.split(";"));
                }

                if (esDeTemporada) {
                    Month mesInicio = Month.valueOf(datos.get("mesInicioTemporada").toUpperCase());
                    Month mesFin = Month.valueOf(datos.get("mesFinTemporada").toUpperCase());
                    return new AtraccionMecanica(nombre, capacidadMax, empleadosMin, ubicacion,
                            nivelExclusividad, mesInicio, mesFin, condicionClimatica,
                            alturaMin, alturaMax, alturaMinima, pesoMax, nivelRiesgo, restricciones);
                } else {
                    return new AtraccionMecanica(nombre, capacidadMax, empleadosMin, ubicacion,
                            nivelExclusividad, condicionClimatica,
                            alturaMin, alturaMax, alturaMinima, pesoMax, nivelRiesgo, restricciones);
                }
            }

        } catch (Exception e) {
            System.out.println("Error al parsear atracción: " + e.getMessage());
        }

        return null;
    }
}

