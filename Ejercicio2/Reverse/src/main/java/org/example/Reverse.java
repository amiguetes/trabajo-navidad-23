package org.example;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Reverse 
{
    public static void main( String[] args )
    {
        try (Scanner sc = new Scanner(System.in)){

            String line = "";

            while (!(line = sc.nextLine()).equalsIgnoreCase("stop")) {
                StringBuilder sb = new StringBuilder(line);
                System.out.println(sb.reverse().toString());
            }

        }
        
    }
}
