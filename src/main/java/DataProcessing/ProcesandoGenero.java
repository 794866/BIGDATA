package DataProcessing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static Utils.Utils.*;

public class ProcesandoGenero {

    static final String inputPath = "/home/uri/Documentos/Universidad/BIGDATA/PRACTICAS" +
            "/datasets/datasets/kaggelMoviesDataSet/procesandoDatos/";

    static final String inputFile="procesandoGenero.csv";

    static List<String> split;
    static ArrayList<Integer> listID = new ArrayList();
    static int count=0;

    public static void executeBuilderGeneroData() throws IOException {
        BufferedReader br = null;
        try {

            br = new BufferedReader(new FileReader(inputPath + inputFile));
            String line = br.readLine();

            while (null != line) {
                System.out.println(count);
                count++;
                String newLine = line;

                newLine = line.replace("\"[{'id': ","")
                        .replace("'name': ","")
                        .replace("{'id': ","")
                        .replace("}]\"","")
                        .replace("[{'","'")
                        .replace("{'","'")
                        .replace("\"","");

                split = List.of(newLine.split("},"));

                for(int i=0;i<split.size();i++){
                    boolean validateIsId = isID(split.get(i));
                    if(validateIsId == true){
                        Integer id = idExtract(split.get(i));
                        List<String> splitedList = List.of(split.get(i).split(","));
                        if(!listID.contains(id)){
                            String genderName = isID(splitedList.get(1)) ? splitedList.get(0) : splitedList.get(1);
                            listID.add(id);
                            String newInsert = "INSERT INTO genero (id, nombre) " +
                                    "VALUES("+id+","+genderName+");";
                            fileWritter(newInsert, "genero_inserts.txt");
                        }
                    }
                }
                line = br.readLine();
            }

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (null != br) {
                br.close();
            }
        }
    }
}

