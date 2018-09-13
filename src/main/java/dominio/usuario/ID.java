package dominio.usuario;

import javax.persistence.*;



@Embeddable

public class ID {

	





	@Enumerated(EnumType.STRING)
	TiposId tipoID;
	String numeroID;

	public ID(){}
	public ID(TiposId tipoID, String numeroID) {
		this.tipoID = tipoID;
		this.numeroID = numeroID;
	}
	public ID(){}
}
