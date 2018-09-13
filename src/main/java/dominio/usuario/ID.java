package dominio.usuario;

import javax.persistence.*;


@Entity
public class ID {

	@Enumerated(EnumType.STRING)
	TiposId tipoID;
	String numeroID;

	public ID(TiposId tipoID, String numeroID) {
		this.tipoID = tipoID;
		this.numeroID = numeroID;
	}
	public ID(){}
}
