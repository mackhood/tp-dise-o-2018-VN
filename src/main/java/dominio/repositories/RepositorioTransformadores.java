package dominio.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dominio.entities.ProcessingDataFailedException;
import dominio.transformador.Transformador;
import dominio.usuario.Administrador;

import java.io.BufferedReader;
import java.io.FileReader;
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

    public List<Transformador> obtenerTransformadores() throws ProcessingDataFailedException {

        try {
            FileReader file = new FileReader(getJsonFile());
            BufferedReader bufferedReader = new BufferedReader(file);
            Gson gson = new Gson();

            Object jsonObject = gson.fromJson(bufferedReader, Object.class);
            String json = jsonObject.toString();

            Type tipoListaTransformadores = new TypeToken<List<Administrador>>() {
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

}
