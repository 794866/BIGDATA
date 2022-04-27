package Fase1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Utils.Utils.fileWritter;

public class tratandoGeneros {

    static final String inputPath = "/home/uri/Documentos/Universidad/BIGDATA/PRACTICAS" +
            "/datasets/datasets/kaggelMoviesDataSet/procesandoDatos/fase2/";

    static final String inputFile="tratando_generos.csv";
    static ArrayList<String> rows = new ArrayList();

    public static void executeTratandoGeneros() throws IOException {
        BufferedReader br = null;
        int count = 0;
        try {
            br = new BufferedReader(new FileReader(inputPath + inputFile));
            String line = br.readLine();

            while (null != line) {

                String[] splitedLine = line.split("},");

                if(!splitedLine[0].equals("genres")){


                    for(int i=0;i<splitedLine.length;i++){
                        String id=null, name=null;
                        String[] genderLine = splitedLine[i].split(",");

                        id = genderLine[0].replace("[{'id': ","")
                                .replace("{'id': ","")
                                .replace("}]","").trim();

                        name = genderLine[1].replace(" 'name': ", "")
                                .replace("}]","").trim();

                        if(!id.equals("") && !name.equals("")){
                            if(!rows.contains(id)){
                                rows.add(id);
                                String newInsert = "INSERT INTO genero(ID,nombre) VALUES("
                                        + Integer.parseInt(id) + "," + name + ");";
                                fileWritter(newInsert,"genero_insert.sql");
                            }

                        }
                        System.out.println(id + " - " + name);
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
