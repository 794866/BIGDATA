import static DataProcessing.TratandoDatosTablaGenero.*;

import java.io.*;

public class main {

    public static void main(String[] args) throws IOException {
        try{
            executeBuilderGeneroData();
        }catch(Exception e){
            System.out.println(e.fillInStackTrace());
        }
    }
}


