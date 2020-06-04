import java.io.*;
import java.util.*;

public class TorresHanoiAutomaticas {
	private static int numfichas,posicionanterior,nummovimiento,virtualengthb1,virtualengthb2,virtualengthb3;
	private static int[] base1, base2, base3;
	private static Torre torre1,torre2,torre3;
	private static boolean nofin,movimientopar,numfichaspar;	
	private static Hashtable htabtorres= new Hashtable();		
	private static String posicionfichamenor,posicionfichamediana,posicionobligada;	
	private static String[] nombresbases=new String[3];		
	private static boolean manual=false;/*Cambiar para jugar personalmente*/	
	private static int tope =10;/*Define el número máximo de nivel*/
	
	public static void main(String[] args)
	{
		nofin=true;
		numfichas=3;				
		jugar();
	}
	private static void jugar() {
		System.out.println("TORRES DE HANOI");
		while(nofin){;			
			nuevoNivel();
			jugarNivel();
			numfichas++;
			numfichaspar=!numfichaspar;
			if (numfichas==tope){nofin=false;};
		}		
	} 
	private static void iniciarBases() {		
		movimientopar=true;
		posicionanterior=0;
		posicionfichamenor="A";
		posicionfichamediana="A";
		posicionobligada="";		
		nombresbases[0]="A";
		nombresbases[1]="B";
		nombresbases[2]="C";		
		virtualengthb1=numfichas;
		virtualengthb2=0;
		virtualengthb3=0;	
		base1=new int[tope];
		base2=new int[tope];
		base3=new int[tope];
		for (int j=0;j<tope;j++){
			base1[j]=0;
			base2[j]=0;
			base3[j]=0;
		}
		torre1 = new Torre(virtualengthb1, base1);
		torre2 = new Torre(virtualengthb2, base2);
		torre3 = new Torre(virtualengthb3, base3);
		htabtorres.put("A", torre1);
		htabtorres.put("B", torre2);
		htabtorres.put("C", torre3);
	}
	private static void jugarNivel() {
		movimientopar=true;
		nummovimiento=0;
		boolean nofinalizado=false;
		while (!nofinalizado){
			mostrar();
			generardatosclave();
			realizarmovimiento();
			nummovimiento++;
			movimientopar=!movimientopar;
			nofinalizado=comprobarFinalizado();
		}
		mostrar();
		System.out.println("\nFelicidades has conseguido superar este nivel.\n");				
	}
	private static boolean comprobarFinalizado() {	
		boolean todook=true;
		int contsube=1;
		int contbaja=numfichas;
		
		while ((contsube<=numfichas)&(todook)){			
			todook=(base3[contsube]==contbaja);
			contsube++;			
			contbaja--;
		}				
		return todook;
	}
	private static void realizarmovimiento() {
		String destino="";
		String origen="";
		if (manual){
			origen=devolverDato("origen","");
			destino=devolverDato("destino",origen);	
			intentarmovimiento(origen,destino);
		}
		else{
			origen=generarMovimientoOrigen();
			destino=generarMovimientoDestino();
			intentarmovimiento(origen,destino);	
		}		
	}
	private static void generardatosclave() {		
		Torre tor=(Torre)htabtorres.get("A");
		virtualengthb1=tor.getVirtlength();
		Torre tor2=(Torre)htabtorres.get("B");
		virtualengthb2=tor2.getVirtlength();
		Torre tor3=(Torre)htabtorres.get("C");
		virtualengthb3=tor3.getVirtlength();				
		int posmenor=buscarMenor();		
		if (movimientopar){
			posicionfichamenor=nombresbases[posmenor];						
		}
		else{
			int posmediana=buscarMediana();
			int posoblig=buscarObligatoria(posmenor,posmediana);
			posicionfichamediana=nombresbases[posmediana];
			posicionobligada=nombresbases[posoblig];			
		}		
	}
	private static int buscarObligatoria(int posmenor, int posmediana) {
		if(posmenor==0){
			if (posmediana==1){return 2;}
			else{return 1;}
		}
		if(posmenor==1){
			if (posmediana==0){return 2;}
			else{return 0;}
		}
		if(posmenor==2){
			if (posmediana==0){return 1;}
			else{return 0;}
		}
		return 0;
	}
	private static int buscarMediana() {
		int mediano=0;
		if(base1[virtualengthb1]==1){
			if(base2[virtualengthb2]==0){mediano=2;}
			else{
				if(base3[virtualengthb3]==0){mediano=1;}
				if(base2[virtualengthb2]<base3[virtualengthb3]){mediano=1;}
				else{mediano=2;}
			}			
		}
		
		if(base2[virtualengthb2]==1){
			if(base1[virtualengthb1]==0){mediano=2;}
			else{
				if(base3[virtualengthb3]==0){mediano=0;}
				else{
					if(base1[virtualengthb1]<base3[virtualengthb3]){mediano=0;}
					else{mediano=2;}
				}
			}			
		}
		
		if(base3[virtualengthb3]==1){
			if(base1[virtualengthb1]==0){mediano=1;}
			else{
				if(base2[virtualengthb2]==0){mediano=0;}
				else{
					if(base1[virtualengthb1]<base2[virtualengthb2]){mediano=0;}
					else{mediano=1;}
				}
			}			
		}		
		return mediano;
	}
	private static int buscarMenor() {
		int menor=0;
		if(base1[virtualengthb1]==1){menor=0;}
		else{
			if(base2[virtualengthb2]==1){menor=1;}
			else{menor=2;}	
		}
		return menor;
	}
	private static String generarMovimientoOrigen() {
		if (movimientopar){return posicionfichamenor;}
		else{return posicionfichamediana;}				
	}
	private static String generarMovimientoDestino() {
		int posicion=0;
		if (movimientopar){
			if (numfichaspar){
				if (posicionanterior!=2){
					posicionanterior++;
					posicion=posicionanterior;
				}
				else{
					posicionanterior=0;
					posicion=0;
				}
			}
			else{
				if (posicionanterior!=0){
					posicionanterior--;
					posicion=posicionanterior;
				}
				else{
					posicionanterior=2;
					posicion=2;
				}
			}					
			return nombresbases[posicion];
		}
		else{
			return posicionobligada;
		}
	}
	
	private static String devolverDato(String paso,String opc1){
		boolean opcionmala=true;
		String opc="";				
		while (opcionmala){
			opc=peticionDato("Elija columna de "+ paso + ": ");
			if (!opc1.equalsIgnoreCase("")){opcionmala=(opc.equalsIgnoreCase(opc1));}
			opcionmala=!((opc.equalsIgnoreCase("A"))|(opc.equalsIgnoreCase("B"))|(opc.equalsIgnoreCase("C")));			
		}
		return opc;
	}
	private static void intentarmovimiento(String origen,String destino) {		
		Torre torreo,torred= new Torre();
		torreo=(Torre)htabtorres.get(origen.toUpperCase());
		torred=(Torre)htabtorres.get(destino.toUpperCase());						
		int auxfich=torreo.getFichas()[torreo.getVirtlength()];		
		if (auxfich!=0){
			if ((auxfich<torred.getFichas()[torred.getVirtlength()])|(torred.getFichas()[torred.getVirtlength()]==0)){
				torred.setVirtlength(torred.getVirtlength()+1);
				torred.getFichas()[torred.getVirtlength()]=auxfich;
				torreo.getFichas()[torreo.getVirtlength()]=0;
				torreo.setVirtlength(torreo.getVirtlength()-1);												
			}
		}		
	}
	private static String peticionDato(String peticion) {
		System.out.print(peticion);			
		return leerDatos();			
	}	
	public static String leerDatos() {
		String datosleidos="";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {datosleidos=br.readLine();}
		catch (NumberFormatException e) {mensajeerroopc();}
		catch (IOException e) {mensajeerroopc();}						
		return datosleidos;
	}
	private static void mensajeerroopc(){
		System.out.println("Entrada no válida.");
		System.out.println();
	}
	private static void nuevoNivel() {		
		int auxfichas=numfichas;
		iniciarBases();		
		for (int j=1;j<=numfichas;j++){
			base1[j]=auxfichas;		
			auxfichas--;
		}					
	}
	private static void mostrar(){
		mostrarbase("A",(Torre)htabtorres.get("A"));
		mostrarbase("B",(Torre)htabtorres.get("B"));
		mostrarbase("C",(Torre)htabtorres.get("C"));
		System.out.println();
	}
	
	private static void mostrarbase(String letra,Torre torreamostrar){		
		System.out.print(letra);
		for (int j=0;j<=torreamostrar.getVirtlength();j++){
			if (torreamostrar.getFichas()[j]!=0){System.out.print(torreamostrar.getFichas()[j]);}			
		}
		System.out.println();
	}		
}
	//Clase TORRE
	class Torre {
		int virtlength,sacarficha;
		int[] fichas;		

	public Torre(int virtlength, int[] fichas) {
			this.virtlength = virtlength;
			this.fichas = fichas;
	}
	public Torre() {}
	public int getVirtlength() {
		return virtlength;
	}
	public void setVirtlength(int virtlength) {
		this.virtlength = virtlength;
	}
	public int[] getFichas() {
		return fichas;
	}
	public void setFichas(int[] fichas) {
		this.fichas = fichas;
	}			
}