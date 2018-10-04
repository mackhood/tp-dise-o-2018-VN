package dominio.zonageografica;

import javax.persistence.*;

@Entity
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @Column(name = "PosicionX", nullable = false, length = 20)
    private double posicionX;
    @Column(name = "PosicionY", nullable = false, length = 20)
    private double posicionY;

    public Ubicacion() {
    }

    public Ubicacion(double posicionX, double posicionY) {

        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public Double calcularDistancia(Ubicacion ubicacionCliente) {

        double posicionXcliente = ubicacionCliente.getPosicionX();
        double posicionYcliente = ubicacionCliente.getPosicionX();
        return Math.hypot(posicionXcliente - posicionX, posicionYcliente - posicionY);

    }

    public double getPosicionX() {
        return posicionX;
    }

    public double getPosicionY() {
        return posicionY;
    }

}
