//https://dzone.com/articles/how-to-convert-csv-to-json-in-java
package DataProcessing.tercerFase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Utils.Utils.fileWritter;

public class tratandoEquipoProduccion {
    static final String inputPath = "/home/uri/Documentos/Universidad/BIGDATA/PRACTICAS" +
            "/datasets/datasets/kaggelMoviesDataSet/procesandoDatos/fase2/";

    static final String inputFile="equipoProduccion.csv";
    static ArrayList<String> rows = new ArrayList();

    public static void TerceraFasetrataEquiposPro() throws IOException {
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

                        while (m.find()) {
                            arrayList.add(m.group(1).replace("'","").replace(",","").trim());
                        }

                        if(!arrayList.get(1).equals("") && !arrayList.get(9).equals("")){
                            for(int i=0;i<arrayList.size();i++){
                                if(!rows.contains(arrayList.get(1))){
                                    rows.add(String.valueOf(arrayList.get(1)));
                                    newInsert = "INSERT INTO Actor("+
                                        "clvActor,nombreActor)"+
                                        "VALUES('" + arrayList.get(1) + "'," + "'"+ arrayList.get(9) + "');";
                                    fileWritter(newInsert , "equipoProd_insert.sql");
                                    newInsert = "";
                                    count++;
                                    System.out.println(count +"\n");
                                }
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

