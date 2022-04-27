package normanDataBase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static Utils.Utils.fileWritter;

public class valoracionPelicula {

    static final String inputPath = "/home/uri/Documentos/Universidad/BIGDATA/PRACTICAS" +
            "/datasets/datasets/kaggelMoviesDataSet/procesandoDatos/fase2/";

    static final String inputFile="ratings.csv";
    static ArrayList<String> rows = new ArrayList();

    public static void factTable_norman_database() throws IOException {
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

                    String userID = null;
                    String clv_pelicula = null;
                    String calificacion = null;

                    if(!splitedText[0].equals("") && !splitedText[1].equals("") && !splitedText[2].equals("")){
                        userID = splitedText[0];
                        clv_pelicula = splitedText[1];
                        calificacion = splitedText[2];

                        //clv_pelicula, userID, calificacion
                        String newInsert = "INSERT INTO valoracionPelicula(clv_pelicula,userID," +
                                "calificacion) VALUES("+ clv_pelicula+","+userID+","+calificacion+");";

                        fileWritter(newInsert, "norman_database/fact_table/valoracionPelicula_FactTable.sql");
                    }else{
                        fileWritter(line, "norman_database/fact_table/errorFactTable.txt");
                    }
                }
                line = br.readLine();
            }

        } catch (Exception e) {
            //System.out.println(e.fillInStackTrace());
            fileWritter(forLineError, "norman_database/fact_table/exceptionFactTable.txt");
        }
    }
}
