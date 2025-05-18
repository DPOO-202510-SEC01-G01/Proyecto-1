package persistencia;

import java.io.FileWriter;
import java.util.Collection;

import parque.Parque;
import usuario.Admin;
import usuario.Cliente;
import usuario.Empleado;
import usuario.Usuario;

public class GuardadorUsuarios {

    public static void guardarUsuarios(String ruta, Parque parque) {
        try (FileWriter writer = new FileWriter(ruta)) {
            Collection<Usuario> usuarios = parque.getUsuarios();

            writer.write("[\n");
            int i = 0;
            for (Usuario u : usuarios) {
                StringBuilder obj = new StringBuilder();
                obj.append("  {\n");
                obj.append("    \"username\": \"" + u.getUserName() + "\",\n");
                obj.append("    \"password\": \"" + u.autenticar(u.getUserName()) + "\",\n"); // Este método devuelve booleano, ¡NO lo uses aquí!
                obj.append("    \"nombre\": \"" + u.getNombre() + "\",\n");

                if (u instanceof Cliente) {
                    obj.append("    \"tipo\": \"cliente\"\n");
                } else if (u instanceof Empleado) {
                    obj.append("    \"tipo\": \"empleado\",\n");
                    obj.append("    \"rol\": \"" + ((Empleado) u).getRol() + "\"\n");
                } else if (u instanceof Admin) {
                    obj.append("    \"tipo\": \"admin\"\n");
                }

                obj.append("  }");

                if (i < usuarios.size() - 1) obj.append(",");
                obj.append("\n");
                writer.write(obj.toString());
                i++;
            }
            writer.write("]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



