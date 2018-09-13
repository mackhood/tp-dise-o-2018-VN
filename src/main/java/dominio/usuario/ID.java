package dominio.usuario;

import javax.persistence.*;


@Entity
@Table(name = "id")
public class ID {
	@Id
	@GeneratedValue( strategy= GenerationType.AUTO)
	protected Long id;

	@Enumerated(EnumType.STRING)
	TiposId tipoID;
	@Column(length=150)

	String numeroID;

	public ID(TiposId tipoID, String numeroID) {
		this.tipoID = tipoID;
		this.numeroID = numeroID;
	}
}
