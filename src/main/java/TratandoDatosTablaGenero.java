import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TratandoDatosTablaGenero {

    static final String inputPath = "/home/uri/Documentos/Universidad/BIGDATA/PRACTICAS" +
            "/datasets/datasets/kaggelMoviesDataSet/";
    static final String outputPath = "/home/uri/Documentos/Universidad/" +
            "BIGDATA/PRACTICAS/sctipts/INSERTS/";

    static final String inputFile="procesando.csv";
    static final String outputFile="genero_inserts.txt";

    static List<String> split;
    static ArrayList<Integer> listID = new ArrayList();

    public static void executeBuilderGeneroData() throws IOException {
        BufferedReader br = null;
        try {

            br = new BufferedReader(new FileReader(inputPath + inputFile));
            String line = br.readLine();
            while (null != line) {
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
                            fileWritter(newInsert);
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



    static boolean isID(String word) {
        List<Integer> numberList = new ArrayList<Integer>();
        Matcher finder = Pattern.compile("\\d+").matcher(word);
        while (finder.find()) {
            return true;
        }
        return false;
    }

    static int idExtract(String word) {
        Matcher finder = Pattern.compile("\\d+").matcher(word);
        while (finder.find()) {
            return Integer.parseInt(finder.group());
        }
        return 0;
    }

    static void fileWritter(String newInsert){
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            File file = new File(outputPath + outputFile);
            // Si el archivo no existe, se crea!
            if (!file.exists()) {
                file.createNewFile();
            }
            // flag true, indica adjuntar información al archivo.
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(newInsert+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Cierra instancias de FileWriter y BufferedWriter
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

