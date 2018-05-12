package Clases.repositories;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Clases.Administrador;



public class RepositorioAdministradores {
    
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
