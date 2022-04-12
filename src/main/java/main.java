import static DataProcessing.segundaFase.tratandoActores.executeTratandoActores;
import static DataProcessing.segundaFase.tratandoEquipoProduccion.executetrataEquiposPro;

import java.io.*;

public class main {

    public static void main(String[] args) throws IOException {
        try{
            executeTratandoActores();
            executetrataEquiposPro();
        }catch(Exception e){
            System.out.println(e.fillInStackTrace());
        }
    }
}


