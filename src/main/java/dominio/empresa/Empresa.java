package Dominio.Empresa;


import java.util.List;
import java.util.ArrayList;

import Dominio.Usuario.*;

public class Empresa {

	private List<Cliente> contratantes = new ArrayList<>();

	public List<Cliente> getClientes() {
		
		return contratantes;
	}
}
