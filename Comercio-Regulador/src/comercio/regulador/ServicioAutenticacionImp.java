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
	private Map <String,Usuario> usuarios = new HashMap <String,Usuario>();
	private static int numSesion=-1;
	
	protected ServicioAutenticacionImp() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean Alta(String nombre, String pass) throws RemoteException {
		Usuario nuevo;

		if (usuarios.containsKey(nombre)){
			System.out.println("Ya existe un usuario autenticado en esta sesión");
			System.out.println("Si desea registrar un nuevo usuario inicie una nueva sesión");
			return false;
		
		}
		else {
			
			nuevo = new Usuario(nombre,pass);
			System.out.println();
			usuarios.put(nombre,nuevo);
			
		
		
			return true;	
			
		}
			
			
	}

	@Override
	public int login(String nombre, String pass) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

}
