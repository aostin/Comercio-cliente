package comercio.regulador;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import comercio.comun.Oferta;
import comercio.comun.ServicioMercanciasInterface;

public class ServicioMercanciasImp extends UnicastRemoteObject implements ServicioMercanciasInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7733485019300670138L;
	private static String[] tiposOferta =new String[]	{
		"Tomates", "Limones","Naranjas","Fresas", "Plátanos", "Melones", "Sandías"};
	private static Map<Integer, Oferta> ofertas = new HashMap<Integer,Oferta>();
	private static Map<String,List<Integer>> demandas = new HashMap<String,List<Integer>>();
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
			idOferta+=1;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nuevaDemanda(String nombre, int tipo) {
		List<Integer> listaTipos = new ArrayList<Integer>();
		if (demandas.containsKey(nombre))
		{
			listaTipos = demandas.get(nombre);
		}
		listaTipos.add(tipo);
		demandas.put(nombre, listaTipos);
		// TODO Auto-generated method stub
		
	}
	
	public void quitarOferta(int idOfertaQuitar) {
		ofertas.remove(idOfertaQuitar);
	}
	public Map <String,List<Integer>> getDemandas()  {
		return demandas;
	}
	
	public Map <Integer,Oferta> getOfertas () {
		return ServicioMercanciasImp.ofertas;
	}
	
	public String[] getTiposOferta() {
		return tiposOferta;
	}

}
