package Clases.repositories;


import Clases.Categoria.Categoria;


public class RepositorioCategoria  extends Repositorio implements RepositorioInterfaz {

    String archivo = "Categoria.json";
    private static RepositorioCategoria instance = new RepositorioCategoria();

    Categoria a;

    {
        a = new Categoria();
    }

    Class b =a.getClass();

    private RepositorioCategoria() {
    }

    public static RepositorioCategoria getInstance() {
        return instance;
    }


}

