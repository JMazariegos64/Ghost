
package swingmain;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class Celda extends JButton{
	
	String tipo;
	boolean bueno;
	int fila;
	int columna;
	
	boolean lista;
	public Celda() {
		
	}
	
	public Celda(String tipo, int fila, int columna) {
           
		setTipo(tipo);
		this.fila = fila;
		this.columna = columna;
		
		setBorder(new LineBorder(Color.BLACK, 1, false));
//		if((int)(Math.random()*(3-1+1)+1) == 1){
//			setBueno(true);
//		}
	}
	
	
	public boolean getLista() {
		return lista;
	}
	
	public void setLista(boolean lista) {
		this.lista = lista;
	}
	
	public int getFila() {
		return fila;
	}
	
	public int getColumna() {
		return columna;
	}
	
	public Celda(int x, int y, int ancho, int alto, String tipo) {
		setTipo(tipo);
		setBounds(x, y, ancho, alto);
		setBorder(new LineBorder(Color.BLACK, 1, false));
//		if((int)(Math.random()*(3-1+1)+1) == 1){
//			setBueno(true);
//		}
	}
	
	public boolean getBueno() {
		return bueno;
	}
	
	public void setBueno(boolean bueno) {
		this.bueno = bueno;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
		setText(tipo);
	}
}
