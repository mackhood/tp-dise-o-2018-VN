package dominio.repositories;

public abstract class Repositorio {

    String nombreArchivo;

    public String getJsonFile(String archivo) { // Separe este metodo para poder mockearlo al momento de testear
        return getClass().getClassLoader().getResource(archivo).getFile();
    }
}
