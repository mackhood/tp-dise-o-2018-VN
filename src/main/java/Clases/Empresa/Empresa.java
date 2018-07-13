package Clases.Empresa;


import java.util.List;
import java.util.ArrayList;

import Clases.Usuario.*;

public class Empresa {

	private List<Cliente> contratantes = new ArrayList<>();

	public List<Cliente> getClientes() {
		
		return contratantes;
	}
}
