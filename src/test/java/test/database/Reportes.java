package test.database;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.dispositivo.Periodo;
import dominio.manager.ClienteManager;
import dominio.manager.DispositivosManager;
import dominio.usuario.Cliente;
import reportes.ReporteConsumoPorDispositivo;
import reportes.ReporteConsumoPorHogar;

public class Reportes implements WithGlobalEntityManager {
	
	@Test
	public void testConsumoPorDispositivoDeUsuario() {
		
		ReporteConsumoPorDispositivo r = new ReporteConsumoPorDispositivo();
		assertEquals(0,r.consumoPorDispositivo(25,9),1);	
	}
	
	@Test
	public void testTotalDispositivosDeUsuario() {
		
		ReporteConsumoPorDispositivo r = new ReporteConsumoPorDispositivo();
		assertEquals(2,r.totalDispositivos(25),0);
	}
	
	@Test
	public void testConsumoDeHogarEnPeriodo() {
		
		Periodo p = new Periodo(LocalDateTime.of(2018,06,8,22,10),LocalDateTime.of(2018,06,9,01,50),null);
		ReporteConsumoPorHogar r = new ReporteConsumoPorHogar();
		assertEquals(0,r.consumoDeHogarEnPeriodo(25,p),0);
	}
	
	
}
