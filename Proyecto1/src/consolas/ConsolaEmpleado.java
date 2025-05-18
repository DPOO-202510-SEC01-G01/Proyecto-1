package consolas;

import java.util.Scanner;
import usuario.Empleado;
import atracciones.Turno;

import java.time.Duration;

public class ConsolaEmpleado {

    private Scanner scanner;
    private Empleado empleado;

    public ConsolaEmpleado() {
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("=== Consola de Empleado ===");
        crearEmpleado();

        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer
            switch (opcion) {
                case 1:
                    mostrarInformacionEmpleado();
                    break;
                case 2:
                    agregarCapacitacion();
                    break;
                case 3:
                    agregarTurnoDesdeConsola();
                    break;
                case 4:
                    eliminarUltimoTurno();
                    break;
                case 5:
                    listarTurnos();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void crearEmpleado() {
        System.out.print("Ingrese nombre de usuario: ");
        String user = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String pass = scanner.nextLine();
        System.out.print("Ingrese correo: ");
        String correo = scanner.nextLine();
        System.out.print("Ingrese rol: ");
        String rol = scanner.nextLine();

        empleado = new Empleado(user, pass, correo, rol);
        System.out.println("Empleado creado exitosamente.\n");
    }

    private void mostrarMenu() {
        System.out.println("\n--- Menú ---");
        System.out.println("1. Mostrar información del empleado");
        System.out.println("2. Agregar capacitación");
        System.out.println("3. Agregar turno");
        System.out.println("4. Eliminar último turno");
        System.out.println("5. Listar turnos");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void mostrarInformacionEmpleado() {
        System.out.println("\n" + empleado);
        System.out.println("Capacitaciones: " + empleado.getCapacitaciones());
    }

    private void agregarCapacitacion() {
        System.out.print("Ingrese nombre de la capacitación: ");
        String cap = scanner.nextLine();
        empleado.addCapacitaciones(cap);
        System.out.println("Capacitación agregada.");
    }

    private void agregarTurnoDesdeConsola() {
        System.out.print("Ingrese hora de inicio (HH:mm): ");
        String inicio = scanner.nextLine();

        System.out.print("Ingrese hora de fin (HH:mm): ");
        String fin = scanner.nextLine();

        try {
            Turno turno = new Turno(inicio, fin);
            empleado.agregarTurno(turno);
            System.out.println("Turno agregado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear el turno: " + e.getMessage());
        }
    }

    private void eliminarUltimoTurno() {
        var turnos = empleado.getTurnos();
        if (!turnos.isEmpty()) {
            Turno ultimo = turnos.get(turnos.size() - 1);
            if (empleado.eliminarTurno(ultimo)) {
                System.out.println("Último turno eliminado.");
            } else {
                System.out.println("No se pudo eliminar el turno.");
            }
        } else {
            System.out.println("No hay turnos asignados.");
        }
    }

    private void listarTurnos() {
        var turnos = empleado.getTurnos();
        if (turnos.isEmpty()) {
            System.out.println("No hay turnos asignados.");
            return;
        }

        System.out.println("Turnos asignados:");
        for (Turno t : turnos) {
            Duration d = t.getDuracion();
            long horas = d.toHours();
            long minutos = d.toMinutesPart();
            System.out.println("- Desde " + t.getHoraInicio() + " hasta " + t.getHoraFin() +
                               " (Duración: " + horas + "h " + minutos + "min)");
        }
    }

    public static void main(String[] args) {
        new ConsolaEmpleado().iniciar();
    }
}


