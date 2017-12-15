package comercio.distribuidor;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import comercio.comun.Oferta;
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
	private static List<Oferta> misOfertas = new ArrayList<Oferta>();
	private static List<Oferta> misVentas = new ArrayList<Oferta>();
	
	
	
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
				case "2":quitarOferta() ;  break;	
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
		Oferta nuevaOferta;
		for (String tipoMer : IMercancias.getTiposOferta() ) {
			System.out.println(i +".- " + tipoMer);
			i+=1;
			
		}
		System.out.println("Introduzca el número del tipo de mercancía que quiere ofertar");
		tipo =Integer.parseInt(leerConsola());
		System.out.println("Introduzca el precio de la mercancía que va a ofertar:");
		precio=Float.parseFloat(leerConsola());
		System.out.println("Introduzca los el peso(kgs) de la mercancía");
		kgs=Integer.parseInt(leerConsola());
		nuevaOferta= new Oferta(IMercancias.getIdOferta(),tipo,precio,kgs,nombre);
		IMercancias.nuevaOferta(nuevaOferta);
		misOfertas.add(nuevaOferta);
		System.out.print("Mercancía introducida correctamente");
		
		
	}
	
	private static void quitarOferta() throws RemoteException  {
		int idOferta;
		Map<Integer,Oferta> ofertas = IMercancias.getOfertas();
		String[] tiposMercancias = IMercancias.getTiposOferta();
		System.out.println("Estas son las ofertas que ha introducido:");
		System.out.println("Id\tTipo\tPrecio\tKgs");
		for (Map.Entry<Integer, Oferta> oferta : ofertas.entrySet()) {
			System.out.println(oferta.getValue().getIdoferta() + "\t" + "\t" + tiposMercancias[(oferta.getValue().getTipo()-1)] + "\t" + oferta.getValue().getPrecio() + "\t" + oferta.getValue().getKgs() );
			
		}
		System.out.println("¿Qué oferta desea quitar?");
		System.out.println("Introduzca su id:");
		idOferta=Integer.parseInt(leerConsola());
		IMercancias.quitarOferta(idOferta);
		System.out.println("Oferta quitada satisfactoriamente");
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
