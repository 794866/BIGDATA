
import static DataProcessing.DetallePelicula_FactTable.tratandoDetallesPeliculas;
import static DataProcessing.tratandoActores.executeTratandoActores;
import static DataProcessing.tratandoDatosPelicula.executeProcesandoDatosPeliculas;
import static DataProcessing.tratandoGeneros.executeTratandoGeneros;
import static DataProcessing.tratandoProductor.executeTrataProductor;
import static DataProcessing.tratandoProductorPeliculas.executeTratandoProductorPeliculas;
import static normanDataBase.crew.crew;
import static normanDataBase.pelicula.pelicula;
import static normanDataBase.productor.productor;
import static normanDataBase.valoracionPelicula.factTable_norman_database;

import java.io.*;

public class main {

    public static void main(String[] args) throws IOException {
        normanDataBase();
   }

    public static void DatProcessing(){
        try{
            executeTratandoActores();
            executeTratandoGeneros();
            executeProcesandoDatosPeliculas();
            executeTrataProductor();
            tratandoDetallesPeliculas();
            executeTratandoProductorPeliculas();
        }catch(Exception e){
            System.out.println(e.fillInStackTrace());
        }
    }

    public static void normanDataBase(){
        try {
            //productor();
            //actor();
            //pelicula();
            //crew();
            factTable_norman_database();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


