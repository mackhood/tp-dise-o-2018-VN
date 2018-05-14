package test;

import Clases.Dispositivo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

;

@PrepareForTest(Dispositivo.class)


@RunWith(PowerMockRunner.class)


public class testRepositorioAdministrador {

	@Before
	public void setUp(){

	}

	@Test
	public void test_mocked() throws Throwable {
		Dispositivo dis = mock(Dispositivo.class);
		when(dis.getConsumoTotal()).thenReturn(20.1);
		PowerMockito.whenNew(Dispositivo.class).withArguments(Mockito.anyString(),Mockito.anyDouble(),Mockito.anyBoolean()).thenReturn(dis);
		assertThat(new Dispositivo("heladera",40,true).getConsumoTotal(), equalTo(20.1));
	}


}
