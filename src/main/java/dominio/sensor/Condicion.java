package dominio.sensor;

import dominio.regla.Regla;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Tipo_Condicion", discriminatorType = DiscriminatorType.STRING)

public abstract class Condicion {
    @Basic
    protected double valorLimite;
    protected double medicionActual;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Basic
    private String tipo;
    @OneToOne(fetch = FetchType.LAZY)
    private Regla regla;

    public Condicion(Regla regla, double valorLimite, String tipo) {

        this.regla = regla;
        this.valorLimite = valorLimite;
        this.tipo = tipo;
    }

    /*
     * Esto sigue la siguiente logica: al instanciar una condicion, se define lo que
     * llame valor limite y si el valor medido esta por abajo o por arriba de este
     * dependiendo si la cond es por mayor o por menor, se considera cumplida la
     * misma. Lo pense de esta manera porque Franco nos dijo que podiamos tomar
     * todas las mediciones como double y por esto mismo para sensar movimiento
     * utilizo "booleanos" de la forma <0/1>. Todas las mediciones quedan como
     * double en cuanto a su valor y se diferencian por su tipo
     */

    public abstract boolean cumpleCondicion();

    public void actualizar(Sensor unSensor) {

        this.setMedicionActual(unSensor.getValorMedicion());
        regla.chequearCondicionesYEjecutar();
    }

    public void modificarCondicion(double nuevoValorLimite) {

        valorLimite = nuevoValorLimite;
    }

    public String getTipo() {

        return tipo;
    }

    public double getMedicionActual() {

        return medicionActual;
    }

    public void setMedicionActual(double valor) {

        medicionActual = valor;
    }
}