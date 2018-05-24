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

public class RepositorioAdministradores <Administrador> extends Repositorio<Administrador> {

    String archivo = "Administradores.json";




        private static RepositorioAdministradores instance = new RepositorioAdministradores();

        private RepositorioAdministradores() { //dejar en privado para que no puedan hacer otra instancia
        }

        public static RepositorioAdministradores getInstance() {
            return instance;
        }



}
