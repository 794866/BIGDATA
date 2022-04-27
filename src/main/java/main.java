
import static Fase1.DetallePelicula_FactTable.tratandoDetallesPeliculas;
import static Fase1.tratandoActores.executeTratandoActores;
import static Fase1.tratandoDatosPelicula.executeProcesandoDatosPeliculas;
import static Fase1.tratandoGeneros.executeTratandoGeneros;
import static Fase1.tratandoProductor.executeTrataProductor;
import static Fase1.tratandoProductorPeliculas.executeTratandoProductorPeliculas;
import static Fase2.valoracionPelicula.factTable_norman_database;

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


