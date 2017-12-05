package comercio.distribuidor;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;

import comercio.comun.PoliticaSeguridad;
import comercio.comun.ServicioAutenticacionInterface;
import comercio.comun.ServicioMercanciasInterface;

public class distribuidor {
	private static ServicioAutenticacionInterface IAutentica;
	private static ServicioMercanciasInterface IMercancias;
	private static Console console = System.console();
	private static String nombre;
	private static String pass;
	private static int numSesion;
	private enum tiposOferta	{
		Tomates, Limones,Naranjas,Fresas, Plátanos, Melones, Sandías
	}
	
	private static BufferedReader reader = new BufferedReader(
											new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		// Gestor de seguridad
		System.setProperty("java.security.policy", PoliticaSeguridad.getLocationOfPolicyFile());
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }	
		
		IAutentica = (ServicioAutenticacionInterface)Naming.lookup("rmi://127.0.1.1:8888/autentica");
		IMercancias = (ServicioMercanciasInterface)Naming.lookup("rmi://127.0.1.1:8888/mercancias");
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
	
	 if (IAutentica.Alta(nombre, pass, true) == false){
		 
			System.out.println("Ya existe un registrado con ese nombre");
	 }
	 else
		 System.out.println("Usuario registrado correctamente");
		
	}
	
	private static void login() throws RemoteException {
		System.out.println("Se va autenticar como distribuidor");
		System.out.println("Escriba su nombre de distribuidor");
		nombre=leerConsola();
		System.out.println("Escriba su contraseña");
		pass=leerConsola();
		numSesion = IAutentica.login(nombre,pass);
		if (numSesion!=-1) {
			System.out.println("Se has autenticado correctamente");
			segundoMenu();
			}
		else
			System.out.println("Error en el usuario o contraseña");
			
		}
		
	
	private static void segundoMenu() throws RemoteException {
		String opt="";
		
		do {
		
		System.out.println("1.- Introducir  oferta");
		System.out.println("2.- Quitar oferta");
		System.out.println("3.- Mostrar ventas");
		System.out.println("4.- Darse de baja");
		System.out.println("5.- Salir");
		opt = leerConsola();

			switch (opt) {
				case "1":nuevaOferta(); break;
				case "2": ;  break;	
				case "3":; break;
				case "4": IAutentica.baja(nombre, pass, numSesion);  break;
				case "5": break;
	}
		}
		
		while (!opt.equals("5"));

		
	}
	
	private static void nuevaOferta() throws RemoteException {
		int tipo;
		float precio;
		int kgs;
		int i=1;
		for (tiposOferta tipos : tiposOferta.values()) {
			System.out.println(i +".- " + tipos);
			i+=1;
			
		}
		System.out.println("Introduzca el número del tipo de mercancía que quiere ofertar");
		tipo =Integer.parseInt(leerConsola());
		System.out.println("Introduzca el precio de la mercancía que va a ofertar:");
		precio=Float.parseFloat(leerConsola());
		System.out.println("Introduzca los el peso(kgs) de la mercancía");
		kgs=Integer.parseInt(leerConsola());
		IMercancias.nuevaOferta(tipo, precio, kgs, nombre);
		System.out.print("Mercancía introducida correctamente");
		
		
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
