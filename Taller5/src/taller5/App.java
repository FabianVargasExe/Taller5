

package taller5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.util.LinkedList;
import ucn.StdIn;
import ucn.StdOut;


public class App implements IApp {
 
    private LinkedList listaArchivos;
    private ArrayList listaValida; 
    private ArrayList listaErrores; 

    public App() {

        listaArchivos = new LinkedList();
        listaValida = new ArrayList();
        listaErrores = new ArrayList();

    }

   // Método de lectura de los archivos .txt

    @Override 
    public void leerArchivos() {
       
       StdOut.println("Ingrese Cantidad de archivos a revisar: ");
       StdOut.println("---------------------");
       int cant = StdIn.readInt(); 
       
       for(int i = 1; i <= cant; i++){
        String filename = "texto"+i+".txt";
         
        // Initialize the reader
         BufferedReader reader = null;
         try {
             scanner = new Scanner(new FileInputStream(filename), "UTF-8"); 
         } 
         catch (FileNotFoundException e) {
             e.printStackTrace();
          }
        readFile();
         
     } 
    }

    
    private static Scanner scanner;

    public void readFile() {
      if (!scanner.hasNext()) return;
      String line = scanner.next();
      if(line.substring(0, 1).matches("[a-zA-Z]+")||line.substring(0, 1).matches("[0-9]+")){
          listaValida.add(line);
          
          if(line.matches("[a-zA-Z]+")||line.matches("[0-9]+")||line.endsWith(",")){
          listaValida.add(line);   
      System.out.println(line);
          }else{
              listaErrores.add(line);    
          }
      }else{
      listaErrores.add(line);
      }
      readFile();}
    
    
  /* public void leer(String cadena, int i, int digito, int caract){
       
       String ultimoCaracter = cadena.substring(cadena.length()-1);
       
       if(i < cadena.length()){
           
           if(cadena.endsWith(","))

           if(Character.isDigit(cadena.charAt(i)) && i == digito){
               digito ++;
               leer(cadena, i+1, digito, caract);
               
           }
           else if(!Character.isAlphabetic(i) && i == caract){
               caract++;
               leer(cadena, i+1, digito, caract);       
           }   
           
        }else if (cadena.length() == digito || cadena.length() == caract){
             
           listaValida.add(cadena);
              
        }else{
           Error error = new Error(digito, caract); 
           listaErrores.add(error);      
        }
  
   }*/
    
   @Override 
   public void RF1() {
       

        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            
        Iterator ite1 = listaErrores.iterator();
        while(ite1.hasNext()){
             String error = (String) ite1.next();
                
         fichero = new FileWriter("errores.txt");
         pw = new PrintWriter(fichero);  
         
         pw.println("Palabra no válida:+" + error.getPalabra() +"(La palabra contiene un carácter no válido en la posición"+ error.getPosicion()+")");

         }
            StdOut.println("\nEl archivo 'errores.txt' se ha creado exitosamente.");
                   
        } catch (Exception e) {
            e.printStackTrace();
        // Se asegura de cerrar el archivo    
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        } 
  }  
       
   @Override 
   public void RF2() {
       
       
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
        
        Iterator ite1 = listaValida.iterator();
        while(ite1.hasNext()){
             String cadena = (String) ite1.next();   
            
         fichero = new FileWriter("TextoValido.txt");
         pw = new PrintWriter(fichero);  
         
         pw.print(cadena+" ");
         pw.println();
        }

        StdOut.println("\nEl archivo 'TextoValido.txt' se ha creado exitosamente.");
                   
        } catch (Exception e) {
            e.printStackTrace();
        // Se asegura de cerrar el archivo    
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
          }        
        }   
    }
