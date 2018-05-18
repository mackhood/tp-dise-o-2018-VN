package Clases;

import Clases.entities.ProcessingDataFailedException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String nombre;
    private String apellido;
    private ID identificacion;
    private long telefono;
    private Domicilio domicilio;
    private LocalDate fechaDeAlta;
    private Categoria categoria;
    private String username;
    private String password;
    private double puntosAcumulados = 0;
    private List<Dispositivo> dispositivos = new ArrayList<>();


    public Cliente(String unNombre, String unApellido, String username, ID id, Domicilio unDomicilio, long unTelefono,
                   List<Dispositivo> listaDispositivos) {

        this.nombre = unNombre;
        this.apellido = unApellido;
        this.identificacion = id;
        this.username = username;
        this.domicilio = unDomicilio;
        this.telefono = unTelefono;
        this.dispositivos = listaDispositivos;
        this.fechaDeAlta = LocalDate.now();
        this.actualizarCategoria();
    }

    public void actualizarCategoria() {

        try {
            AsignadorDeCategoria asignadorDeCategoria = this.asignadorDeCategoria();
            this.setCategoria(asignadorDeCategoria.definirCategoriaPara(this));
        } catch (ProcessingDataFailedException e) {
            e.printStackTrace();
        }
    }

    private AsignadorDeCategoria asignadorDeCategoria = AsignadorDeCategoria.getInstance();

    public AsignadorDeCategoria asignadorDeCategoria() {
        return AsignadorDeCategoria.getInstance();
    }


    public void agregarModuloAdaptador(DispositivoEstandar disp) {
        disp.agregarAdaptadorInteligente();
        this.sumarPuntos(10);
    }

    public boolean algunDispositivoEncendido() {

        return dispositivos.stream().anyMatch(disp -> disp.esCiertoEstado(EstadoDispositivoEnum.ENCENDIDO));
    }

    public long cantidadDeDispositivosEncendidos() {

        return dispositivos.stream().filter(disp -> disp.esCiertoEstado(EstadoDispositivoEnum.ENCENDIDO)).count();
    }

    public long cantidadDeDispositivosApagados() {

        return dispositivos.stream().filter(disp -> disp.esCiertoEstado(EstadoDispositivoEnum.APAGADO)).count();
    }

    public int cantidadDeDispositivos() {

        return dispositivos.size();
    }

    public void agregarDispositivo(Dispositivo disp) {

        dispositivos.add(disp);
        this.sumarPuntos(15);
    }

    public void sumarPuntos(int puntos) {
        puntosAcumulados = puntosAcumulados + puntos;
    }

    public void usarDispositivo(Dispositivo dispositivo, int cantHorasEstimativa) {
        dispositivo.serUsado(cantHorasEstimativa);
    }

    public double consumoEnergeticoTotal() {

        return dispositivos.stream().mapToDouble(disp -> disp.getConsumoTotal()).sum();
    }

    public double obtenerGastosAproximados() {

        this.actualizarCategoria();
        return categoria.calcularCostosPara(this);
    }

    public void setCategoria(Categoria unaCategoria) {

        categoria = unaCategoria;
    }

    public Categoria getCategoria() {

        return this.categoria;
    }

    public String nombreCategoria() {

        return this.categoria.getNombreCategoria();
    }

    public String nombre() {
        return this.nombre;
    }


}
