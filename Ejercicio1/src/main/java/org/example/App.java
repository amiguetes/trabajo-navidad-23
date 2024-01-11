package org.example;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
/**
 * Hello world!
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
                System.out.println("El comando ha tardado más de 2 segundos en ejecutarse");
                hijo.destroy();
                System.exit(1);
            }

            if (hijo.exitValue() == 0) {

                Scanner sc = new Scanner(hijo.getInputStream());
                PrintWriter pw = new PrintWriter(new FileWriter("output.txt"), true);

                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    pw.println(line);
                    
                }
                sc.close();
                pw.close();
            } else {
                System.out.println("El comando ha devuelto un código de error");
                System.exit(1);
            }

        } catch (Exception e) {
            System.err.println("Error al ejecutar el comando" + e.getLocalizedMessage());
            System.exit(1);
        }

        

    }


}
