package test.db;

import dominio.cargarCliente.ClienteManager;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;

public class Runner {

    public static void main(String[] args) {


        ClienteManager clienteManager = new ClienteManager();
        clienteManager.modificarNombreDeUnDispositivoDelCliente(new Long(1),"ddd","ttt");

        EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    }
    }
