package repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dominio.entities.ProcessingDataFailedException;
import dominio.usuario.Administrador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class RepositorioAdministradores extends Repositorio {


    private static RepositorioAdministradores instance = new RepositorioAdministradores();

    private RepositorioAdministradores() { // dejar en privado para que no puedan hacer otra instancia
        nombreArchivo = "Administradores.json";
    }

    public static RepositorioAdministradores getInstance() {
        return instance;
    }

    public List<Administrador> obtenerAdministradores() throws ProcessingDataFailedException {

        try {
            FileReader file = new FileReader(getJsonFile());
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

    /*
     * public String getJsonFile() { //Separe este metodo para poder mockearlo al
     * momento de testear return
     * getClass().getClassLoader().getResource("Administradores.json").getFile(); }
     */
}
