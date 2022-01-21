/******************************************************************************************
 * Clase tratamiento fichero

 * En esta clase nos encargamos de leer el fichero que nos pasen y asi
 * crear un ArrayList con los nombres de los jugadores almacenados en el fichero.
 *****************************************************************************************/

import java.util.ArrayList;
import java.io.*;

public class TratamientoFichero
{

	public static boolean ficheroExiste = false;
    public static ArrayList<String> nombreJugadores = new ArrayList<String>();//Array donde guardamos el nombre de los jugadores
    //Metodo para leer los equipos de un fichero, devolvemos un ArrayList con el nombre de todos los equipos
    public static ArrayList<String> Leer(String nombreArchivo, int numero){
        FileReader fr = null;
        BufferedReader br = null;
        String linea;
        try
        {
            fr = new FileReader (nombreArchivo); //Abrimos el fichero
            br = new BufferedReader(fr); // Creamos un BufferReader para leer cada linea
            //Aqui se esta controlando que solo se lean los n primeros nombres, 
            //siendo n el parametro que nos han pasado.
            while((linea=br.readLine())!=null && (numero>0)){
                nombreJugadores.add(linea); //Insertamos el nombre del equipo en el ArrayList
                numero--;
            }
        }
        catch(java.io.IOException e)
        {
            System.out.println ("Error en la lectura del fichero");
            System.exit(0);
        }finally{ //Cerramos el fichero
            try{
                if( fr != null ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        ficheroExiste=true;
		return nombreJugadores;
    }
    
    public static void muestraSintaxis() {
        System.out.println("*******************************************************************************");
        System.out.println("Calendario de un torneo");
        System.out.println("*******************************************************************************");
        System.out.println("Formato de la linea de parametros: java torneo [-t] [-h] n");
        System.out.println();
        System.out.println("[-t]: Traza la parametrizacion de cada invocacion recursiva");
        System.out.println();
        System.out.println("[-h]: muestra esta ayuda");
        System.out.println();
        System.out.println("n : numero de jugadores del torneo");
        System.out.println();
        System.out.println("*******************************************************************************");
        System.out.println();
    }
    
    public static boolean preguntarFichero() {
    	return ficheroExiste;
    }

} 