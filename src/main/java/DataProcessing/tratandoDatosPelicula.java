package DataProcessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Utils.Utils.fileWritter;

public class tratandoDatosPelicula {

    static final String inputPath = "/home/uri/Documentos/Universidad/BIGDATA/PRACTICAS" +
            "/datasets/datasets/kaggelMoviesDataSet/procesandoDatos/fase2/";

    static final String inputFile="peliculas_tratadas.csv";
    static ArrayList<String> rows = new ArrayList();

    public static void executeProcesandoDatosPeliculas() throws IOException {
        BufferedReader br = null;
        int count = 0;
        try {
            br = new BufferedReader(new FileReader(inputPath + inputFile));
            String line = br.readLine();

            String title = null, releaseDate=null;
            int id = 0, budget= 0, revenue= 0;
            float runtime= 0.0F, popularity=0.0F;
            while (null != line) {

                String[] splitedText = line.split(";");

                if(!splitedText[0].equals("id")){

                    if(!splitedText[0].equals("")){
                        if(!rows.contains(splitedText[0])){
                            rows.add(splitedText[0]);
                            id = Integer.parseInt(splitedText[0]);
                            title = splitedText[1].replace("'","").trim();
                            releaseDate = splitedText[2].trim();
                            runtime = Float.parseFloat(splitedText[3]);
                            popularity = Float.parseFloat(splitedText[4].replace(",","."));
                            budget = Integer.parseInt(splitedText[5]);
                            revenue = Integer.parseInt(splitedText[6]);

                            String newInsert = "INSERT INTO datospelicula(clvPelicula,titulo,fechaPublicacion,duracionMinutos,popularidad," +
                                    "presupuesto,ingresos) VALUES(" + id + ",'" + title + "','" + releaseDate
                                    + "'," + runtime + "," + popularity + "," + budget + "," + revenue + ");";

                            fileWritter(newInsert, "datosPelicula_insert.sql");
                        }
                    }

                    count++;
                    System.out.println(count);
                }
                line = br.readLine();
            }


        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    static String getId(String cadena) {
        List<Integer> id = new ArrayList<Integer>();
        Matcher finder = Pattern.compile("\\d+").matcher(cadena);
        while (finder.find()) {
            id.add(Integer.parseInt(finder.group()));
        }
        return id.get(0).toString();
    }

}
