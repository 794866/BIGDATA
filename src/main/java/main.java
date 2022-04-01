import com.opencsv.CSVReader;

import java.io.*;

public class main {

    public static void main(String[] args) throws IOException {
        try{
            TratandoDatosTablaGenero.executeBuilderGeneroData();
        }catch(Exception e){
            System.out.println(e.fillInStackTrace());
        }
    }
}


