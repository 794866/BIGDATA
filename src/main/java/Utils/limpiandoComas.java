//https://dzone.com/articles/how-to-convert-csv-to-json-in-java
package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Utils.Utils.fileWritter;

public class limpiandoComas {
    static final String inputPath = "/home/uri/Documentos/Universidad/BIGDATA/PRACTICAS" +
            "/datasets/datasets/kaggelMoviesDataSet/procesandoDatos/fase2/";

    static final String inputFile="actor.csv";
    static ArrayList<String> rows = new ArrayList();

    public static void executelimpiandoComas() throws IOException {
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

                        if(!arrayList.get(4).equals("") && !arrayList.get(8).equals("")){
                            for(int i=0;i<arrayList.size();i++){
                                if(!rows.contains(arrayList.get(4))){
                                    rows.add(String.valueOf(arrayList.get(4)));
                                    newInsert = "INSERT INTO Actor("+
                                        "clvActor,nombreActor)"+
                                        "VALUES('" + arrayList.get(4) + "'," + "'"+ arrayList.get(8) + "');";
                                    fileWritter(newInsert , "actor_insert.sql");
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

