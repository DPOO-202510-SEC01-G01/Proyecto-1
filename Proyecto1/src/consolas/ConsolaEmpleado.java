package consolas;
import java.util.Scanner;
import parque.Parque;
import persistencia.CargadorUsuarios;
import persistencia.GuardadorUsuarios;
import usuario.Empleado;
import usuario.Usuario;

public class ConsolaEmpleado {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parque parque = new Parque();


        CargadorUsuarios.cargarUsuarios("data/usuarios.json", parque);

        System.out.println("=== Sistema de Empleados ===");
        System.out.print("Usuario: ");
        String username = scanner.nextLine();

        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        Usuario usuario = parque.iniciarSesion(username, password);

        if (usuario == null || !(usuario instanceof Empleado)) {
            System.out.println("Acceso denegado. Solo empleados pueden acceder a esta consola.");
        }

        Empleado empleado = (Empleado) usuario;
        System.out.println("Bienvenido, " + empleado.getUserName() + ". Rol: " + empleado.getRol());

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- Menú Empleado ---");
            System.out.println("1. Ver capacitaciones");
            System.out.println("2. Agregar capacitación");
            System.out.println("3. Ver turnos asignados");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("Capacitaciones:");
                    if (empleado.getCapacitaciones() != null) {
                        for (String cap : empleado.getCapacitaciones()) {
                            System.out.println("- " + cap);
                        }
                    } else {
                        System.out.println("No hay capacitaciones registradas.");
                    }
                    break;
                case "2":
                    System.out.print("Ingrese nueva capacitación: ");
                    String nuevaCap = scanner.nextLine();
                    empleado.addCapacitaciones(nuevaCap);
                    System.out.println("Capacitación agregada.");
                    break;
                case "3":
                    System.out.println("Turnos:");
                    if (empleado.getTurnos().isEmpty()) {
                        System.out.println("No hay turnos asignados.");
                    } else {
                        empleado.getTurnos().forEach(turno -> System.out.println(turno));
                    }
                    break;
                case "4":
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }

        // Guardar usuarios antes de salir
        GuardadorUsuarios.guardarUsuarios("data/usuarios.json", parque);
        System.out.println("Datos guardados. Cerrando sesión...");
        scanner.close();
    }
}




