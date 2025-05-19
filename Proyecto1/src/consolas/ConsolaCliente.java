package consolas;

import usuario.Cliente;
import usuario.Empleado;
import usuario.Usuario;
import tiquetes.*;
import parque.Parque;
import persistencia.CargadorUsuarios;
import persistencia.GuardadorUsuarios;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;


public class ConsolaCliente {
	
    
	private static final Scanner scanner = new Scanner(System.in);
    private Parque parque;
    
    private static Cliente clienteActual;

    public static void main(String[] args) {
    	Parque parque = new Parque();
    	
    	 CargadorUsuarios.cargarUsuarios(parque);

        while (true) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            int opcion = scanner.nextInt(); scanner.nextLine();

            switch (opcion) {
                case 1 -> registrarCliente();
                case 2 -> {
                    if (autenticarCliente()) {
                        menuCliente();
                    }
                }
                case 3 -> {
                	 GuardadorUsuarios.guardarUsuarios(parque);
                    System.out.println("Gracias por usar el sistema.");
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void registrarCliente() {
        System.out.print("Nombre de usuario: ");
        String userName = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();

        if (clientes.containsKey(userName)) {
            System.out.println("Ese usuario ya existe.");
            return;
        }

        Cliente cliente = new Cliente(userName, password, correo);
        clientes.put(userName, cliente);
        System.out.println("Registro exitoso.");
    }

    private static boolean autenticarCliente(Parque parque) {
        System.out.print("Usuario: ");
        String userName = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        Usuario usuario = parque.iniciarSesion(userName, password);

        if (usuario == null || !(usuario instanceof Empleado)) {
            System.out.println("Acceso denegado. Solo empleados pueden acceder a esta consola.");
            scanner.close();
            return;
    }

    private static void menuCliente() {
        while (true) {
            System.out.println("\n--- Menú Cliente ---");
            System.out.println("1. Comprar tiquete general");
            System.out.println("2. Comprar tiquete individual");
            System.out.println("3. Comprar fastpass");
            System.out.println("4. Comprar tiquete de temporada");
            System.out.println("5. Ver mis tiquetes");
            System.out.println("6. Cerrar sesión");
            int opcion = scanner.nextInt(); scanner.nextLine();

            switch (opcion) {
                case 1 -> comprarTiqueteGeneral();
                case 2 -> comprarTiqueteIndividual();
                case 3 -> comprarFastpass();
                case 4 -> comprarTemporada();
                case 5 -> verTiquetes();
                case 6 -> {
                    clienteActual = null;
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void comprarTiqueteGeneral() {
        System.out.print("Tipo (Básico, Familiar, Oro, Diamante): ");
        String tipo = scanner.nextLine();
        System.out.print("Nivel de exclusividad (0 a 3): ");
        int nivel = scanner.nextInt(); scanner.nextLine();
        System.out.print("Precio: ");
        int precio = scanner.nextInt(); scanner.nextLine();

        Tiquete tiquete = new General(new Date(), precio, tipo, nivel);
        clienteActual.comprarTiquete(tiquete);
        
    }

    private static void comprarTiqueteIndividual() {
        System.out.print("Nombre de la atracción: ");
        String atraccion = scanner.nextLine();
        System.out.print("Precio: ");
        int precio = scanner.nextInt(); scanner.nextLine();

        Tiquete tiquete = new Individual(new Date(), precio, atraccion);
        clienteActual.comprarTiquete(tiquete);
       
    }

    private static void comprarFastpass() {
        System.out.print("Fecha de uso (AAAA-MM-DD): ");
        String fechaUso = scanner.nextLine();
        System.out.print("Precio: ");
        int precio = scanner.nextInt(); scanner.nextLine();

        // Formateador para parsear la fecha ingresada
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = formatter.parse(fechaUso);
        } catch (ParseException e) {
            System.out.println("Fecha inválida. Usa el formato AAAA-MM-DD.");
            return;
        }

        // Crear el tiquete con la fecha de compra actual y la fecha de uso
        Tiquete tiquete = new Fastpass(new Date(), precio, fecha);
        clienteActual.comprarTiquete(tiquete);
       
    }

    private static void comprarTemporada() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.print("Fecha inicio (AAAA-MM-DD): ");
        Date inicio = null;
        try {
            inicio = formatter.parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Fecha de inicio inválida. Usa el formato AAAA-MM-DD.");
            return;
        }
        System.out.print("Fecha fin (AAAA-MM-DD): ");
        Date fin = null;
        try {
            fin = formatter.parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Fecha de fin inválida. Usa el formato AAAA-MM-DD.");
            return;
        }
        System.out.print("Categoría (Familiar, Oro, Diamante): ");
        String categoria = scanner.nextLine();
        System.out.print("Precio: ");
        int precio = scanner.nextInt(); scanner.nextLine();

        Tiquete tiquete = new Temporada(new Date(), precio, inicio, fin, categoria);
        clienteActual.comprarTiquete(tiquete);
        
    }

    private static void verTiquetes() {
        System.out.println("\n--- Mis Tiquetes ---");
        clienteActual.getTiquetes().forEach(System.out::println);
    }

}
    