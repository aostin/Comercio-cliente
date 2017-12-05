package comercio.cliente;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import comercio.comun.PoliticaSeguridad;
import comercio.comun.ServicioAutenticacionInterface;
public class Cliente {

	private static ServicioAutenticacionInterface IAutentica;
	private static Console console = System.console();
	private static String nombre;
	private static String pass;
	private static int numSesion;
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
				case "2": login();  break;					
			}
		}
		while (!opt.equals("3"));					
	}
	private static void registrar() throws RemoteException  {
		System.out.println("Se va a registrar en el sistema");
		System.out.println("Escriba un nombre de usuario y pulse enter:");
		nombre = leerConsola();
		System.out.println("Escriba su contraseña");
		pass=leerConsola();
	
	 if (IAutentica.Alta(nombre, pass,false) == false){
		 
			System.out.println("Ya existe un registrado con ese nombre");
	 }
	 else
		 System.out.println("Usuario registrado correctamente");
		
	}
	
	private static void login() throws RemoteException {
		System.out.println("Se va autenticar como usuario");
		System.out.println("Escriba su nombre de usuario");
		nombre=leerConsola();
		System.out.println("Escriba su contraseña");
		pass=leerConsola();
		numSesion = IAutentica.login(nombre, pass);
		
		if (numSesion!=-1) {
			System.out.println("Se ha autenticado correctamente");
			segundoMenu();
			}
		else
			System.out.println("Error en el usuario o contraseña");
			
		}
		
	private static void segundoMenu() throws RemoteException {
		String opt="";
		
		do {
		
		System.out.println("1.- Introducir  demanda");
		System.out.println("2.- Recibir oferta");
		System.out.println("3.- Comprar mercancía");
		System.out.println("4.- Darse de baja");
		System.out.println("5.- Salir");
		opt = leerConsola();

			switch (opt) {
				case "1":; break;
				case "2": ;  break;	
				case "3":; break;
				case "4": IAutentica.baja(nombre, pass, numSesion);  break;
				case "5": break;
	}
		}
		
		while (!opt.equals("5"));

		
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
