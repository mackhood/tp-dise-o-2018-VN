package test;

import Clases.Dispositivo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;


@PrepareForTest(Dispositivo.class)


@RunWith(PowerMockRunner.class)


public class testDispositivo {
    private Dispositivo dis;

    @Before
    public  void setUp(){
        dis = Mockito.mock(Dispositivo.class);
    }

    @Test
    public void test_Dispositivo() throws Throwable {

        when(dis.getConsumoTotal()).thenReturn(20.1);
        PowerMockito.whenNew(Dispositivo.class).withArguments(Mockito.anyString(),Mockito.anyDouble(),Mockito.anyBoolean()).thenReturn(dis);
        assertThat(new Dispositivo("heladera",40,true).getConsumoTotal(), equalTo(20.1));
    }
    @Test
    public void  test_calcularIntervalo(){
        when(dis.calcularIntervalo(LocalTime.of(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyInt()),LocalTime.of(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyInt()))).thenReturn(25);
        assertThat(dis.calcularIntervalo(LocalTime.of(15,20,50),LocalTime.of(17,20,50)), equalTo(20.1));
    }


}
