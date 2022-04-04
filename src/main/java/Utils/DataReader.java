package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataReader {

    static final String inputPath = "/home/uri/Documentos/Universidad/BIGDATA/PRACTICAS" +
            "/datasets/datasets/kaggelMoviesDataSet/procesandoDatos";

    static final String inputFile="procesandoGenero.csv";

    public static void executeBuilderGeneroData() throws IOException {
        BufferedReader br = null;
        try {

            br = new BufferedReader(new FileReader(inputPath + inputFile));
            String line = br.readLine();

            while (null != line) {
                String newLine = line;
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
