package Clases.repositories;

import Clases.Usuario.Cliente;
import Clases.entities.ProcessingDataFailedException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import Clases.Categoria.AsignadorDeCategoria;



public class RepositorioClientes   extends Repositorio implements RepositorioInterfaz{

    String archivo = "Clientes.json";

    public static RepositorioClientes getInstance() {
        return instance;
    }

    private static RepositorioClientes instance = new RepositorioClientes();


    private RepositorioClientes() {
    }


}
