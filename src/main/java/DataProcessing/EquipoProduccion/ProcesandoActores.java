//https://dzone.com/articles/how-to-convert-csv-to-json-in-java
package DataProcessing.EquipoProduccion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static Utils.Utils.fileWritter;

public class ProcesandoActores {
    static final String inputPath = "/home/uri/Documentos/Universidad/BIGDATA/PRACTICAS" +
            "/datasets/datasets/kaggelMoviesDataSet/procesandoDatos/";

    static final String inputFile="procesandoActores.csv";
    static int count = 1;
    static String movieID;
    static List<String> split;
    static ArrayList<String> rows = new ArrayList();

    public static void executeProcesandoActores() throws IOException {
        BufferedReader br = null;
        String movieID;

        try {
            br = new BufferedReader(new FileReader(inputPath + inputFile));
            String line = br.readLine();
            while (null != line) {
                if (!line.equals("cast,id")) {
                    try {
                        String[] arrays = line.split("}]\",");
                        movieID = arrays[1];
                        if (!arrays[0].equals("[]")) {
                            System.out.println(count);
                            count++;
                            String newLine = line.replace("\"[{'cast_id': ","")
                                    .replace("{'cast_id': ","")
                                    .replace("'character': '","")
                                    .replace("'credit_id': '","")
                                    .replace("'gender': ","")
                                    .replace("'name': '","")
                                    .replace("'id': ","")
                                    .replaceAll("\"\"", "")
                                    .replaceAll("\"", "")
                                    .replaceAll("\'", "");

                            split = List.of(newLine.split("},"));
                            for(int i=0;i<split.size();i++) {
                                String[] campos = split.get(i).split(",");


                                if(!rows.contains(campos[2])){
                                    rows.add(campos[2]);
                                    String newInsert = "INSERT INTO Actor("+
                                            "ID,papel,nombre,genero,pelicula)"+
                                            "VALUES('"
                                            + campos[2].trim() +
                                            "','" + campos[1].trim() +
                                            "','" + campos[5].trim() +
                                            "'," + campos[3].trim() +
                                            "," + movieID.trim() + ");";
                                    fileWritter(newInsert, "inserts_actores.sql");
                                }
                            }
                        }
                    } catch (Exception exception) {
                        fileWritter(String.valueOf(count), "errors.txt");
                        line = br.readLine();
                    }
                }
                line = br.readLine();
            }

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    public static void ordenarCampos(String[] campos){

    }
}

