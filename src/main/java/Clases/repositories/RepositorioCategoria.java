package Clases.repositories;

import Clases.Categoria;
import Clases.entities.ProcessingJsonFailedException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class RepositorioCategoria {
//Aca va la logica para extraer del JSON de categorias las distintas categorias existentes

    //TODO: Hacer singleton este repositorio
    public RepositorioCategoria() {
    }

    public List<Categoria> obtenerCategorias() throws ProcessingJsonFailedException {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            FileReader file = new FileReader(classLoader.getResource("Categoria.json").getFile());
            BufferedReader bufferedReader = new BufferedReader(file);

            Gson gson = new Gson();
            Object jsonObject = gson.fromJson(bufferedReader, Object.class);
            String json = jsonObject.toString();
            Type tipoListaCategorias = new TypeToken<List<Categoria>>() {
            }.getType();
            List<Categoria> categorias = gson.fromJson(json, tipoListaCategorias);

            return categorias;

        } catch (FileNotFoundException e) {
            throw new ProcessingJsonFailedException(e.getLocalizedMessage());
        }
    }
}

