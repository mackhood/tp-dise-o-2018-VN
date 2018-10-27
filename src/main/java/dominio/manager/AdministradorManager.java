package dominio.manager;

import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.usuario.*;
import dominio.zonageografica.Ubicacion;
import org.apache.commons.lang3.ObjectUtils;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



    public class AdministradorManager implements WithGlobalEntityManager, TransactionalOps {


        private static dominio.manager.AdministradorManager instance = new dominio.manager.AdministradorManager();

        private AdministradorManager() {
        }

        public static dominio.manager.AdministradorManager getInstance() {
            return instance;
        }

        public void persistirAdminDePrueba() {
            withTransaction(() -> {
                Domicilio domicilio = new Domicilio("av cordoba", 1234, 7, 'A');


                Administrador unAdministrador = new Administrador("ger", "jugo", LocalDate.of(2016, 5, 18),"gerjor","1234");



                entityManager().persist(unAdministrador);
                entityManager().getTransaction().commit();
            });
        }

        public Administrador getAdministradorDeLaBDPorUsuario(String username){

            Administrador administrador = entityManager().createQuery("from Administrador where usuario='"+username+"'" ,Administrador.class).getSingleResult();

            return administrador;

        }
    }











