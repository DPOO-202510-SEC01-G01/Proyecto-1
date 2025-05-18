package parque;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import atracciones.Atraccion;
import atracciones.Espectaculo;
import tiquetes.Tiquete;
import usuario.Usuario;

public class Parque {
	
	
	private final Map<String, Atraccion> atracciones;
    private final Map<String, Espectaculo> espectaculos;
    private final Map<String, Usuario> usuarios;
    private Map<String, Tiquete> tiquetes;
    
	public Parque(Map<String, Atraccion> atracciones, Map<String, Espectaculo> espectaculos,
			Map<String, Usuario> usuarios, Map<String, Tiquete> tiquetes) {
		super();
		this.atracciones = new HashMap<>();
		this.espectaculos = new HashMap<>();
		this.usuarios = new HashMap<>();
		this.tiquetes = new HashMap<>();
	}

	public void agregarAtraccion(Atraccion atraccion) {
        this.atracciones.put( atraccion.getNombre( ), atraccion );
    }
	public void agregarEspectaculo(Espectaculo espectaculo) {
        this.espectaculos.put( espectaculo.getNombre( ), espectaculo );
    }
	public void agregarUsuario(Usuario usuario) {
        this.usuarios.put( usuario.getUserName( ), usuario );
    }
	
	
	
	 public boolean existeAtraccion( String nombre )
	    {
	        return this.atracciones.containsKey( nombre );
	    }
	 public boolean existeEspectaculo( String nombre )
	    {
	        return this.espectaculos.containsKey( nombre );
	    }
	 public boolean existeUsuario( String userName )
	    {
	        return this.espectaculos.containsKey( userName );
	    }
	 public boolean existeTiquete( String user )  
	    {
	        return this.espectaculos.containsKey( user );
	    }
	 
	 
	 
	 public Atraccion getAtraccion( String nombre )
	    {
	        return this.atracciones.get( nombre );
	    }
	 public Espectaculo getEspectaculo( String nombre )
	    {
	        return this.espectaculos.get( nombre );
	    }
	 public Usuario getUsuario( String userNombre )
	    {
	        return this.usuarios.get( userNombre );
	    }
	 public Tiquete getTiquete( String user )
	    {
	        return this.tiquetes.get( user );
	    }
	 
	 
	 public Collection<Atraccion> getAtracciones( )
	    {
	        return atracciones.values( );
	    }
	 public Collection<Espectaculo> getEspectaculos( )
	    {
	        return espectaculos.values( );
	    }
	 public Collection<Usuario> getUsuarios( )
	    {
	        return usuarios.values( );
	    }
	 public Collection<Tiquete> getTiquetes( )
	    {
	        return tiquetes.values( );
	    }
	 
	 
	 public void eliminarAtraccion(String nombre)
	 {
		 if (atracciones.containsKey(nombre)) {
		        atracciones.remove(nombre);
		 }
	 }
	 public void eliminarEspectaculo(String nombre)
	 {
		 if (espectaculos.containsKey(nombre)) {
		        espectaculos.remove(nombre);
		 }
	 }
	 public void eliminarUsuario(String userName)
	 {
		 if (usuarios.containsKey(userName)) {
		        usuarios.remove(userName);
		 }
	 }
	 public void eliminarTiquete(String user)
	 {
		 if (tiquetes.containsKey(user)) {
		        tiquetes.remove(user);
		 }
	 }
	 
	 
	 public Usuario iniciarSesion(String userName, String password) {
	        Usuario usuario = usuarios.get(userName);
	        if (usuario == null) {
	            System.out.println("Error de autenticación: Usuario '" + userName + "' no encontrado.");
	             return null;
	        }
	        if (!usuario.autenticar(password)) {
	        	System.out.println("Error de autenticación: Contraseña incorrecta para '" + userName + "'.");
	            return null;
	        } else {
	            return usuario;
	        }
	    }
	 
}
