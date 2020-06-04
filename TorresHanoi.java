import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TorresHanoi {
	private static int numfichas;
	private static int[] base1=new int[10];
	private static int[] base2=new int[10];
	private static int[] base3=new int[10];
	private static int virtualengthb1;
	private static int virtualengthb2;
	private static int virtualengthb3;
	private static boolean nofin;
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
			if (numfichas==10){nofin=false;};
		}		
	}
	private static void iniciarBases() {
		virtualengthb1=numfichas;
		virtualengthb2=0;
		virtualengthb3=0;		
		for (int j=0;j<10;j++){
			base1[j]=0;
			base2[j]=0;
			base3[j]=0;
		}				
	}
	private static void jugarNivel() {
		boolean nofinalizado=false;
		while (!nofinalizado){
			mostrar();
			pedirmovimiento();
			nofinalizado=comprobarFinalizado();
		}
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
	private static void pedirmovimiento() {
		String destino="";
		String origen="";
		origen=devolverDato("origen","");
		destino=devolverDato("destino",origen);
		intentarmovimiento(origen,destino);
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
//		abs("A",origen,destino,virtualengthb1,virtualengthb2,virtualengthb3,base1,base2,base3);
//		abs("B",origen,destino,virtualengthb2,virtualengthb1,virtualengthb3,base2,base1,base3);
//		abs("C",origen,destino,virtualengthb3,virtualengthb1,virtualengthb2,base3,base2,base1);
		
		int auxfich=0;
		
		if (origen.equalsIgnoreCase("A")){auxfich=base1[virtualengthb1];}
		if (origen.equalsIgnoreCase("B")){auxfich=base2[virtualengthb2];}
		if (origen.equalsIgnoreCase("C")){auxfich=base3[virtualengthb3];}
		
		if (auxfich!=0){
			if (destino.equalsIgnoreCase("A")){
				if ((auxfich<base1[virtualengthb1])|(base1[virtualengthb1]==0)){
					virtualengthb1++;
					base1[virtualengthb1]=auxfich;
					if (origen.equalsIgnoreCase("B")){
						base2[virtualengthb2]=0;		
						virtualengthb2--;		
					}
					if (origen.equalsIgnoreCase("C")){	
						base3[virtualengthb3]=0;		
						virtualengthb3--;
					}
				}			
			}
			if (destino.equalsIgnoreCase("B")){
				if ((auxfich<base2[virtualengthb2])|(base2[virtualengthb1]==0)){				
					virtualengthb2++;
					base2[virtualengthb2]=auxfich;	
					if (origen.equalsIgnoreCase("A")){		
						base1[virtualengthb1]=0;		
						virtualengthb1--;
					}
					if (origen.equalsIgnoreCase("C")){
						base3[virtualengthb3]=0;
						virtualengthb3--;
					}
				}			
			}		
			if (destino.equalsIgnoreCase("C")){
				if ((auxfich<base3[virtualengthb3])|(base3[virtualengthb1]==0)){				
					virtualengthb3++;
					base3[virtualengthb3]=auxfich;					
					if (origen.equalsIgnoreCase("A")){
						base1[virtualengthb1]=0;		
						virtualengthb1--;
					}
					if (origen.equalsIgnoreCase("B")){						
						base2[virtualengthb2]=0;		
						virtualengthb2--;
					}				
				}			
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
		try {
			datosleidos=br.readLine();
		} catch (NumberFormatException e) {
			mensajeerroopc();
			
		} catch (IOException e) {
			mensajeerroopc();
		}
						
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
		mostrarbase("A",base1,virtualengthb1);
		mostrarbase("B",base2,virtualengthb2);
		mostrarbase("C",base3,virtualengthb3);
		System.out.println();
	}
	private static void mostrarbase(String letra,int[] base, int vlenght){		
		System.out.print(letra);
		for (int j=0;j<=vlenght;j++){
			if (base[j]!=0){System.out.print(base[j]);}			
		}
		System.out.println();
	}
//	private static void abs(String opc,String orig,String dest, int virtlen,int virtlenx,int virtleny, int[] arrbase,int[] arrbasex,int[] arrbasey){
//	int auxfich=0;
//	if (orig.equalsIgnoreCase(opc)){auxfich=arrbase[virtlen];}
//	if (auxfich!=0){
//		if (dest.equalsIgnoreCase(opc)){
//			if ((auxfich<arrbase[virtlen])|(arrbase[virtlen]==0)){
//				virtlen++;
//				arrbase[virtlen]=auxfich;
//				if (orig.equalsIgnoreCase(orig)){
//					arrbasex[virtlenx]=0;		
//					virtlenx--;		
//				}					
//			}			
//		}
//	}
//}
}
