package dominio.empresa;

import dominio.usuario.Cliente;

import java.util.ArrayList;
import java.util.List;

public class Empresa {

    private List<Cliente> contratantes = new ArrayList<>();

    public List<Cliente> getClientes() {

        return contratantes;
    }
}
