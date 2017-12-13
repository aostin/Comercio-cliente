package comercio.comun;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface ServicioMercanciasInterface extends Remote {
	void nuevaOferta(int tipo, float precio, int kilo, String distribuidor) throws RemoteException;
	void nuevaDemanda(String nombre,int tipo) throws RemoteException;
	void quitarOferta(int idOferta) throws RemoteException;
	Map <Integer,Oferta> getOfertas() throws RemoteException;
	String[] getTiposOferta() throws RemoteException;

}
