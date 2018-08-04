package Clases.repositories;

import Clases.Usuario.Administrador;
import Clases.entities.ProcessingDataFailedException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public abstract class Repositorio {

    String nombreArchivo;
    /*
    public List<TypeRepo> obtenerLista() throws ProcessingDataFailedException{
        try {
            FileReader file = new FileReader(getJsonFile());
            BufferedReader bufferedReader = new BufferedReader(file);
            Gson gson = new Gson();

            Object jsonObject = gson.fromJson(bufferedReader, Object.class);
            String json = jsonObject.toString();

            Type tipoLista = new TypeToken<List<TypeRepo>>() {
            }.getType();
            List<TypeRepo> lista = gson.fromJson(json, tipoLista);

            return lista;

        } catch (IOException e) {
            e.printStackTrace();
            throw new ProcessingDataFailedException(e.getLocalizedMessage());
        }
    }
    */
    public String getJsonFile() { //Separe este metodo para poder mockearlo al momento de testear
        return getClass().getClassLoader().getResource(nombreArchivo).getFile();
    }
}
