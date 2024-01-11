package org.example;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
/** 
 * Ejercicio 1 de la práctica 2 de la asignatura de Programación de Servicios y Procesos
 * 
 * El programa recibe como argumentos un comando y sus argumentos y lo ejecuta.
 * 
 * Toda la información asociada al desarrollo de esta clase se encuentra en la issue: https://github.com/amiguetes/trabajo-navidad-23/issues/1
 * 
 */
public class App 
{
    public static void main( String[] args )
    {
       
        if (0 >= args.length) {
            System.out.println("No se ha especificado ningún argumento");
            System.exit(1);
        }

        List<String> argumentos = Arrays.asList(args);

        ProcessBuilder pb = new ProcessBuilder(argumentos);

        try {
            Process hijo = pb.start();
            
            if (!hijo.waitFor(2, TimeUnit.SECONDS)){
                System.err.println("El comando ha tardado más de 2 segundos en ejecutarse y no ha finalizado");
                hijo.destroy();
                System.exit(1);
            }

            if (hijo.exitValue() == 0) {

                Scanner sc = new Scanner(hijo.getInputStream());
                PrintWriter pw = new PrintWriter(new FileWriter("output.txt"), true);

                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    System.out.println(line);
                    pw.println(line);
                    
                }
                sc.close();
                pw.close();
            } else {
                System.err.println("El comando ha devuelto un código de error");
                System.err.println("A continuación se muestra la salida de error del comando");

                Scanner sc = new Scanner(hijo.getErrorStream());
                
                while (sc.hasNextLine()) {  
                    String line = sc.nextLine();
                    System.err.println(line);
                    
                }

                System.exit(1);
            }

        } catch (Exception e) {
            System.err.println("Error al ejecutar el comando" + e.getLocalizedMessage());
            System.exit(1);
        }

        

    }


}
