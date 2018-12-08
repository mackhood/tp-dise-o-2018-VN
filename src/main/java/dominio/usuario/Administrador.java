package dominio.usuario;

import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoEstandar;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Entity
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idAdministrador")
    protected Long id;

    private String nombre;
    private String apellido;
    @Embedded
    private Domicilio domicilio;
    private LocalDate fechaAlta;
    private long numId;
    private String usuario;
    private String contrasenia;

    public Administrador() {
    }
    public Administrador(String nombre, String unApellido, LocalDate fecha,String username , String password) {
        this.nombre = nombre;
        this.apellido = unApellido;
        this.fechaAlta = fecha;
        this.usuario = username;
        this.contrasenia=password;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public LocalDate fechaActual() {
        return LocalDate.now();
    }

    public LocalDate fechaAlta() {
        return fechaAlta;
    }

    public long cantMesesComoAdmin() {
        LocalDate ahora = LocalDate.now();
        return fechaAlta.until(ahora, ChronoUnit.MONTHS);
    }
    public String getContrasenia() {
        return contrasenia;
    }




    }

