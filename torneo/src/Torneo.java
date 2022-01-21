/******************************************************************************************
 * Clase principal
 * Es la que decide en funcion de los parametros de entrada que debe de mostrar
 * y si hubiera un fichero de nombres llama a TratamientoFichero para guardarlos en un
 * ArrayList.
 * Tambien, llama a la clase algoritmo para ejecutar el algoritmo de DYV (torneo)
 * y lo vuelve a llamar para imprimir la tabla.
 *****************************************************************************************/

import java.util.ArrayList;

public class Torneo {

    static Algoritmo algoritmo; // Objeto para resolver el algoritmo
    public static ArrayList nombreJugadores = new ArrayList();
    static final int MAXIMO = 20; 
    public static int encuentros [][] = encuentros = new int [MAXIMO][MAXIMO];

    public static void main(String [] args) {
        boolean f=false, h=false, t=false;
        int numeroJugadores = 0;
        String nombreFichero=""; // Almacenamos nombre Fichero

        for (int c = 0; c < args.length; c++) {
            if (args[c].startsWith("-t")){
                t=true;
                algoritmo.traza(t);

                if (c+1<=args.length){ // En la posicion 2 puede estar la h o la n.
                    if (args[c+1].startsWith("-h")){ // primero t y luego h
                        TratamientoFichero.muestraSintaxis();
                        t=true;
                        if(c+2<=args.length) {
                            numeroJugadores= Integer.parseInt(args[c+2]);
                            if(c+3<=args.length) {
                                nombreFichero= args[c+3];
                                f=true;
                            }
                        } else {
                            System.out.println("ERROR: Debe introducir un parametro n de entrada");
                            System.exit(0);
                        }
                    } else {  //Solo se ha introducido t y n (+fichero opcional)
                        numeroJugadores= Integer.parseInt(args[c+1]);
                        if(c+2<args.length) {
                            nombreFichero= args[c+2];
                            f=true;
                        }	
                    }
                } else {
                    System.out.println("Se debe introducir el parametro n");
                }
            } else if (args[c].startsWith("-h") && (t==false)) {
                TratamientoFichero.muestraSintaxis();

                if(c+1<args.length) {
                    numeroJugadores= Integer.parseInt(args[c+1]);
                    if(c+2<args.length) {
                        nombreFichero= args[c+2];
                    }
                }
                else {
                    System.out.println("ERROR: Debe introducir un parametro n de entrada");
                    System.exit(0);
                }

            } else if (f==false){
                numeroJugadores= Integer.parseInt(args[c]);
                if(c+1<args.length) {
                    nombreFichero= args[c+1];
                    f=true;
                }
            }
        }

        if (f==true){
            //Leemos los datos del archivo indicado en los argumentos
            nombreJugadores= TratamientoFichero.Leer(nombreFichero, numeroJugadores);
        }

        Algoritmo.torneo(numeroJugadores, encuentros);
        Algoritmo.imprimirTabla(numeroJugadores, nombreJugadores, encuentros);

    }
}