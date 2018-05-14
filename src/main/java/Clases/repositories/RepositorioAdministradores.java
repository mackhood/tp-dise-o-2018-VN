package Clases.repositories;

import Clases.Administrador;
import Clases.entities.ProcessingDataFailedException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


public class RepositorioAdministradores {

    public static RepositorioAdministradores instance = new RepositorioAdministradores();

    private RepositorioAdministradores() {
    }

    public List<Administrador> obtenerAdministradores() throws ProcessingDataFailedException {

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            FileReader file = new FileReader(classLoader.getResource("Administradores.json").getFile());
            BufferedReader bufferedReader = new BufferedReader(file);
            Gson gson = new Gson();

            Object jsonObject = gson.fromJson(bufferedReader, Object.class);
            String json = jsonObject.toString();

            Type tipoListaAdmins = new TypeToken<List<Administrador>>() {
            }.getType();
            List<Administrador> admins = gson.fromJson(json, tipoListaAdmins);

            return admins;

        } catch (IOException e) {
            e.printStackTrace();
            throw new ProcessingDataFailedException(e.getLocalizedMessage());
        }
    }


}
