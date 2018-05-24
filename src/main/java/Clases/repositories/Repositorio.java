package Clases.repositories;

import Clases.Usuario.Cliente;
import Clases.entities.ProcessingDataFailedException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public   class Repositorio implements RepositorioInterfaz{

 String archivo;






    public String getJsonFile() {
        return getClass().getClassLoader().getResource(archivo).getFile();
    }

   // public List<//RepositorioInterfaz//> obtenerLista() throws ProcessingDataFailedException {

        try {
            FileReader file = new FileReader(getJsonFile());
            BufferedReader bufferedReader = new BufferedReader(file);
            Gson gson = new Gson();
            Object jsonObject = gson.fromJson(bufferedReader, Object.class);
            String json = jsonObject.toString();
            Type tipoListaEmpleados = new TypeToken<List<Cliente>>() {
            }.getType();

          //  List<ClaseRepositorio> lista = gson.fromJson(json, tipoListaEmpleados); //Y CATEGORIA REPOSITORIO SEA UNA INTERFAZ QUE CUMPLA CLIENTE ADMINISTARDOR Y DISPOSITIVO



            return lista;

        } catch (IOException e) {
            e.printStackTrace();
            throw new ProcessingDataFailedException(e.getLocalizedMessage());
        }

    }}
