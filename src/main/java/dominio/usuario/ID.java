package dominio.usuario;

import javax.persistence.*;


@Embeddable
//@Table(name = "id")
public class ID {

	//@Id
	//@GeneratedValue( strategy= GenerationType.AUTO)
	//@Column(name="idID")
	//protected Long idID;


	@Enumerated(EnumType.STRING)
	TiposId tipoID;
	@Column(length=150)

	String numeroID;

	public ID(){}
	public ID(TiposId tipoID, String numeroID) {
		this.tipoID = tipoID;
		this.numeroID = numeroID;
	}
}
