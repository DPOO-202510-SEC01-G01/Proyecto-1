package usuarios.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tiquetes.General;
import tiquetes.Tiquete;
import usuario.Cliente;

public class ClienteTest {
	private Tiquete tiquete;
	private Cliente cliente;
	@BeforeEach
	void setUp() {
		tiquete = new General("2024-01-01", 1000, "Diamante", 3);
	}
	@Test
	void agregarTiqueteTest() {
		cliente.agregarTiquete(tiquete);
		assertTrue(cliente.getTiquetes().contains(tiquete));
	}
	@Test
	void comprarTiqueteTest() {
		cliente.comprarTiquete(tiquete);
		assertTrue(cliente.getTiquetes().contains(tiquete));
	}
	@Test
	void getTiquetesTest() {
		cliente.agregarTiquete(tiquete);
		assertTrue(cliente.getTiquetes().contains(tiquete));
	}
}
