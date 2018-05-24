package Clases.repositories;




public class RepositorioCategoria  extends Repositorio implements RepositorioInterfaz {

    String archivo = "Categoria.json";
Class clase = Categoria
    private static RepositorioCategoria instance = new RepositorioCategoria();

    private RepositorioCategoria() {
    }

    public static RepositorioCategoria getInstance() {
        return instance;
    }


}

