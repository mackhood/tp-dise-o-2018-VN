package dominio.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dominio.categoria.Categoria;
import dominio.entities.ProcessingDataFailedException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class RepositorioCategoria extends Repositorio {
    // Aca va la logica para extraer del JSON de categorias las distintas categorias
    // existentes


    private static RepositorioCategoria instance = new RepositorioCategoria();

    private RepositorioCategoria() { // dejar en privado para que no puedan hacer otra instancia
        nombreArchivo = "Categoria.json";
    }

    public static RepositorioCategoria getInstance() {
        return instance;
    }

    public List<Categoria> obtenerCategorias() throws ProcessingDataFailedException {
        try {
            FileReader file = new FileReader(getJsonFile(nombreArchivo));
            BufferedReader bufferedReader = new BufferedReader(file);

            Gson gson = new Gson();
            Object jsonObject = gson.fromJson(bufferedReader, Object.class);
            String json = jsonObject.toString();
            Type tipoListaCategorias = new TypeToken<List<Categoria>>() {
            }.getType();
            List<Categoria> categorias = gson.fromJson(json, tipoListaCategorias);

            return categorias;

        } catch (FileNotFoundException e) {
            throw new ProcessingDataFailedException(e.getLocalizedMessage());
        }
    }
}
