package Clases.repositories;

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
import Clases.*;

//import com.github.cliftonlabs.json_simple.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class RepositorioClientes {
	public RepositorioClientes() {
		// this.createAdminsJSON();
		// this.createClientesJSON();
		// this.devolverClientes();
		// this.createClientesJSON();
	}

	public List<Administrador> devolverAdministradores() {
		String rutaArchivo = "Administradores.json";
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(rutaArchivo))) {
			Gson gson = new Gson();

			Object jsonObject = gson.fromJson(bufferedReader, Object.class);
			String json = jsonObject.toString();

			Type tipoListaAdmins = new TypeToken<List<Administrador>>() {
			}.getType();
			List<Administrador> admins = gson.fromJson(json, tipoListaAdmins);

			return admins;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void updateClientes(List<Cliente> clientes) {
		clientes.stream().forEach(x -> {
			x.actualizarCategoria();
		});
	}

	public List<Cliente> devolverClientes() {
		String rutaArchivo = "Clientes.json";
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(rutaArchivo))) {
			Gson gson = new Gson();

			Object jsonObject = gson.fromJson(bufferedReader, Object.class);
			String json = jsonObject.toString();
			Type tipoListaEmpleados = new TypeToken<List<Cliente>>() {
			}.getType();

			List<Cliente> clientes = gson.fromJson(json, tipoListaEmpleados);

			this.updateClientes(clientes);

			return clientes;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void createClientesJSON() {
		Dispositivo dispositivo1 = new Dispositivo("Heladera", 100, false);
		Dispositivo dispositivo2 = new Dispositivo("Microondas", 50, false);
		Dispositivo dispositivo3 = new Dispositivo("Lavarropas", 34, false);

		List<Dispositivo> dispositivos = new ArrayList();
		dispositivos.add(dispositivo1);
		dispositivos.add(dispositivo2);
		dispositivos.add(dispositivo3);
		ID unDoc = new ID(TiposIdEnum.DNI, 48158457);
		Domicilio unDomicilio = new Domicilio("Av.X", 1234, 7, 'a');
		Cliente unCliente = new Cliente("Ema", "Emma", "emaema2016", unDoc, unDomicilio, 42781596, dispositivos);

		Cliente otro = new Cliente("nombreASD", "apellidoASD", "ASDASD99", unDoc, unDomicilio, 4178965, dispositivos);

		List<Cliente> clientes = new ArrayList();
		clientes.add(unCliente);
		clientes.add(otro);
		// Gson gson = new Gson();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(clientes);

		try (FileWriter file = new FileWriter("Clientes.json")) {
			file.write(json);
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void createAdminsJSON() {
		Administrador admin1 = new Administrador("AAAAA", "BBBBBB", LocalDate.of(2016, 5, 18));
		Administrador admin2 = new Administrador("XXXX", "ZZZZ", LocalDate.of(2014, 9, 20));

		List<Administrador> admins = new ArrayList();

		admins.add(admin1);
		admins.add(admin2);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(admins);

		try (FileWriter file = new FileWriter("Administradores.json")) {
			file.write(json);
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
