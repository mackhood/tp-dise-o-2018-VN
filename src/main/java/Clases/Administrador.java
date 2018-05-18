package Clases;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Administrador {

    private String nombre;
    private String apellido;
    private Domicilio domicilio;
    private LocalDate fechaAlta;
    private long numId;
    private String username;
    private String password;

    public Administrador(String nombre, String unApellido, LocalDate fecha) {
        this.nombre = nombre;
        this.apellido = unApellido;
        this.fechaAlta = fecha;
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

}
