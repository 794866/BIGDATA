package normanDataBase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static Utils.Utils.fileWritter;

public class crew {

    static final String inputPath = "/home/uri/Documentos/Universidad/BIGDATA/PRACTICAS" +
            "/datasets/datasets/kaggelMoviesDataSet/procesandoDatos/fase2/";

    static final String inputFile="crew.csv";
    static ArrayList<String> rows = new ArrayList();

    public static void crew() throws IOException {
        BufferedReader br = null;
        int count = 0;

        try {
            br = new BufferedReader(new FileReader(inputPath + inputFile));
            String line = br.readLine();

            while (null != line) {
                if(!line.equals("crew,id")){
                    getData(line);
                }
                line = br.readLine();
            }

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    public static void getData(String line){
        String[] textToMovieId = line.split("}]\",");
        String[] splitedText = line.split("},");
        int i=0;
        try{
            for(i=0;i<splitedText.length;i++){
                String clvPelicula = textToMovieId[1].trim();

                String[] values = splitedText[i].split(",");
                String clv_crew = values[0].replace("\"[{'credit_id':","")
                        .replace("{'credit_id':","")
                        .replace("\"","")
                        .replace("'","")
                        .trim();

                if(clv_crew.length() != 24){
                    fileWritter(splitedText[i], "norman_database/crew/new/errorsID.txt");
                }else{
                    if(!rows.contains(clv_crew)){
                        rows.add(clv_crew);
                        String newInsert = "INSERT INTO crew(clv_crew,clv_pelicula)"
                                + "VALUES ('" + clv_crew + "'," + clvPelicula + ");";

                        fileWritter(newInsert, "norman_database/crew/new/crew.sql");

                    }
                }
            }
        }catch(Exception e){
            fileWritter(splitedText[i], "norman_database/crew/new/errorsSplited.txt");
        }
    }
}
