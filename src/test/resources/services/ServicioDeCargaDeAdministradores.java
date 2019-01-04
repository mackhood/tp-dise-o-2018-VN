package services;

import dominio.repositories.RepositorioAdministradores;
import dominio.usuario.Administrador;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.util.List;

public class ServicioDeCargaDeAdministradores extends AbstractPersistenceTest implements WithGlobalEntityManager {

    private static  ServicioDeCargaDeAdministradores instance = new ServicioDeCargaDeAdministradores();

    public static ServicioDeCargaDeAdministradores getInstance() {
        return instance;
    }

    public void persistirAdministradores() {
        withTransaction(() -> {
            List<Administrador> administradorList = RepositorioAdministradores.getInstance().obtenerAdministradores();
            administradorList.stream().forEach(administrador -> entityManager().persist(administrador));
        });
    }
}
