package comercio.comun;

public class Usuario {
	private String nombre;
	private String pass;
	private boolean esDistribuidor=false;
	
	
	public Usuario (String nombre, String pass) {
		this.nombre= nombre;
		this.pass = pass;
	}
	public void setNombre (String nombre) {
		this.nombre=nombre;
	}
	
	public String getNombre ()        {
		return this.nombre;
	}
	
	public void setPass (String pass) {
		this.pass=pass;
	}
	
	public String getPass () {
		return this.pass;
	}
	
	public void setEsDistribuidor (boolean esDistribuidor) {
		this.esDistribuidor = esDistribuidor;
	}
	
	public boolean getEsDistribuidor () {
		return this.esDistribuidor;
	}
}
