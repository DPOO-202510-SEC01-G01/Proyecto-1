package consolas;

import usuario.Cliente;
import usuario.Usuario;
import tiquetes.*;
import parque.Parque;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class ConsolaCliente {
	
    
	private static final Scanner scanner = new Scanner(System.in);
    private static final String CLIENTES_JSON = "clientes.json";
    private static Map<String, Cliente> clientes = new HashMap<>();
    private static Cliente clienteActual;

    public static void main(String[] args) {
        cargarClientes();

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
                    guardarClientes();
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
        guardarClientes();
        System.out.println("Registro exitoso.");
    }

    private static boolean autenticarCliente() {
        System.out.print("Usuario: ");
        String userName = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        Cliente cliente = clientes.get(userName);
        if (cliente != null && cliente.autenticar(password)) {
            clienteActual = cliente;
            System.out.println("Inicio de sesión exitoso.");
            return true;
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
            return false;
        }
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

        Tiquete tiquete = new General(LocalDate.now(), precio, tipo, nivel);
        clienteActual.comprarTiquete(tiquete);
        guardarClientes();
    }

    private static void comprarTiqueteIndividual() {
        System.out.print("Nombre de la atracción: ");
        String atraccion = scanner.nextLine();
        System.out.print("Precio: ");
        int precio = scanner.nextInt(); scanner.nextLine();

        Tiquete tiquete = new Individual(new Date(), precio, atraccion);
        clienteActual.comprarTiquete(tiquete);
        guardarClientes();
    }

    private static void comprarFastpass() {
        System.out.print("Fecha de uso (AAAA-MM-DD): ");
        String fechaUso = scanner.nextLine();
        System.out.print("Precio: ");
        int precio = scanner.nextInt(); scanner.nextLine();

        Date fecha = java.sql.Date.valueOf(fechaUso);
        Tiquete tiquete = new Fastpass(new Date(), precio, fecha);
        clienteActual.comprarTiquete(tiquete);
        guardarClientes();
    }

    private static void comprarTemporada() {
        System.out.print("Fecha inicio (AAAA-MM-DD): ");
        Date inicio = java.sql.Date.valueOf(scanner.nextLine());
        System.out.print("Fecha fin (AAAA-MM-DD): ");
        Date fin = java.sql.Date.valueOf(scanner.nextLine());
        System.out.print("Categoría (Familiar, Oro, Diamante): ");
        String categoria = scanner.nextLine();
        System.out.print("Precio: ");
        int precio = scanner.nextInt(); scanner.nextLine();

        Tiquete tiquete = new Temporada(new Date(), precio, inicio, fin, categoria);
        clienteActual.comprarTiquete(tiquete);
        guardarClientes();
    }

    private static void verTiquetes() {
        System.out.println("\n--- Mis Tiquetes ---");
        clienteActual.getTiquetes().forEach(System.out::println);
    }

    private static void cargarClientes() {
        try (Reader reader = new FileReader(CLIENTES_JSON)) {
            clientes = gson.fromJson(reader, new TypeToken<Map<String, Cliente>>(){}.getType());
        } catch (IOException e) {
            System.out.println("No se encontraron clientes previos.");
        }
    }

    private static void guardarClientes() {
        try (Writer writer = new FileWriter(CLIENTES_JSON)) {
            gson.toJson(clientes, writer);
        } catch (IOException e) {
            System.out.println("Error guardando los datos de clientes.");
        }
    }
}