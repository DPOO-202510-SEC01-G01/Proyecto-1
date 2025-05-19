package persistencia;

import java.io.FileWriter;
import java.util.Collection;

import parque.Parque;
import usuario.Admin;
import usuario.Cliente;
import usuario.Empleado;
import usuario.Usuario;

public class GuardadorUsuarios {

    private static final String RUTA_USUARIOS = "data/usuarios.json";

    public static void guardarUsuarios(Parque parque) {
        try (FileWriter writer = new FileWriter(RUTA_USUARIOS)) {
            Collection<Usuario> usuarios = parque.getUsuarios();

            writer.write("[\n");
            int i = 0;
            for (Usuario u : usuarios) {
                StringBuilder obj = new StringBuilder();
                obj.append("  {\n");
                obj.append("    \"username\": \"" + u.getUserName() + "\",\n");
                obj.append("    \"password\": \"" + recuperarPassword(u) + "\",\n");
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

    // Método auxiliar temporal hasta que agregues un getPassword()
    private static String recuperarPassword(Usuario u) {
        try {
            java.lang.reflect.Field f = Usuario.class.getDeclaredField("password");
            f.setAccessible(true);
            return (String) f.get(u);
        } catch (Exception e) {
            return "error";
        }
    }
}




