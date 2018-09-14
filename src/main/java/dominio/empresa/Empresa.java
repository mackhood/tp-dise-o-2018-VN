package dominio.empresa;

import java.util.List;
import java.util.ArrayList;

import dominio.usuario.*;

public class Empresa {

	private List<Cliente> contratantes = new ArrayList<>();

	public List<Cliente> getClientes() {

		return contratantes;
	}
}
