package Fase1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static Utils.Utils.fileWritter;

public class DetallePelicula_FactTable {

    static final String inputPath = "/home/uri/Documentos/Universidad/BIGDATA/PRACTICAS" +
            "/datasets/datasets/kaggelMoviesDataSet/procesandoDatos/fase2/";

    static final String inputFile="ratings.csv";
    static ArrayList<String> rows = new ArrayList();

    public static void tratandoDetallesPeliculas() throws IOException {
        BufferedReader br = null;
        int count = 0;
        String forLineError = null;

        try {
            br = new BufferedReader(new FileReader(inputPath + inputFile));
            String line = br.readLine();
            forLineError = line;

            while (null != line) {
                if(!line.equals("userId,movieId,rating,timestamp")){
                    String[] splitedText = line.split(",");

                    String userId = null;
                    String movieId = null;
                    String rating = null;

                    if(!splitedText[0].equals("") && !splitedText[1].equals("") && !splitedText[2].equals("")){
                        userId = splitedText[0];
                        movieId = splitedText[1];
                        rating = splitedText[2];

                        String newInsert = "INSERT INTO DetallePelicula(clvPelicula,clv_pelicula," +
                                "userID,calificacion) VALUES(" + movieId + "," + movieId + "," + userId + ","
                                + rating + ");";

                        fileWritter(newInsert, "detallesPeliculas_FactTable.sql");
                    }else{
                        fileWritter(line, "errorFactTable.txt");
                    }
                }
                line = br.readLine();
            }

        } catch (Exception e) {
            //System.out.println(e.fillInStackTrace());
            fileWritter(forLineError, "exceptionFactTable.txt");
        }
    }
}
