package comercio.regulador;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import comercio.comun.ServicioAutenticacionInterface;
import comercio.comun.Usuario;



public class ServicioAutenticacionImp extends UnicastRemoteObject implements ServicioAutenticacionInterface{
		
	private static final long serialVersionUID = 1L;
	private Map <Integer,Usuario> usuarios = new HashMap <Integer,Usuario>();
	private static int numUsuario =0;
	
	protected ServicioAutenticacionImp() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int Alta(String nombre, String pass) throws RemoteException {
		Usuario nuevo;
		if (usuarios.containsValue(nombre)) {
			System.out.println("Usuario ya registrado");
			return 0;
		
		}
		else {
			
			nuevo = new Usuario(nombre,pass);
			numUsuario+=1;
			nuevo.setNombre(nombre);
			usuarios.put(numUsuario,nuevo );
		
		
			return numUsuario;	
			
		}
			
			
	}

	@Override
	public int login(String nombre, String pass) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

}
