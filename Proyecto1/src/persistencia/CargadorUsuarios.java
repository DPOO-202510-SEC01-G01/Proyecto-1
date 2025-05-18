package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import parque.Parque;
import usuario.Admin;
import usuario.Cliente;
import usuario.Empleado;
import usuario.Usuario;

public class CargadorUsuarios {

    public static void cargarUsuarios(String rutaArchivo, Parque parque) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                jsonBuilder.append(linea.trim());
            }

            String json = jsonBuilder.toString();

            json = json.substring(1, json.length() - 1); // Quita corchetes []
            String[] usuariosRaw = json.split("\\},\\{");

            for (int i = 0; i < usuariosRaw.length; i++) {
                String usuarioJson = usuariosRaw[i];

                if (!usuarioJson.startsWith("{")) usuarioJson = "{" + usuarioJson;
                if (!usuarioJson.endsWith("}")) usuarioJson = usuarioJson + "}";

                Map<String, String> atributos = UtilJSON.parsearObjetoJSON(usuarioJson);

                String tipo = atributos.get("tipo");
                String username = atributos.get("username");
                String password = atributos.get("password");
                String correo = atributos.get("correo");

                Usuario u = null;

                if (tipo.equals("cliente")) {
                    u = new Cliente(username, password, correo);
                } else if (tipo.equals("empleado")) {
                    String rol = atributos.get("rol");
                    u = new Empleado(username, password, correo, rol);
                } else if (tipo.equals("admin")) {
                    u = new Admin(username, password, correo, parque);
                }

                if (u != null) {
                    parque.agregarUsuario(u);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




