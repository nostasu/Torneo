
import java.util.ArrayList;

/*****************************************************************************************
 * CLASE algoritmo principal del problema basado en la t�cnica divide y vencer�s
 *****************************************************************************************/

public class Algoritmo
{
    static int encuentros [][]; // Matriz donde almacenamos los encuentros
    static int numeroJugadores;
    static int dia;
    static boolean existenNombres;
    static boolean hacerTraza;
    static int jugadoresAux;
    static int jugador;

    /* Funcion que permite verificar si un valor n es potencia de 2
     * @param n es el valor que recibe la funcion para verificar si es multiplo de 2.
     * @return devuelve un booleano que nos indicara si es potencia o no.
     */
    public static void traza (boolean t) {
        if (t=true) {
            hacerTraza=true;
        } else {
            hacerTraza = false;
        }
    }

    /*
     * Exposici�n del algoritmo divide y vencer�s
     * @param el numero de jugadores totales del torneo
     * @param encuentros la matriz en la que se van a guardar los enfrentamientos
     */
    public static void torneo(int numeroJugadores, int encuentros[][]) {

        int m=numeroJugadores/2;

        /**
         * Exponemos primero de todo el caso trivial o caso base, el cual tiene soluci�n inmediata
         * En este caso base si se enfrentan 2 jugadores, tardaran N-1 dias, es decir, 1 dia, en disputar el partido.
         * 
         */
        if (numeroJugadores==2) {
            if(hacerTraza) { 
                System.out.println("El valor de n es: " + numeroJugadores);  //Para mostrar la traza del algoritmo
            }
            encuentros[0][0]=2;
            encuentros[1][0]=1;
        }

        /** 
         * Caso en el que no es potencia de 2, pero si divisible por 2
         */
        else if (numeroJugadores%2 == 0) {
            if(hacerTraza) { 
                System.out.println("El valor de n es: " + numeroJugadores);  //Para mostrar la traza del algoritmo
            }
            /* Llamamos a la funci�n recursivamente hasta llegar al caso base */
            torneo (m, encuentros);

            // Llenado de los cuadrantes
            for (jugador=0; jugador<m; jugador++) {
                for (dia=m; dia<numeroJugadores-1; dia++) {
                    encuentros[jugador][dia] = jugador+1 + dia+1;
                    if (encuentros[jugador][dia] > numeroJugadores)
                        encuentros[jugador][dia] = encuentros[jugador][dia]-m;
                }
            }

            for (jugador=m; jugador<numeroJugadores; jugador++) {
                for (dia=m; dia<numeroJugadores-1; dia++) {
                    encuentros[jugador][dia] = jugador+1 - (dia+1);
                    if (encuentros[jugador][dia] <= 0)
                        encuentros[jugador][dia] = encuentros[jugador][dia] + m;
                }
            }

            for (jugador=m; jugador<numeroJugadores; jugador++) {
                for (dia=0; dia<m; dia++) {
                    if (encuentros[jugador-m][dia] != 0)
                        encuentros[jugador][dia] = encuentros[jugador - numeroJugadores / 2][dia] + numeroJugadores / 2;
                }
            }

            for (jugador=0; jugador<m; jugador++) {
                for (dia=0; dia<m; dia++) {
                    if (encuentros[jugador][dia] == 0)
                        encuentros[jugador][dia] = jugador+1+ m ;
                }
            }

            for (jugador=m; jugador<numeroJugadores; jugador++) {
                for (dia=0; dia<m; dia++) {
                    if (encuentros[jugador][dia] == 0)
                        encuentros[jugador][dia] = jugador+1 -m;
                }
            }
        }
        /** 
         * Caso especial: cuando el numero de jugadores es impar
         * Usamos divide y venceras para encontrar manera de resolverlo para 
         * un par impar de jugadores, sabiendo que lo tenemos resuelto para un numero par.
         */
        else {
            if(hacerTraza) { 
                System.out.println("El valor de n es: " + numeroJugadores);  //Para mostrar la traza del algoritmo
            }
            /* Si n es impar, como conocemos la solucion para caso par, le sumamos uno para poder llegar al caso base y llamamos recursivamente */
            torneo (numeroJugadores+1, encuentros);
            /* Eliminamos los valores excedentes y estos valores seran los dias de descanso */
            for (jugador=0; jugador<numeroJugadores; jugador++) {
                for (dia=0; dia<numeroJugadores; dia++) {
                    if (encuentros[jugador][dia]==numeroJugadores+1)
                        encuentros[jugador][dia] = 0;
                }
            }
        }
    }

    /* Funcion ImprimirTabla - Da formato a la impresion de la tabla de encuentros
     * @param n - es la cantidad de jugadores
     * @param listaNombres es el arrayList que nos permite rellenar la tabla con los nombres adecuados
     */
    static void imprimirTabla(int n, ArrayList<String> listaNombres, int encuentros[][])
    {
        int dias, i, j;
        existenNombres = TratamientoFichero.preguntarFichero();
        boolean impar = false;

        /*
         * La divisi�n de casos, n es par y n impar.
         */
        if(n%2 == 0) {
            dias = n-1; 
        }
        else {
            dias = n;
            impar=true;
        }

        System.out.println("Tabla de encuentros");
        System.out.printf("[JUGADOR ]");

        //Esto es lo que nos pinta la tabla, hay que controlar el formato
        for(i=0; i<dias; i++) {
            if(i+1 < 10) {
                System.out.printf("%s%-4d%s", "[Dia ",(i+1),"]");
            }else {
                System.out.printf("%s%-4d%s", "[Dia ",(i+1),"]");
            }
        }

        /* Finalmente imprimimos valores */

        if(impar) {
            for(i=0; i<dias; i++) {

                if(i+1 < 10) {
                    if(existenNombres) {
                        System.out.printf("\n[%8s]", listaNombres.get(i));
                    } else {
                        System.out.printf("\n[%8d]",i+1);
                    }
                }
                else {
                    if(existenNombres) {
                        System.out.printf("\n[%8s]", listaNombres.get(i));
                    } else {
                        System.out.printf("\n[%8d]",i+1);
                    }
                }

                for(j=0; j<dias; j++) {
                    if(encuentros[i][j] == 0) {
                        System.out.printf("[    -   ]");
                    }else if(encuentros[i][j] < 10) {
                        if(existenNombres) {
                            System.out.printf("[%8s]", listaNombres.get(encuentros[i][j]-1));
                        } else {
                            System.out.printf("[%8s]", encuentros[i][j]);
                        }
                    }else
                    if(existenNombres) {
                        System.out.printf("[%8s]", listaNombres.get(encuentros[i][j]-1));
                    } else {
                        System.out.printf("[%8s]", encuentros[i][j]);
                    }
                }
            }
        } else {
            for(i=0; i<dias+1; i++) {

                if(i+1 < 10) {
                    if(existenNombres) {
                        System.out.printf("\n[%8s]", listaNombres.get(i));
                    } else {
                        System.out.printf("\n[%8d]",i+1);
                    }
                }
                else {
                    if(existenNombres) {
                        System.out.printf("\n[%8s]", listaNombres.get(i));
                    } else {
                        System.out.printf("\n[%8d]",i+1);
                    }
                }

                for(j=0; j<dias; j++) {
                    if(encuentros[i][j] == 0) {
                        System.out.printf("[    -   ]");
                    }else if(encuentros[i][j] < 10) {
                        if(existenNombres) {
                            System.out.printf("[%8s]", listaNombres.get(encuentros[i][j]-1));
                        } else {
                            System.out.printf("[%8s]", encuentros[i][j]);
                        }
                    }else
                    if(existenNombres) {
                        System.out.printf("[%8s]", listaNombres.get(encuentros[i][j]-1));
                    } else {
                        System.out.printf("[%8s]", encuentros[i][j]);
                    }
                }
            }
        }

    }
}