


package taller5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
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
             reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filename)), "UTF-8"));
         } 
         catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (UnsupportedEncodingException ex) {
              Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
          }

        myRecursion(reader);
         
     } //end main   
    }
    
    
    public static void myRecursion(BufferedReader br) 
    {
        String s1 = "";
         
        try {
             s1 = br.readLine();
         }
         catch (IOException e) {
             e.printStackTrace();
         }
 
        if(s1 == null)
            return;
        else
        {
            int i = 0;
            System.out.println (s1);

            
            myRecursion(br);
            return;
        }
    }
   

   public void leer(String cadena, int i, int digito, int caract){
       
       int c = 1;
       String ultimoCaracter = cadena.substring(cadena.length()-1);
       
       if(i < cadena.length()){

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
           Error error = new Error(digito,"dd"); 
           listaErrores.add(error); 
           
        }
                 
        
   }
    
   public void IterarLista (String[] a, int i){
        
      
        if(!a[i].isEmpty()){
           
        leer(a[i], 0, 0, 0);
        IterarLista(a , i+1);
        }

   }
   

   @Override 
   public void RF1() {
       
       Iterator ite = listaArchivos.iterator();
       int i = 0;
        while(ite.hasNext()){
             String[] da = (String[]) ite.next();
             IterarLista(da , i);
             i++;
        
        
        }

        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            
        Iterator ite1 = listaErrores.iterator();
        while(ite1.hasNext()){
             Error error = (Error) ite1.next();
                
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
        
        Iterator ite = listaValida.iterator();
        while(ite.hasNext()){
             String cadena = (String) ite.next();   
            
         fichero = new FileWriter("TextoValido.txt");
         pw = new PrintWriter(fichero);  
         
         pw.println(cadena+" ");
 
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