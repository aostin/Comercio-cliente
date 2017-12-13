package comercio.comun;

import java.io.Serializable;

public class Oferta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2787239736751372269L;
	private int tipo;
	private float precio;
	private int kgs;
	private String distribuidor;
	private int idOferta;
	
	
	public Oferta (int idOferta,int tipo, float precio, int kgs, String distribuidor) {
		this.idOferta = idOferta;
		this.tipo = tipo;
		this.precio= precio;
		this.kgs = kgs;
		this.distribuidor =distribuidor;
	}
	
	public void setIdOfeta(int idOferta) {
		this.idOferta = idOferta;
	}
	
	public int getIdoferta() {
		return this.idOferta;
	}
	public void setTipo (int tipo)  {
		this.tipo = tipo;
	}
	
	public int getTipo ()  {
		return this.tipo;
	}
	
	public void setPrecio (float precio) {
		this.precio = precio;
	}
	
	public float getPrecio () {
		return this.precio;
	}
	
	public void setKgs (int kgs) {
		this.kgs=kgs;
		
	}
	
	public int getKgs() { 
		return this.kgs;	
		
	}
	
	public void setDistribuidor (String distribuidor)  {
		this.distribuidor = distribuidor;
	}
	
	public String getDistribuidor () {
		return this.distribuidor;
	}
	
	
}
