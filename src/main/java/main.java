import static DataProcessing.procesandoPeliculas.executeProcesandoPeliculas;

import java.io.*;

public class main {

    public static void main(String[] args) throws IOException {
        try{
//            executeTratandoActores();
//            executetrataEquiposPro();
            executeProcesandoPeliculas();
        }catch(Exception e){
            System.out.println(e.fillInStackTrace());
        }
    }
}


