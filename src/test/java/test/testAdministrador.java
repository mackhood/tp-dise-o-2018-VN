package test;


import dominio.usuario.Administrador;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class testAdministrador {

    private Administrador unNuevoAdmin;
    private Administrador otroNuevoAdmin;

    @Before
    public void setUp() {
        unNuevoAdmin = new Administrador(" AAAAA ", " BBBBBB ", LocalDate.of(2016, 5, 18));
        otroNuevoAdmin = new Administrador(" XXXX ", " ZZZZ ", LocalDate.of(2014, 9, 20));
    }

    // Tiene sentido hacer test de esta clase que no tiene comportamiento?
    //@Ignore //Los ignoro porque no tiene sentido testear eso porque con el tiempo deja de funcionar.
    @Test
    public void testCantMesesDelAdminCaso1() {
        Assert.assertEquals(26, unNuevoAdmin.cantMesesComoAdmin());
    }

    //@Ignore //Los ignoro porque no tiene sentido testear eso porque con el tiempo deja de funcionar.
    @Test
    public void testCantMesesDelAdminCaso2() {
        Assert.assertEquals(46, otroNuevoAdmin.cantMesesComoAdmin());
    }


}
