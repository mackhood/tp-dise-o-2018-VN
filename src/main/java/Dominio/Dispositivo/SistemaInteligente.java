package Dominio.Dispositivo;

import Dominio.Actuador.*;
import Dominio.Usuario.Cliente;

import java.util.stream.Collectors;


public class SistemaInteligente {
    public boolean algunDIencendido(Cliente unCliente)
    {
        ConsultaEstaEncendido consultaEstaEncendido = new ConsultaEstaEncendido();
        return unCliente.getDispositivosInteligentes().stream().anyMatch( unDI ->  consultaEstaEncendido.consultarDI(unDI));
    }

    public int cantidadDIencendidos(Cliente unCliente)
    {
        ConsultaEstaEncendido consultaEstaEncendido = new ConsultaEstaEncendido();
        return unCliente.getDispositivosInteligentes().stream().filter(unDI -> consultaEstaEncendido.consultarDI(unDI)).collect(Collectors.toList()).size();
    }

    public int cantidadDIapagados(Cliente unCliente)
    {
        ConsultaEstaApagado consultaEstaApagado = new ConsultaEstaApagado();
        return unCliente.getDispositivosInteligentes().stream().filter(unDI -> consultaEstaApagado.consultarDI(unDI)).collect(Collectors.toList()).size();
    }

    public int cantidadDispositivos(Cliente unCliente)
    {
        return unCliente.cantidadDeDispositivos();
    }
}