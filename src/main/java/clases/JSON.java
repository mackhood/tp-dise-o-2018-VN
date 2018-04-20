package clases;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.google.gson.*;
import com.google.gson.annotations.*;
import com.google.gson.internal.*;
import com.google.gson.internal.bind.*;
import com.google.gson.internal.bind.util.*;
import com.google.gson.reflect.*;
import com.google.gson.stream.*;

import documento.*;

//import com.github.cliftonlabs.json_simple.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
public class JSON
{
	public JSON()
	{
		//this.createJSON();
		//this.devolverClientes();
		//this.createClientesJSON();
	}
	
	public List<Administrador> devolverAdministradores()
	{
		String rutaArchivo = "Administradores.json";
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(rutaArchivo)))
		{
			Gson gson = new Gson();

			Object jsonObject = gson.fromJson(bufferedReader, Object.class);
			String json = jsonObject.toString();

			Type tipoListaAdmins = new TypeToken<List<Administrador>>(){}.getType();
			List<Administrador> admins = gson.fromJson(json, tipoListaAdmins);
			

			return admins;
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<Cliente> devolverClientes()
	{
		String rutaArchivo = "Clientes.json";
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(rutaArchivo)))
		{
			Gson gson = new Gson();

			Object jsonObject = gson.fromJson(bufferedReader, Object.class);
			String json = jsonObject.toString();
			Type tipoListaEmpleados = new TypeToken<List<Cliente>>(){}.getType();
			List<Cliente> clientes = gson.fromJson(json, tipoListaEmpleados);
			
			
			//Cliente unCliente = gson.fromJson(json, Cliente.class);
			//System.out.println(clientes.get(0).nombre());
			//System.out.println(clientes.get(1).nombre());

			return clientes;
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public void createClientesJSON()
	{
		Dispositivo dispositivo1 = new Dispositivo("Heladera",100,true);
		Dispositivo dispositivo2 = new Dispositivo("Microondas",50,false);
		Dispositivo dispositivo3 = new Dispositivo("Lavarropas",34,false);

		List <Dispositivo> dispositivos = new ArrayList();
		dispositivos.add(dispositivo1);
		dispositivos.add(dispositivo2);
		dispositivos.add(dispositivo3);
		Documento doc = new Documento("48158457", TipoDocumento.DNI);
		Cliente unCliente = new Cliente("Ariel","Galvan","galvanariel97","z","av.av", 1, doc,44444444, dispositivos);
		
		Cliente otro = new Cliente("Juan","Apellido","jad","e","avenida",2,doc,48192389,dispositivos);
		
		List<Cliente> clientes = new ArrayList();
		clientes.add(unCliente);
		clientes.add(otro);
		//Gson gson = new Gson();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(clientes);

		
		try(FileWriter file = new FileWriter("clientes.json"))
		{
			file.write(json);
			file.flush();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	public void createAdminsJSON()
	{
		Administrador admin1 = new Administrador("AAAAA","BBBBBB",LocalDate.of(2016, 5, 18));
		Administrador admin2 = new Administrador("XXXX","ZZZZ",LocalDate.of(2014, 9, 20));
		
		List<Administrador> admins = new ArrayList();
		
		admins.add(admin1);
		admins.add(admin2);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(admins);
		
		try(FileWriter file = new FileWriter("administradores.json"))
		{
			file.write(json);
			file.flush();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		new JSON();
	}
}
