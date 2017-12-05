package comercio.regulador;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import comercio.comun.Oferta;
import comercio.comun.ServicioMercanciasInterface;

public class ServicioMercanciasImp extends UnicastRemoteObject implements ServicioMercanciasInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7733485019300670138L;
	
	private static Map<Integer, Oferta> ofertas = new HashMap<Integer,Oferta>();
	private static int idOferta=0;
	
	protected ServicioMercanciasImp() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void nuevaOferta (int tipo, float precio, int kilo,
			String distribuidor) {
			Oferta oferta = new Oferta(idOferta, tipo, precio, kilo, distribuidor);
			ofertas.put(idOferta, oferta);
			
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nuevaDemanda(String tipo) {
		// TODO Auto-generated method stub
		
	}
	
	public Map <Integer,Oferta> getOfertas () {
		return ServicioMercanciasImp.ofertas;
	}

}
