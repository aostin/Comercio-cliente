package comercio.comun;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServicioMercanciasInterface extends Remote {
	void nuevaOferta(int tipo, float precio, int kilo, String distribuidor) throws RemoteException;
	void nuevaDemanda(String tipo) throws RemoteException;

}
