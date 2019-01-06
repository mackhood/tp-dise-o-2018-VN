package repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dominio.entities.ProcessingDataFailedException;
import dominio.transformador.Transformador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class RepositorioTransformadores extends Repositorio {

    private static RepositorioTransformadores instance = new RepositorioTransformadores();

    private RepositorioTransformadores() { // dejar en privado para que no puedan hacer otra instancia
        nombreArchivo = "Transformadores.json";
    }

    public static RepositorioTransformadores getInstance() {
        return instance;
    }
    public List<Transformador> obtenerTransformadores(String archivo) throws ProcessingDataFailedException {
        return  obtenerTransformadoresDelJson(archivo);
    }
    public List<Transformador> obtenerTransformadores() throws ProcessingDataFailedException {
        return  obtenerTransformadoresDelJson(this.nombreArchivo);
    }

    private List<Transformador> obtenerTransformadoresDelJson(String archivo) throws ProcessingDataFailedException {
        try {
            FileReader file = new FileReader(archivo);
            BufferedReader bufferedReader = new BufferedReader(file);
            Gson gson = new Gson();

            Object jsonObject = gson.fromJson(bufferedReader, Object.class);
            String json = jsonObject.toString();

            Type tipoListaTransformadores = new TypeToken<List<Transformador>>() {
            }.getType();
            List<Transformador> transformadores = gson.fromJson(json, tipoListaTransformadores);

            return transformadores;

        } catch (IOException e) {
            e.printStackTrace();
            throw new ProcessingDataFailedException(e.getLocalizedMessage());
        }
    }

    public int cantidadTransformadores() {

        return this.obtenerTransformadores().size();
    }

    public List<Transformador> nuevoTransformador(Transformador transformador,String archivo) throws IOException {
        Gson gson = new Gson();
        List<Transformador> transformadores = this.obtenerTransformadoresDelJson(archivo);
        transformadores.add(transformador);
        String jsonString = gson.toJson(transformadores);
        FileWriter fileWriter = new FileWriter(archivo);
        //Saco el append
        //FileWriter fileWriter = new FileWriter(archivo,true);
        fileWriter.write(jsonString);
        fileWriter.close();
        List<Transformador> transformadoresConElNuevoTransformador = this.obtenerTransformadores(archivo);

        return transformadoresConElNuevoTransformador;


    }

}
