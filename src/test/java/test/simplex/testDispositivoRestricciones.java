package test.simplex;

import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.TipoDispositivo;
import dominio.repositories.RepositorioDispositivo;
import org.junit.Assert;
import org.junit.Test;

public class testDispositivoRestricciones {
    Dispositivo disp;


    @Test
    public void testAireAcondicionadoRestriccionMinima() {
        disp = RepositorioDispositivo.getInstance().traerDispositivoInteligenteDeNombreConcreto("Aire Acondicionado", "De 3500 frigorias");

        Assert.assertEquals(90, disp.getRestriccionMinima(), 0);
    }

    @Test
    public void testAireAcondicionadoRestriccionMaxima() {
        disp = new DispositivoEstandar.DispositivoEstandarBuilder("aire").tipoDispositivo(TipoDispositivo.AIREACONDICIONADO).build();
        Assert.assertEquals(360, disp.getRestriccionMaxima(), 0);
    }

    @Test
    public void testLavarropasRestriccionMinima() {
        disp = new DispositivoEstandar.DispositivoEstandarBuilder("aire").tipoDispositivo(TipoDispositivo.LAVARROPAS).build();
        Assert.assertEquals(6, disp.getRestriccionMinima(), 0);
    }

    @Test
    public void testLavarropasRestriccionMaxima() {
        disp = new DispositivoEstandar.DispositivoEstandarBuilder("aire").tipoDispositivo(TipoDispositivo.LAVARROPAS).build();
        Assert.assertEquals(30, disp.getRestriccionMaxima(), 0);
    }
}
