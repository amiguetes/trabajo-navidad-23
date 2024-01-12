package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Ejercicio2
{
    private static final String STOP_MESSAGE = "stop";
    private static final String WELCOME_MESSAGE = "A partir de ahora todas las cadenas que me introduzcas y pulses enter yo les daré la vuelta, cuando termines, escribe la cadena: "+STOP_MESSAGE;
    private static final String GAME_STRING_GENERATED_MESSAGE = "La cadena dada la vuelta es: ";
    private static final String END_MESSAGE = "Encantado de haber jugado contigo, un saludo.";
    private static final String DEFAULT_REVERSE_JAR_LOCATION = "Reverse/target/Reverse-1.0-SNAPSHOT-jar-with-dependencies.jar";
    private static final String JAVA_JAR_COMMAND = "java -jar ";

    public static void main( String[] args )
    {
        System.out.println(WELCOME_MESSAGE);

        String reverse_jar_location = args.length > 0 ? args[0] : DEFAULT_REVERSE_JAR_LOCATION;

        String process_path = JAVA_JAR_COMMAND + reverse_jar_location;

        ProcessBuilder pb = new ProcessBuilder(process_path.split(" "));

        try {
            Process reverse = pb.start();

            try (Scanner entradaUsuario = new Scanner(System.in)){

            }


        } catch (IOException e) {
            System.err.println("Error al ejecutar el proceso de Reverse en la ruta: "+reverse_jar_location);
            System.err.println(e.getLocalizedMessage());
            System.exit(1);
        }
    }
}
