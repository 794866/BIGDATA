//https://dzone.com/articles/how-to-convert-csv-to-json-in-java
package Fase1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Utils.Utils.fileWritter;

public class tratandoProductor {
    static final String inputPath = "/home/uri/Documentos/Universidad/BIGDATA/PRACTICAS" +
            "/datasets/datasets/kaggelMoviesDataSet/procesandoDatos/fase2/";

    static final String inputFile="credits.csv";
    static ArrayList<String> rows = new ArrayList();

    public static void executeTrataProductor() throws IOException {
        BufferedReader br = null;
        int count = 0;
        String newInsert="";
        String forError = null;

        try {
            br = new BufferedReader(new FileReader(inputPath + inputFile));
            String line = br.readLine();
            while (null != line) {
                if(!line.equals("crew")){
                    String[] splitedText = line.split("}");
                    Pattern patt = Pattern.compile("\'([^\']*)\'");

                    for(String newLine : splitedText){
                        forError = newLine;
                        if(!newLine.equals("]\"")){

                            Matcher m = patt.matcher(newLine);
                            ArrayList arrayList = new ArrayList();
                            String[] textToGetActorId = newLine.split(":");

                            while (m.find()) {
                                arrayList.add(m.group(1).replace("'","").replace(",","").trim());
                            }

                            for(int i=0;i<textToGetActorId.length;i++){
                                String nombreTrabajador = null;
                                String clv_productor = null;
                                if(textToGetActorId[4] != null){
                                    clv_productor = getId(textToGetActorId[4]);
                                }
                                if(clv_productor != null && !rows.contains(clv_productor)){
                                    rows.add(clv_productor);
                                    String textToNameActor = textToGetActorId[6];
                                    List<String> splitedTextToNameActor = List.of(textToNameActor.split(","));
                                    if(splitedTextToNameActor.get(0) != null){
                                        nombreTrabajador = String.valueOf(splitedTextToNameActor.get(0).
                                                replace("'","")
                                                .replace(",","")
                                                .replace("\"","")
                                                .trim());
                                    }

                                    newInsert = "INSERT INTO productor("+
                                            "clv_productor,nombreTrabajador)"+
                                            "VALUES(" + clv_productor + "," + "'"+ nombreTrabajador + "');";
                                    fileWritter(newInsert , "productor_insert.sql");

                                    if(count == 96){
                                        //System.out.println("next");
                                    }
                                    count++;
                                    System.out.println("productor " + count +"\n");
                                }
                            }
                        }
                    }
                }

                line = br.readLine();
            }

        } catch (Exception e) {
            fileWritter(forError, "errors.txt");
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

