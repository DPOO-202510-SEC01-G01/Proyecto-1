package persistencia;

import java.util.HashMap;
import java.util.Map;

public class UtilJSON {

    public static Map<String, String> parsearObjetoJSON(String json) {
        Map<String, String> datos = new HashMap<>();

        // Quitar llaves
        json = json.trim();
        if (json.startsWith("{")) json = json.substring(1);
        if (json.endsWith("}")) json = json.substring(0, json.length() - 1);

        // Separar atributos
        String[] pares = json.split(",");

        for (String par : pares) {
            String[] partes = par.split(":", 2);
            if (partes.length == 2) {
                String clave = partes[0].trim().replace("\"", "");
                String valor = partes[1].trim().replace("\"", "");
                datos.put(clave, valor);
            }
        }

        return datos;
    }
}
