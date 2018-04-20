package clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import clases.*;
import documento.*;
import residencial.*;
import categorias.*;

public class Cliente extends Usuario {
	Documento documento;
	int telefonoContacto;
	Residencial residencia;
	List<Dispositivo> dispositivos = new ArrayList();

	public Cliente(String unNombre, String unApellido,String unNombreUsuario, String unaContrasenia,
			String unDomicilio,int unId, Documento doc, int unTelefono, List<Dispositivo> dispos) 
	{
		nombre = unNombre;
		apellido = unApellido;
		dispositivos = dispos;
		fechaAlta = LocalDate.now();
		nombreUsuario = unNombreUsuario;
		contrasenia = unaContrasenia;
		domicilio = unDomicilio;
		id = unId;
		documento = doc;
		telefonoContacto = unTelefono;
		this.queCategoriaSoy();		
	}

	public void queCategoriaSoy()
	{
		ResidenciasConstantes x = new ResidenciasConstantes();
		residencia = x.categoriasResidenciales().stream().filter(cat -> cat.cumpleCondicionConsumoMensual(this)).collect(Collectors.toList()).get(0);
	}

	public double facturacionEstimada() 
	{
		return residencia.facturacionEstimada(this);
	}

	public void agregarDispositivo(Dispositivo dispositivo) 
	{
		dispositivos.add(dispositivo);
		this.queCategoriaSoy();
	}

	public Boolean algunDispositivoEncendido() 
	{
		return dispositivos.stream().anyMatch(dispo -> dispo.estaEncendido);
	}

	public int cantidadDispositivosEncendidos() 
	{
		return dispositivos.stream().filter(dispo -> dispo.estaEncendido()).collect(Collectors.toList()).size();
	}

	public int cantidadDispositivos() 
	{
		return dispositivos.size();
	}

	public int cantidadDispositivosApagados() 
	{
		return dispositivos.stream().filter(dispo -> !dispo.estaEncendido()).collect(Collectors.toList()).size();
	}

	public double consumoEnergetico() 
	{
		return dispositivos.stream().map(dis -> dis.kWperH()).reduce(0.0, (acum, kws) -> acum + kws);
		
	}

}
