package tiquetes.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tiquetes.General;

public class GeneralTest {
	General basico;
	General familiar;
	General oro;
	General diamante;
	@BeforeEach
	void setUp() {
		basico = new General(LocalDate.now(), 5, "basico", 0);
		familiar = new General(LocalDate.now(), 10, "familiar", 0);
		oro = new General(LocalDate.now(), 15, "oro", 0);
		diamante = new General(LocalDate.now(), 20, "diamante", 0);
	}
	@Test
	void getTipo() {
		assertTrue(basico.getTipo().equals("basico"));
		assertTrue(familiar.getTipo().equals("familiar"));
		assertTrue(oro.getTipo().equals("oro"));
		assertTrue(diamante.getTipo().equals("diamante"));
	}
	@Test
	void getNivel() {
		assertTrue(basico.getNivel()==0);
		assertTrue(familiar.getNivel()==1);
		assertTrue(oro.getNivel()==2);
		assertTrue(diamante.getNivel()==3);
	}
}
