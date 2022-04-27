package Fase2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import static Utils.Utils.fileWritter;

public class actor {

    static final String inputPath = "/home/uri/Documentos/Universidad/BIGDATA/PRACTICAS" +
            "/datasets/datasets/kaggelMoviesDataSet/procesandoDatos/fase2/";

    static final String inputFile="cast2.csv";
    static ArrayList<String> rows = new ArrayList();
    private static String forErrors;

    public static void actor() throws IOException {
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

                    //clv_actor, clv_cast, descripcionPersonaje, nombreActor
                    for(int i=0;i<splitedText.length;i++){
                        forErrors = splitedText[i];
                        String[] values = splitedText[i].split("\\|");
                        String clv_cast = values[2].replace(" 'credit_id': ","")
                                .replace("\"","")
                                .replace("'","")
                                .trim();

                        if(clv_cast.length() != 24) {
                            clv_cast = values[3].replace(" 'credit_id': ", "")
                                    .replace("\"", "")
                                    .replace("'", "")
                                    .trim();

                            String clv_actor = values[5].replace("'id': ", "")
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

                            String nombreActor = values[6].replace(" 'name': ", "")
                                    .replace("\"", "")
                                    .replace("'", "")
                                    .trim();

                            if(clv_cast.length() != 24){
                                fileWritter(splitedText[i], "norman_database/actor/actor_ERROR.txt");
                            }else{
                                if (!rows.contains(clv_cast)) {
                                    rows.add(clv_cast);
                                    String newInsert = "INSERT INTO actor(clv_actor, clv_cast, descripcionPersonaje, nombreActor)"
                                            + "VALUES (" + clv_actor + ",'" + clv_cast + "','" +
                                            descripcionPersonaje + "','" + nombreActor + "');";

                                    fileWritter(newInsert, "norman_database/actor/actor.sql");

                                }
                            }
                        }
                        else {
                            String clv_actor = values[4].replace("'id': ", "")
                                    .replace("\"", "")
                                    .replace("'", "")
                                    .trim();
                            String descripcionPersonaje = values[1].replace("'character': ", "")
                                    .replace("\"", "")
                                    .replace("'", "")
                                    .trim();
                            String nombreActor = values[5].replace(" 'name': ", "")
                                    .replace("\"", "")
                                    .replace("'", "")
                                    .trim();

                            if (!rows.contains(clv_cast)) {
                                rows.add(clv_cast);
                                //clv_actor, clv_cast, descripcionPersonaje, nombreActor
                                String newInsert = "INSERT INTO actor(clv_actor, clv_cast, descripcionPersonaje, nombreActor)"
                                        + "VALUES (" + clv_actor + ",'" + clv_cast + "','" +
                                        descripcionPersonaje + "','" + nombreActor + "');";

                                fileWritter(newInsert, "norman_database/actor/actor.sql");

                            }
                        }
                    }
                }
                line = br.readLine();
            }

        } catch (Exception e) {
            fileWritter(forErrors, "norman_database/actor/actor_ERROR.txt");
        }
    }
}
