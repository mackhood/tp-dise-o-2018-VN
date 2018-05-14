package Clases.repositories;

import Clases.Cliente;
import Clases.entities.ProcessingDataFailedException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


public class RepositorioClientes {

    public static RepositorioClientes instance = new RepositorioClientes();

    private RepositorioClientes() {
    }

    public void updateClientes(List<Cliente> clientes) {
        clientes.stream().forEach(x -> {
            x.actualizarCategoria();
        });
    }

    public List<Cliente> obtenerClientes() throws ProcessingDataFailedException {

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            FileReader file = new FileReader(classLoader.getResource("Clientes.json").getFile());
            BufferedReader bufferedReader = new BufferedReader(file);
            Gson gson = new Gson();
            Object jsonObject = gson.fromJson(bufferedReader, Object.class);
            String json = jsonObject.toString();
            Type tipoListaEmpleados = new TypeToken<List<Cliente>>() {
            }.getType();

            List<Cliente> clientes = gson.fromJson(json, tipoListaEmpleados);

            this.updateClientes(clientes);

            return clientes;

        } catch (IOException e) {
            e.printStackTrace();
            throw new ProcessingDataFailedException(e.getLocalizedMessage());
        }

    }
}
