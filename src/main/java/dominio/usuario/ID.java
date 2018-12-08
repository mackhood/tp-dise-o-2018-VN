package dominio.usuario;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Embeddable
public class ID {



    @Enumerated(EnumType.STRING)
    TiposId tipoID;
    @Column(length = 150)

    String numeroID;

    public ID() {
    }

    public ID(TiposId tipoID, String numeroID) {
        this.tipoID = tipoID;
        this.numeroID = numeroID;
    }
}
