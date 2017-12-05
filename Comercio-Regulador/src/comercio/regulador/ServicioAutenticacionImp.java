package comercio.regulador;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import comercio.comun.ServicioAutenticacionInterface;
import comercio.comun.Usuario;



public class ServicioAutenticacionImp extends UnicastRemoteObject implements ServicioAutenticacionInterface{
		
	private static final long serialVersionUID = 1L;
	private Map <Integer,Usuario> autenticados = new HashMap <Integer,Usuario>();
	private List<Usuario> registrados = new ArrayList<Usuario>();
	private static int numSesion=-1;
	
	protected ServicioAutenticacionImp() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean Alta(String nombre, String pass, boolean esDistribuidor) throws RemoteException {
		Usuario nuevo;
		if ( buscarUsuario(nombre)!=null){
			return false;
		
		}
		else {
			
			nuevo = new Usuario(nombre,pass, esDistribuidor);
			System.out.println();
			registrados.add(nuevo);
			
		
		
			return true;	
			
		}
			
			
	}

	@Override
	public int login(String nombre, String pass) throws RemoteException {
		// TODO Auto-generated method stub
		Usuario autenticado = buscarUsuario(nombre);
		if (autenticado!=null) {
			if (autenticado.getPass().equals(pass)) {
				numSesion+=1;
				autenticados.put(numSesion, autenticado);
				return numSesion;
			}
			
			
		}
		return -1;
	}
	
	
	
	private Usuario buscarUsuario(String nombre) {
		for (Usuario user: registrados) {
			if (user.getNombre().equals(nombre)) {
				return user;
				
			}
			
		}
		return null;
		
		
	}
	
	public List<Usuario> getRegistrados() {
		return registrados;
	}

	@Override
	public void baja(String nombre, String pass, int numSesion) throws RemoteException {
		List<Usuario> nuevoRegistrados = new ArrayList<Usuario>();
		for (Usuario user : registrados) {
			if (!user.getNombre().equals(nombre)) {
				nuevoRegistrados.add(user);
			}
		}
		autenticados.remove(numSesion);
		registrados = nuevoRegistrados;
		// TODO Auto-generated method stub
		
	}

}
