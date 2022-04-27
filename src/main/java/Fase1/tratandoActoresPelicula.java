package Fase1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import static Utils.Utils.fileWritter;

public class tratandoActoresPelicula {

    static final String inputPath = "/home/uri/Documentos/Universidad/BIGDATA/PRACTICAS" +
            "/datasets/datasets/kaggelMoviesDataSet/procesandoDatos/fase2/";

    static final String inputFile="cast2.csv";
    static ArrayList<String> rows = new ArrayList();

    public static void executeTratandoActoresPelicula() throws IOException {
        BufferedReader br = null;
        int count = 0;

        try {
            br = new BufferedReader(new FileReader(inputPath + inputFile));
            String line = br.readLine();

            while (null != line) {

                if(!line.equals("cast|id")){
                    String[] textToMovieId = line.split("}]\"\\|");
                    String clvPelicula = textToMovieId[1].trim();
                    String[] splitedText = line.split("}\\|");
                    Pattern patt = Pattern.compile("\'([^\']*)\'");

                    //clv_peliculaCast, clvPelicula, clvActor, clvPelicula, descripcionPersonaje
                    for(int i=0;i<splitedText.length;i++){

                        String[] values = splitedText[i].split("\\|");
                        String clv_peliculaCast = values[2].replace(" 'credit_id': ","")
                                .replace("\"","")
                                .replace("'","")
                                .trim();

                        if(clv_peliculaCast.length() != 24) {
                            clv_peliculaCast = values[3].replace(" 'credit_id': ", "")
                                    .replace("\"", "")
                                    .replace("'", "")
                                    .trim();
                            String clvActor = values[5].replace("'id': ", "")
                                    .replace("\"", "")
                                    .replace("'", "")
                                    .trim();
                            String descripcionPersonaje = values[1].replace("'character': ", "")
                                    .replace("\"", "")
                                    .replace("'", "")
                                    .trim()
                                    + " " + values[2].replace("\"", "")
                                    .replace("'", "")
                                    .trim();

                            if(clv_peliculaCast.length() != 24){
                                fileWritter(splitedText[i], "insertErrors_ActoresPelicula.txt");
                            }else{
                                if (!rows.contains(clv_peliculaCast)) {
                                    rows.add(clv_peliculaCast);
                                    String newInsert = "INSERT INTO actoresPelicula(clv_peliculaCast, clvPelicula, clvActor, clv_pelicula, descripcionPersonaje)"
                                            + "VALUES ('" + clv_peliculaCast + "'," + clvPelicula + "," + clvActor + "," + clvPelicula
                                            + ",'" + descripcionPersonaje + "');";

                                    fileWritter(newInsert, "actoresPeliculas_error_inserts.sql");

                                    count++;
                                    System.out.println(count);
                                }
                            }
                        }
//                        else{
//                        String clvActor = values[4].replace("'id': ","")
//                                .replace("\"","")
//                                .replace("'","")
//                                .trim();
//                        String descripcionPersonaje = values[1].replace("'character': ","")
//                                .replace("\"","")
//                                .replace("'","")
//                                .trim();
//                            if(!rows.contains(clv_peliculaCast)){
//                                rows.add(clv_peliculaCast);
//                                String newInsert = "INSERT INTO actoresPelicula(clv_peliculaCast, clvPelicula, clvActor, clv_pelicula, descripcionPersonaje)"
//                                        + "VALUES ('" + clv_peliculaCast + "'," + clvPelicula + "," + clvActor + "," + clvPelicula
//                                        + ",'" + descripcionPersonaje + "');";
//
//                                fileWritter(newInsert, "actoresPelicula_insert.sql");
//
//                                count++;
//                                System.out.println(count);
//                            }
                    }
                }
                line = br.readLine();
            }

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }
}
