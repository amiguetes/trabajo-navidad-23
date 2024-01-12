package org.example;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.*;

public class DescargadorDeImagenes {

    private static final String IMAGE_PATH = "imagenesDescargadas";

    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "https://portal.edu.gva.es/cipfpbatoi/";
        String html = descargarHTML(url);
        String[] urlsDeImagenes = extraerUrlsDeImagenes(html);

        for (String s : urlsDeImagenes){
            System.out.println(s);
        }

        descargarImagenesPorURL(urlsDeImagenes);

    }

    public static String descargarHTML(String url) throws IOException, InterruptedException {
        ProcessBuilder curlPB = new ProcessBuilder("/usr/bin/curl", "-s", "-X", "GET", url);
        Process proceso = curlPB.start();

        StringBuilder html = new StringBuilder();
        try (Scanner scanner = new Scanner(proceso.getInputStream())) {
            while (scanner.hasNextLine()) {
                html.append(scanner.nextLine()).append(System.lineSeparator());
            }
        }
        proceso.waitFor();
        return html.toString();
    }

    public static String[] extraerUrlsDeImagenes(String html) {
        List<String> urlsDeImagenes = new ArrayList<>();
        Pattern pattern = Pattern.compile("<img [^>]*src=[\"']([^\"']+)[\"'][^>]*>");
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            urlsDeImagenes.add(matcher.group(1));
        }

        return urlsDeImagenes.toArray(new String[0]);
    }

    public static void descargarImagenesPorURL( String[] urlsImagenes) throws MalformedURLException{

        File imagenes = new File(IMAGE_PATH);

        if (!imagenes.exists()){
            imagenes.mkdir();   
        } else {
            if (!imagenes.isDirectory()){
                System.err.println( IMAGE_PATH + " Existe pero no es un directorio");
                System.exit(1);
            }
        }

        ProcessBuilder pb[] = new ProcessBuilder[urlsImagenes.length];
        Process process[] = new Process[urlsImagenes.length];

        for (int i = 0; i < pb.length; i++) {

            URL url = new URL(urlsImagenes[i]);
            String path = url.getPath();
            
            pb[i] = new ProcessBuilder("/usr/bin/curl", "-s", "-X", "GET", url.toString());
            pb[i].redirectOutput(new File(imagenes.getName() + File.separator +  path.substring(path.lastIndexOf('/') + 1)));

            try {
                process[i] = pb[i].start();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            
        }

        for (Process p : process){
            try {
                p.waitFor();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        

    }
}
