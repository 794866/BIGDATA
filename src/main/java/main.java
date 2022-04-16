
import static DataProcessing.tratandoProductor.executeTrataProductor;

import java.io.*;

public class main {

    public static void main(String[] args) throws IOException {
        try{
//            executeTratandoActores();
//            executetrataEquiposPro();
//            executeProcesandoPeliculas();
//            executeTratandoGeneros();
            //executeProcesandoDatosPeliculas();
            executeTrataProductor();
            //executeProcesandoDatosPeliculas();
            //executeTratandoProductorPeliculas();
        }catch(Exception e){
            System.out.println(e.fillInStackTrace());
        }
    }
}


