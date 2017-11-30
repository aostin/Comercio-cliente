package comercio.cliente;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import comercio.comun.PoliticaSeguridad;
import comercio.comun.ServicioAutenticacionInterface;
public class Cliente {

	private static ServicioAutenticacionInterface IAutentica;
	private static Console console = System.console();
	private static BufferedReader reader = new BufferedReader(
											new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		// Gestor de seguridad
		System.setProperty("java.security.policy", PoliticaSeguridad.getLocationOfPolicyFile());
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }	
		
		IAutentica = (ServicioAutenticacionInterface)Naming.lookup("rmi://127.0.1.1:8888/autentica");
		
		String opt = "";			
		do {
		    System.out.println("Elija la operacion:");
		    System.out.println("1.- Registrar un nuevo usuario");
		    System.out.println("2.- Autenticarse en el sistema(hacer login)");
		    System.out.println("3 - Salir");
		    
		    opt = leerConsola();
			switch (opt) {
				case "1": registrar(); break;
				case "2": ;  break;					
			}
		}
		while (!opt.equals("3"));					
	}
	private static void registrar() throws RemoteException  {
		String nombre, pass;
		System.out.println("Se va a registrar en el sistema");
		System.out.println("Escriba un nombre de usuario y pulse enter:");
		nombre = leerConsola();
		System.out.println("Escriba su contraseña");
		pass=leerConsola();
		System.out.println(IAutentica.Alta(nombre, pass));
		
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
