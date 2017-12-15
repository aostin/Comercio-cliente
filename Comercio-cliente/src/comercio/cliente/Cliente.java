package comercio.cliente;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import comercio.comun.Oferta;
import comercio.comun.PoliticaSeguridad;
import comercio.comun.ServicioAutenticacionInterface;
import comercio.comun.ServicioMercanciasInterface;
public class Cliente {

	private static ServicioAutenticacionInterface IAutentica;
	private static ServicioMercanciasInterface IMercancias;
	private static Console console = System.console();
	private static String nombre;
	private static String pass;
	private static int numSesion;
	private static List<Integer> demandas =  new ArrayList<Integer>();
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
				case "1":introducirDemanda(); break;
				case "2":recibirOferta() ;  break;	
				case "3":; break;
				case "4": IAutentica.baja(nombre, pass, numSesion);  break;
				case "5": break;
	}
		}
		
		while (!opt.equals("5"));

		
	}
	
	private static void introducirDemanda() throws RemoteException {
		int i=1;
		int tipo;
		System.out.println("Introduzca el tipo de mercancía que desee:");
		
		for (String tipoMer: IMercancias.getTiposOferta()) {
			System.out.println(i+".-" + tipoMer);
			i+=1;
		}
		System.out.println("Tipo mercancia: ");
		tipo =Integer.parseInt(leerConsola());
		demandas.add(tipo);
		IMercancias.nuevaDemanda(nombre, tipo);
		
	}
	
	private static void recibirOferta() throws RemoteException {
		Map<Integer,Oferta> ofertasRecibidas = IMercancias.getOfertas(demandas);
		String[] tiposMercancias = IMercancias.getTiposOferta();
		System.out.println("Id\tDistribuidor\tTipo\tPrecio\tKgs");
		for (Map.Entry<Integer, Oferta> oferta : ofertasRecibidas.entrySet()) {
			System.out.println(oferta.getValue().getIdoferta() + "\t" + oferta.getValue().getDistribuidor() + "\t" + tiposMercancias[(oferta.getValue().getTipo()-1)] + "\t" + oferta.getValue().getPrecio() + "\t" + oferta.getValue().getKgs() );
			
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
