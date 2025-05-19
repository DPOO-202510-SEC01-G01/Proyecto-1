import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.Scanner;

import atracciones.Atraccion;
import atracciones.AtraccionCultural;
import atracciones.AtraccionMecanica;
import atracciones.Turno;
import parque.Parque;
import persistencia.CargadorUsuarios;
import usuario.Admin;
import usuario.Empleado;

public class ConsolaAdmin {

    public static void main(String[] args) {
    	
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        Parque parque = new Parque();
        CargadorUsuarios.cargarUsuarios(parque);
        System.out.println("Ingrese su nombre de usuario: ");
        String User = scanner.nextLine();
        System.out.println("Ingrese su contraseña: ");
        String Pass = scanner.nextLine();
        boolean auth = false;
        while (!auth) {
        	System.out.println("Contraseña incorrecta para el usuario " + User + " intentelo de nuevo");
        	Pass = scanner.nextLine();
	        if (parque.getUsuario(User).autenticar(Pass)) {
	        	Admin admin = (Admin) parque.getUsuario(User);
	        	System.out.println("Bienvenido a la Consola de Administración, " + admin.getUserName() + "!");
	        	auth = true;
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
                    miParque.listarAtracciones();
                    break;
                case "4":
                    miParque.listarEmpleados();
                    break;
                case "5":
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

