package DataProcessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import static Utils.Utils.fileWritter;

public class tratandoProductorPeliculas {

    static final String inputPath = "/home/uri/Documentos/Universidad/BIGDATA/PRACTICAS" +
            "/datasets/datasets/kaggelMoviesDataSet/procesandoDatos/fase2/";

    static final String inputFile="crew.csv";
    static ArrayList<String> rows = new ArrayList();

    public static void executeTratandoProductorPeliculas() throws IOException {
        BufferedReader br = null;
        int count = 0;

        try {
            br = new BufferedReader(new FileReader(inputPath + inputFile));
            String line = br.readLine();

            while (null != line) {

                if(!line.equals("crew,id")){
                    String[] textToMovieId = line.split("}]\",");
                    String clvPelicula = textToMovieId[1].trim();
                    String[] splitedText = line.split("},");

                    //clv_productorPelis, clvPelicula, clv_productor, clv_pelicula, nombreDepartamento, cargo
                    for(int i=0;i<splitedText.length;i++){

                        String[] values = splitedText[i].split(",");
                        String clv_productorPelis = values[0].replace("\"[{'credit_id':","")
                                .replace("{'credit_id':","")
                                .replace("\"","")
                                .replace("'","")
                                .trim();
                        String clv_productor = values[3].replace(" 'id': ","")
                                .replace("\"","")
                                .replace("'","")
                                .trim();

                        String nombreDepartamento = values[1].replace(" 'department': ","")
                                .replace("\"","")
                                .replace("'","")
                                .trim();

                        String cargo = values[4].replace(" 'job': ","")
                                .replace("\"","")
                                .replace("'","")
                                .trim();

                        if(clv_productorPelis.length() != 24){

                            fileWritter(splitedText[i], "productorPeliculas_errors.txt");
                        }else{
                            if(!rows.contains(clv_productorPelis)){
                                rows.add(clv_productorPelis);
                                String newInsert = "INSERT INTO productorPeliculas(clv_productorPelis, clvPelicula, clv_productor, " +
                                        "clv_pelicula, nombreDepartamento, cargo)"
                                        + "VALUES ('" + clv_productorPelis + "'," + clvPelicula + "," + clv_productor + "," + clvPelicula
                                        + ",'" + nombreDepartamento + "','" + cargo + "');";

                                fileWritter(newInsert, "productorPeliculas.sql");

                                count++;
                                System.out.println(count);
                            }
                        }
                    }
                }
                line = br.readLine();
            }

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }
}
