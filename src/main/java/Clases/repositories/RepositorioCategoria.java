package Clases.repositories;




public class RepositorioCategoria  extends Repositorio implements RepositorioInterfaz {

    String archivo = "Categoria.json";

    private static RepositorioCategoria instance = new RepositorioCategoria();

    private RepositorioCategoria() {
    }

    public static RepositorioCategoria getInstance() {
        return instance;
    }


}

