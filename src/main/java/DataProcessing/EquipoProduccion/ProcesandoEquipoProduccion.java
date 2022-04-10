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
    static int count = 0;

    public static void executeProcesandoEquipoProduccion() throws IOException {

        BufferedReader br = null;
        String movieID;

        try {
            br = new BufferedReader(new FileReader(inputPath + inputFile));
            String line = br.readLine();

            while (null != line) {
                if(!line.equals("crew,id")){
                    try{
                        String[] arrays = line.split("}]\",");
                        if(!arrays[0].equals("[]")){
                            System.out.println(count);
                            count++;
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
                                            + campos[0] + ",'"
                                            + campos[1].replace("'","").trim() + "','"
                                            + campos[4].replace("'","").trim() + "','"
                                            +campos[5].replace("'","").trim() + "',"
                                            +campos[2]+","
                                            +movieID + ");";

                                    fileWritter(newInsert, "equipo_produccion.sql");
                                }
                            }
                        }
                    }catch (Exception exception){
                        fileWritter(String.valueOf(count), "errors.txt");
                        line = br.readLine();
                    }
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            //System.out.println(e.fillInStackTrace());
            fileWritter(String.valueOf(count), "errors_equipoProduccion.txt");
        }
    }

    public static boolean validateID(String parameter){
        if(parameter.equals("credit_id")){
            return true;
        }
        return false;
    }
}

