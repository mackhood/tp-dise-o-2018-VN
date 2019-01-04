package dominio.dispositivo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoDispositivo {
    
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected long id;
	
	@Column(unique=true)
	protected String nombre;
    protected double restriccionMinima;
    protected double restriccionMaxima;

    TipoDispositivo() {}

    public TipoDispositivo(String nombre, double restriccionMinima, double restriccionMaxima) {
        this.nombre = nombre;
    	this.restriccionMinima = restriccionMinima;
        this.restriccionMaxima = restriccionMaxima;
    }
    
    public String getNombre()
    {
    	return nombre;
    }
    
    public double getRestriccionMinima() {
        return restriccionMinima;
    }

    public double getRestriccionMaxima() {
        return restriccionMaxima;
    }

}
