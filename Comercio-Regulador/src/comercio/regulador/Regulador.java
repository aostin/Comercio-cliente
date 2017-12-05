package comercio.regulador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import comercio.comun.Codebase;
import comercio.comun.Oferta;
import comercio.comun.PoliticaSeguridad;
import comercio.comun.ServicioAutenticacionInterface;
import comercio.comun.Usuario;

import java.io.Console;


public class Regulador {
	private static Console console = System.console();
	private static BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in));
	private static ServicioAutenticacionImp servicioAutenticacion;	
	private static ServicioMercanciasImp servicioMercancias;
	private static String[] tiposOferta =new String[]	{
		"Tomates", "Limones","Naranjas","Fresas", "Plátanos", "Melones", "Sandías"};
		public static void main(String[] args) throws Exception {		
			
			// Gestor de seguridad - no es necesario en este ejemplo, 
			// pero lo incluiremos con carater did�ctico
			System.setProperty("java.security.policy", 
					PoliticaSeguridad.getLocationOfPolicyFile());
	        if(System.getSecurityManager() == null) {
	            System.setSecurityManager(new SecurityManager());
	        }	
			// java.rmi.server.codebase - no es necesario en este ejemplo,
	        // pero lo incluiremos con carater did�ctico
			Codebase.setCodeBase(ServicioAutenticacionInterface.class);
			//Arrancamos el enlazador en el servidor. Tambi�n podriamos hacerlo desde consola:
			// path_bin_proyecto_comun/rmiregistry 8888
			LocateRegistry.createRegistry(8888);		
			servicioAutenticacion = new ServicioAutenticacionImp();	
			servicioMercancias = new ServicioMercanciasImp();
			Naming.bind("rmi://127.0.1.1:8888/autentica",servicioAutenticacion);
			Naming.bind("rmi://127.0.1.1:8888/mercancias",servicioMercancias);

			
			System.out.println("Servicio de autenticación preparado");
			System.out.println("Servicio de mercancías preparado");
			menu();
			
			
}
		private static void menu () throws RemoteException, MalformedURLException, NotBoundException  {
			
			String opt = "";			
			do {
			    System.out.println("Elija la operacion:");
			    System.out.println("1.- Listar ofertas actuales");
			    System.out.println("2.- Listar demandas actuales");
			    System.out.println("3.- Listar clientes");
			    System.out.println("4.- Listar distribuidores");
			    System.out.println("5.- Salir");


			    
			    opt = leerConsola();
				switch (opt) {
					case "1":listarOfertas() ; break;
					case "2": ;  break;		
					case "3": listarUsuarios(false);;  break;	
					case "4": listarUsuarios(true);  break;	
					case "5": salir();  break;	
				}
			}
			while (!opt.equals("5"));
			
			
		}
		
		private static void salir() throws RemoteException, MalformedURLException, NotBoundException {
			UnicastRemoteObject.unexportObject(servicioAutenticacion, true);
			UnicastRemoteObject.unexportObject(servicioMercancias,true);
		}
		
		private static void listarUsuarios(boolean esDistribuidor) {
			List<Usuario> registrados = servicioAutenticacion.getRegistrados();
			for (Usuario user: registrados) {
				if (user.getEsDistribuidor()==esDistribuidor)
					System.out.println(user.getNombre());
			}
		}
		
		private static void listarOfertas () {
			Map<Integer,Oferta> ofertas = servicioMercancias.getOfertas();
			System.out.println("Id\tDistribuidor\tTipo\tPrecio\tKgs");
			for (Map.Entry<Integer, Oferta> oferta : ofertas.entrySet()) {
				System.out.println(oferta.getValue().getIdoferta() + "\t" + oferta.getValue().getDistribuidor() + "\t" + tiposOferta[(oferta.getValue().getTipo())] + "\t" + oferta.getValue().getPrecio() + "\t" + oferta.getValue().getKgs() );
				
			}
		}

		private static String leerConsola() {	
			if (console != null) return console.readLine();
			try {
				return reader.readLine();
			} 
			catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
			
		}
	
