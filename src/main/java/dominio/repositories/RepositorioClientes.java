package dominio.repositories;

import dominio.categoria.AsignadorDeCategoria;
import dominio.usuario.Cliente;
import dominio.entities.ProcessingDataFailedException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class RepositorioClientes extends Repositorio {

	public static RepositorioClientes getInstance() {
		return instance;
	}

	private static RepositorioClientes instance = new RepositorioClientes();

	private RepositorioClientes() { // dejar en privado para que no puedan hacer otra instancia
		nombreArchivo = "Clientes.json";
	}

	public void updateClientes(List<Cliente> clientes) {
		AsignadorDeCategoria asignadorDeCategoria = AsignadorDeCategoria.getInstance();
		clientes.stream().forEach(asignadorDeCategoria::actualizarPara);
	}

	public List<Cliente> obtenerClientes() throws ProcessingDataFailedException {

		try {
			FileReader file = new FileReader(getJsonFile());
			BufferedReader bufferedReader = new BufferedReader(file);
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
			throw new ProcessingDataFailedException(e.getLocalizedMessage());
		}

	}

	/*
	 * private String getJsonFile() { //Separe este metodo para poder mockearlo al
	 * momento de testear return
	 * getClass().getClassLoader().getResource("Clientes.json").getFile(); }
	 */
}
