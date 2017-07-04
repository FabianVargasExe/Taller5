

package taller5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import ucn.StdOut;


public class Taller5 {


    public static void main(String[] args) throws IOException {
        
   
    App app = new App();

    app.leerArchivos();
    
        
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
  
    StdOut.println(" (1) - RF1");
    StdOut.println(" (2) - RF2" );
    StdOut.println(" (3) - Salir. " );    
        
    int op;
        do{  
            StdOut.println("\nIngrese una opción del menú: " );
            op = Integer.parseInt(bf.readLine()); 
            switch(op){
                
                case 1: 
                    app.RF1(); 
                    break;
                case 2:
                    app.RF2(); 
                    break;
                case 3:
                    StdOut.println("Usted está saliendo del menú..." );
                    break;    
                default:
                    StdOut.println("OPCION NO VALIDA" );
                    break;               
            }
            }while( op != 3 );
     } 
}
