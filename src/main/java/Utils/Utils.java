package Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    static final String outputPath = "/home/uri/Documentos/Universidad/" +
            "BIGDATA/PRACTICAS/sctipts/INSERTS/";


    public static void fileWritter(String newInsert, String nameFile){
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            File file = new File(outputPath + nameFile);
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

    public static boolean isID(String word) {
        List<Integer> numberList = new ArrayList<Integer>();
        Matcher finder = Pattern.compile("\\d+").matcher(word);
        while (finder.find()) {
            return true;
        }
        return false;
    }

    public static int idExtract(String word) {
        Matcher finder = Pattern.compile("\\d+").matcher(word);
        while (finder.find()) {
            return Integer.parseInt(finder.group());
        }
        return 0;
    }
}
