package comercio.comun;

// TODO: Auto-generated Javadoc
/**
 * The Class Usuario.
 */
public class Usuario {
	
	
	/** The nombre. */
	private String nombre;
	
	/** The pass. */
	private String pass;
	
	/** The es distribuidor. */
	private boolean esDistribuidor=false;
	
	
	/**
	 * Constructor de un nuevo usuario.
	 *
	 * @param nombre el nombre de usuario
	 * @param pass su contraseña
	 */
	
	public Usuario (String nombre, String pass, boolean esDistribuidor) {
		this.nombre= nombre;
		this.pass = pass;
		this.esDistribuidor = esDistribuidor;
	}
	
	
	
	public void setNombre (String nombre) {
		this.nombre=nombre;
	}
	
	/**
	 * Getter de nombre.
	 *
	 * @return el nombre
	 */
	public String getNombre ()        {
		return this.nombre;
	}
	
	/**
	 * Setter de pass.
	 *
	 * @param pass la nueva contraseña
	 */
	public void setPass (String pass) {
		this.pass=pass;
	}
	
	/**
	 * Getter de pass.
	 *
	 * @return la contraseña
	 */
	public String getPass () {
		return this.pass;
	}
	
	/**
	 * Setter de esDistribuidor.
	 *
	 * @param esDistribuidor the new es distribuidor
	 */
	public void setEsDistribuidor (boolean esDistribuidor) {
		this.esDistribuidor = esDistribuidor;
	}
	
	/**
	 * Getter esDistribuidor.
	 *
	 * @return  esDistribuidor
	 */
	public boolean getEsDistribuidor () {
		return this.esDistribuidor;
	}
}
