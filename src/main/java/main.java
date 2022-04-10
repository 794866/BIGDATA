import static DataProcessing.EquipoProduccion.ProcesandoActores.executeProcesandoActores;
import static DataProcessing.EquipoProduccion.ProcesandoEquipoProduccion.*;
import static DataProcessing.ProcesandoGenero.*;
import static Utils.limpiandoComas.executelimpiandoComas;

import java.io.*;

public class main {

    public static void main(String[] args) throws IOException {
        try{
            //executeBuilderGeneroData();
            //executeProcesandoEquipoProduccion();
            //executeProcesandoActores();
            executelimpiandoComas();
        }catch(Exception e){
            System.out.println(e.fillInStackTrace());
        }
    }
}


