import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import atracciones.Turno;
import parque.Parque;
import persistencia.CargadorUsuarios;
import usuario.Admin;
import usuario.Empleado;
import usuario.Usuario;

public class ConsolaAdmin {

    private static Parque miParque; // Hacer el parque accesible a los métodos
    private static Admin adminLogueado; // Guardar la instancia del administrador logueado

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        miParque = new Parque();
        CargadorUsuarios.cargarUsuarios(miParque);
        System.out.println("Ingrese su nombre de usuario: ");
        String User = scanner.nextLine();
        System.out.println("Ingrese su contraseña: ");
        String Pass = scanner.nextLine();
        boolean auth = false;
        while (!auth) {
            Optional<Usuario> usuarioOptional = Optional.of(miParque.getUsuario(User));
            if (usuarioOptional.isPresent() && usuarioOptional.get().autenticar(Pass) && usuarioOptional.get() instanceof Admin) {
                adminLogueado = (Admin) usuarioOptional.get();
                System.out.println("Bienvenido a la Consola de Administración, " + adminLogueado.getUserName() + "!");
                auth = true;
            } else {
                System.out.println("Credenciales incorrectas o usuario no es administrador. Inténtelo de nuevo.");
                System.out.println("Ingrese su nombre de usuario: ");
                User = scanner.nextLine();
                System.out.println("Ingrese su contraseña: ");
                Pass = scanner.nextLine();
            }
        }


        while (!salir) {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    crearNuevaAtraccion(scanner);
                    break;
                case "2":
                    asignarTurnoEmpleado(scanner);
                    break;
                case "3":
                    miParque.getAtracciones(); // Usar el método listarAtracciones
                    break;
                case "4":
                    System.out.println("Saliendo de la consola de administración...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
            System.out.println(); // Espacio para legibilidad
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú Administrador ---");
        System.out.println("1. Crear Nueva Atracción");
        System.out.println("2. Asignar Turno a Empleado");
        System.out.println("3. Ver Listado de Atracciones");
        System.out.println("4. Ver Listado de Empleados y Turnos");
        System.out.println("5. Salir");
    }

    private static void crearNuevaAtraccion(Scanner scanner) {
        System.out.println("\n--- Crear Nueva Atracción ---");
        System.out.println("Seleccione el tipo de atracción:");
        System.out.println("1. Cultural");
        System.out.println("2. Mecánica");
        System.out.print("Ingrese su opción: ");
        String tipoAtraccion = scanner.nextLine();

        System.out.print("Ingrese el nombre de la atracción: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la capacidad máxima: ");
        int capacidadMax = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el número mínimo de empleados: ");
        int empleadosMin = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese la ubicación: ");
        String ubicacion = scanner.nextLine();

        if (tipoAtraccion.equals("1")) {
            System.out.print("Ingrese el nivel (entero): ");
            int nivel = Integer.parseInt(scanner.nextLine());
            System.out.print("¿Tiene temporada especial? (si/no): ");
            String tieneTemporada = scanner.nextLine().toLowerCase();
            System.out.print("Ingrese el clima recomendado: ");
            String clima = scanner.nextLine();
            System.out.print("Ingrese la edad mínima permitida: ");
            int edadMin = Integer.parseInt(scanner.nextLine());

            if (tieneTemporada.equals("si")) {
                System.out.print("Ingrese la fecha de inicio de la temporada (YYYY-MM-DD): ");
                String fechaInicioStr = scanner.nextLine();
                System.out.print("Ingrese la fecha de fin de la temporada (YYYY-MM-DD): ");
                String fechaFinStr = scanner.nextLine();
                try {
                    java.time.LocalDate fechaInicio = java.time.LocalDate.parse(fechaInicioStr);
                    java.time.LocalDate fechaFin = java.time.LocalDate.parse(fechaFinStr);
                    adminLogueado.crearNuevaAtraccionCulturalDeTemporada(nombre, capacidadMax, empleadosMin, ubicacion, nivel, fechaInicio, fechaFin, clima, edadMin);
                    System.out.println("Atracción cultural de temporada creada exitosamente.");
                } catch (DateTimeParseException e) {
                    System.out.println("Error al parsear las fechas. La atracción no se creó.");
                }
            } else {
                adminLogueado.crearNuevaAtraccionCulturalSinTemporada(nombre, capacidadMax, empleadosMin, ubicacion, nivel, null, null, clima, edadMin);
                System.out.println("Atracción cultural creada exitosamente.");
            }
        } else if (tipoAtraccion.equals("2")) {
            System.out.print("Ingrese el nivel de exclusividad (entero): ");
            int nivelExclusividad = Integer.parseInt(scanner.nextLine());
            System.out.print("Ingrese la condición climática adecuada: ");
            String condicionClimatica = scanner.nextLine();
            System.out.print("Ingrese la altura mínima requerida: ");
            double alturaMinima = Double.parseDouble(scanner.nextLine());
            System.out.print("Ingrese la altura máxima permitida (opcional, deje en blanco si no aplica): ");
            String alturaMaxStr = scanner.nextLine();
            double alturaMax = alturaMaxStr.isEmpty() ? Double.MAX_VALUE : Double.parseDouble(alturaMaxStr);
            System.out.print("Ingrese el peso máximo permitido (opcional, deje en blanco si no aplica): ");
            String pesoMaxStr = scanner.nextLine();
            int pesoMax = pesoMaxStr.isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(pesoMaxStr);
            System.out.print("Ingrese el nivel de riesgo: ");
            String nivelRiesgo = scanner.nextLine();
            System.out.print("Ingrese las restricciones (separadas por coma, ej: mareos, cardíacos): ");
            String restriccionesStr = scanner.nextLine();
            List<String> restricciones = List.of(restriccionesStr.split(","));

            adminLogueado.crearNuevaAtraccionMecanidaSinTemporada(nombre, capacidadMax, empleadosMin, ubicacion, nivelExclusividad, condicionClimatica, alturaMinima, alturaMax, alturaMinima, pesoMax, nivelRiesgo, restricciones);
            System.out.println("Atracción mecánica creada exitosamente.");
        } else {
            System.out.println("Tipo de atracción no válido.");
        }
    }

    private static void asignarTurnoEmpleado(Scanner scanner) {
        System.out.println("\n--- Asignar Turno a Empleado ---");
        System.out.print("Ingrese el nombre de usuario del empleado: ");
        String nombreUsuarioEmpleado = scanner.nextLine();

        Optional<Usuario> empleadoOptional = Optional.of(miParque.getUsuario(nombreUsuarioEmpleado));
        if (empleadoOptional.isPresent() && empleadoOptional.get() instanceof Empleado) {
            Empleado empleado = (Empleado) empleadoOptional.get();
            System.out.print("Ingrese la hora del inicio del turno (HH:MM): ");
            String horaInicioStr = scanner.nextLine();
            System.out.print("Ingrese la hora del fin del turno (HH:MM): ");
            String horaFinStr = scanner.nextLine();

            try {
                Turno nuevoTurno = new Turno(horaInicioStr, horaFinStr);
                adminLogueado.asignarTurnoAEmpleado(empleado, nuevoTurno);
                System.out.println("Turno asignado exitosamente a " + empleado.getUserName() + ".");
            } catch (DateTimeParseException e) {
                System.out.println("Error al parsear la fecha y hora. El turno no se asignó.");
            }
        } else {
            System.out.println("No se encontró un empleado con el nombre de usuario proporcionado.");
        }
    }
}