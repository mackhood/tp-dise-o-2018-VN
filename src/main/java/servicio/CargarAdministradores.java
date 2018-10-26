package servicio;

import dominio.repositories.RepositorioAdministradores;
import dominio.usuario.Administrador;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.util.List;

public class CargarAdministradores extends AbstractPersistenceTest implements WithGlobalEntityManager {

    private static CargarAdministradores instance = new CargarAdministradores();

    public static CargarAdministradores getInstance() {
        return instance;
    }

    public void persistirAdministradores() {
        withTransaction(() -> {
            List<Administrador> administradorList = RepositorioAdministradores.getInstance().obtenerAdministradores();
            administradorList.stream().forEach(administrador -> entityManager().persist(administrador));
        });
    }
}
