//https://dzone.com/articles/how-to-convert-csv-to-json-in-java
package DataProcessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Utils.Utils.fileWritter;

public class tratandoEquipoProduccion {
    static final String inputPath = "/home/uri/Documentos/Universidad/BIGDATA/PRACTICAS" +
            "/datasets/datasets/kaggelMoviesDataSet/procesandoDatos/fase2/";

    static final String inputFile="equipoProduccion.csv";
    static ArrayList<String> rows = new ArrayList();

    public static void executetrataEquiposPro() throws IOException {
        BufferedReader br = null;
        int count = 0;
        String newInsert="";

        try {
            br = new BufferedReader(new FileReader(inputPath + inputFile));
            String line = br.readLine();
            while (null != line) {

                String[] splitedText = line.split("}");
                Pattern patt = Pattern.compile("\'([^\']*)\'");

                for(String newLine : splitedText){
                    if(!newLine.equals("]\"")){

                        Matcher m = patt.matcher(newLine);
                        ArrayList arrayList = new ArrayList();
                        String[] textToGetActorId = newLine.split(":");

                        while (m.find()) {
                            arrayList.add(m.group(1).replace("'","").replace(",","").trim());
                        }

                        for(int i=0;i<textToGetActorId.length;i++){
                            String nombreActor = null;
                            String clvActor = null;
                            if(textToGetActorId[5] != null){
                                clvActor = getId(textToGetActorId[4]);
                            }
                            if(clvActor != null && !rows.contains(clvActor)){
                                rows.add(clvActor);
                                String textToNameActor = textToGetActorId[6];
                                List<String> splitedTextToNameActor = List.of(textToNameActor.split(","));
                                if(splitedTextToNameActor.get(0) != null){
                                    nombreActor = String.valueOf(splitedTextToNameActor.get(0).
                                            replace("'","")
                                            .replace(",","")
                                            .replace("\"","")
                                            .trim());
                                }

                                newInsert = "INSERT INTO equipoProduccion("+
                                        "clvEquipoProduccion,nombreTrabajador)"+
                                        "VALUES(" + Integer.parseInt(clvActor) + "," + "'"+ nombreActor + "');";
                                fileWritter(newInsert , "equipoProd_insert.sql");

                                count++;
                                System.out.println("EquipoProduccion " + count +"\n");
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

    static String getId(String cadena) {
        List<Integer> id = new ArrayList<Integer>();
        Matcher finder = Pattern.compile("\\d+").matcher(cadena);
        while (finder.find()) {
            id.add(Integer.parseInt(finder.group()));
        }
        return id.get(0).toString();
    }

}

