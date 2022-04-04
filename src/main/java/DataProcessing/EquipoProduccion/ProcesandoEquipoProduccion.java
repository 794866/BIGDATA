//https://dzone.com/articles/how-to-convert-csv-to-json-in-java
package DataProcessing.EquipoProduccion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static Utils.Utils.fileWritter;
import static java.lang.Integer.parseInt;

public class ProcesandoEquipoProduccion {

    static final String inputPath = "/home/uri/Documentos/Universidad/BIGDATA/PRACTICAS" +
            "/datasets/datasets/kaggelMoviesDataSet/procesandoDatos/";

    static final String inputFile="procesandoEquipoProduccion.csv";

    static List<String> split;
    static ArrayList<String> rows = new ArrayList();

    public static void executeProcesandoEquipoProduccion() throws IOException {

        BufferedReader br = null;
        String movieID;
        Pattern p = Pattern.compile("/\"([^\"]*)\"|'([^']*)'/g\n");

        try {
            br = new BufferedReader(new FileReader(inputPath + inputFile));
            String line = br.readLine();

            Pattern patt = Pattern.compile("\'([^\']*)\'");
            String creditID="";

            while (null != line) {
                if(!line.equals("crew,id")){

                    String newLine = line;

                    newLine = line.replace("\"[{'credit_id': ","")
                            .replace("'department': ","")
                            .replace("'gender': ","")
                            .replace("'id': ","")
                            .replace("'job': ","")
                            .replace("'name': ","")
                            .replace("'profile_path': ","")
                            .replace(" {","")
                            .replace("'credit_id': ","")
                            .replaceAll("\"\"", "\'")
                            .replace("O'","O")
                            .replace("D'","D")
                            .replace("Actor's","Actors");


                    String[] arrays = line.split("}]\",");
                    movieID = arrays[1];

                    split = List.of(newLine.split("},"));
                    for(int i=0;i<split.size();i++){
                        String[] campos = split.get(i).split(",");

                        if(!rows.contains(campos[0])){
                            rows.add(campos[0]);
                            String newInsert = "INSERT INTO EquipoProduccion(" +
                                    "ID,"+
                                    "nombreDepartamento,"+
                                    "cargo,"+
                                    "nombreTrabajador,"+
                                    "genero,"+
                                    "pelicula)" + "VALUES("
                                    + campos[0] + ","
                                    + campos[1] + ","
                                    + campos[4] + ","
                                    +campos[5]+","
                                    +campos[2]+","
                                    +movieID + ");";

                            fileWritter(newInsert, "equipo_produccion.sql");
                        }
                    }
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    public static boolean validateID(String parameter){
        if(parameter.equals("credit_id")){
            return true;
        }
        return false;
    }
}

