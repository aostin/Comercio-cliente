package comercio.comun;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface ServicioMercanciasInterface extends Remote {
	void nuevaOferta(Oferta nuevaOferta) throws RemoteException;
	void nuevaDemanda(String nombre,int tipo) throws RemoteException;
	void quitarOferta(int idOferta) throws RemoteException;
	Map <Integer,Oferta> getOfertas() throws RemoteException;
	String[] getTiposOferta() throws RemoteException;
	int getIdOferta() throws RemoteException;
	Map<Integer, Oferta> getOfertas(List<Integer> demandas) throws RemoteException;

}
