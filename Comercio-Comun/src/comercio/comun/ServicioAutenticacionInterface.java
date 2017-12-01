package comercio.comun;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServicioAutenticacionInterface extends Remote {
	
	boolean Alta (String nombre, String pass) throws RemoteException;
	int login (String nombre, String pass) throws RemoteException;

}
