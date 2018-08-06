package Dominio.repositories;

import Dominio.Categoria.Categoria;
import Dominio.entities.ProcessingDataFailedException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class RepositorioCategoria extends Repositorio {
//Aca va la logica para extraer del JSON de categorias las distintas categorias existentes

    String nombreArchivo = "Categoria.json";
    private static RepositorioCategoria instance = new RepositorioCategoria();

    private RepositorioCategoria() { //dejar en privado para que no puedan hacer otra instancia
    }

    public static RepositorioCategoria getInstance() {
        return instance;
    }

    public List<Categoria> obtenerCategorias() throws ProcessingDataFailedException {
        try {
            FileReader file = new FileReader(getJsonFile());
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

    //Separe este metodo para poder mockearlo al momento de testear
    //Lo hice publico para poder mockearlo

    /*public String getJsonFile() {
        return getClass().getClassLoader().getResource("Categoria.json").getFile();
    }*/


}

