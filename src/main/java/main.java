import static DataProcessing.EquipoProduccion.ProcesandoEquipoProduccion.*;

import java.io.*;

public class main {

    public static void main(String[] args) throws IOException {
        try{
            //executeBuilderGeneroData();
            executeProcesandoEquipoProduccion();
        }catch(Exception e){
            System.out.println(e.fillInStackTrace());
        }
    }
}


