package comercio.regulador;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import comercio.comun.Codebase;
import comercio.comun.PoliticaSeguridad;
import comercio.comun.ServicioAutenticacionInterface;


public class Regulador {
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
			
			
			ServicioAutenticacionImp servicio = new ServicioAutenticacionImp();				
			Naming.rebind("rmi://127.0.1.1:8888/autentica", servicio);			
			System.out.println("Servicio de autenticación preparado");
			System.in.read();
			Naming.unbind("rmi://127.0.1.1:8888/autentica");
			UnicastRemoteObject.unexportObject(servicio, true);
			
}

}