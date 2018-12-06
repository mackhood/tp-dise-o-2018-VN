package dominio.dispositivo;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idDispositivo")
    protected Long id;

    protected String nombre;
    protected double consumoEstimadoPorHora;
    protected String equipoConcreto;
    protected double horasDeUso = 0;
    @Enumerated
    protected TipoDispositivo tipoDispositivo;

    public double getRestriccionMinima() {
        return tipoDispositivo.getRestriccionMinima();
    }

    public double getRestriccionMaxima() {
        return tipoDispositivo.getRestriccionMaxima();
    }

    public double consumoEstimadoPorHora() {
        return consumoEstimadoPorHora;
    }

    public double getConsumoEstimadoPorHora() {
        return consumoEstimadoPorHora;
    }

    public void setConsumoEstimadoPorHora(double consumoEstimadoPorHora) {
        this.consumoEstimadoPorHora = consumoEstimadoPorHora;
    }

    public double getHorasDeUso() {
        return horasDeUso;
    }

    public void setHorasDeUso(double horasDeUso) {
        this.horasDeUso = horasDeUso;
    }

    public double getConsumoTotal() {

        return consumoEstimadoPorHora * horasDeUso;
    }

    public abstract int getPuntos();

    public String getEquipoConcreto() {
        return equipoConcreto;
    }

    public void setEquipoConcreto(String equipoConcreto) {
        this.equipoConcreto = equipoConcreto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void aumentarConsumoPor(int aumento) {
        consumoEstimadoPorHora = consumoEstimadoPorHora + aumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
