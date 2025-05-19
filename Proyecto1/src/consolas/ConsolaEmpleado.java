package consolas;
import parque.Parque;
import persistencia.CargadorUsuarios;
import persistencia.GuardadorUsuarios;

public class ConsolaEmpleado {
    public static void main(String[] args) {
        Parque parque = new Parque();

        // Cargar usuarios desde archivo
        CargadorUsuarios.cargarUsuarios("data/usuarios.json", parque);

        // ... usar el sistema ...

        // Guardar usuarios al salir
        GuardadorUsuarios.guardarUsuarios("data/usuarios.json", parque);
    }
}



