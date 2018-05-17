package test;


import Clases.Administrador;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class testAdministrador {

	private Administrador unNuevoAdmin;
    private Administrador otroNuevoAdmin;

	@Before
	public void setUp() {
         unNuevoAdmin =  new  Administrador ( " AAAAA " , " BBBBBB " , LocalDate.of ( 2016 , 5 , 18 ));
         otroNuevoAdmin =  new  Administrador ( " XXXX " , " ZZZZ " , LocalDate.of ( 2014 , 9 , 20 ));
	}
	@Test
    public void testCantMesesDelAdminCaso1() {

        Assert.assertEquals(23,unNuevoAdmin.cantMesesComoAdmin());
    }
    @Test
    public void testCantMesesDelAdminCaso2() {


        Assert.assertEquals(43,otroNuevoAdmin.cantMesesComoAdmin());
    }


}
