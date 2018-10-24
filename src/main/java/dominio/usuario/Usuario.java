package dominio.usuario;

import javax.persistence.*;

@Embeddable
public class Usuario {

    @Column(length = 150)
    private String usuario;
    @Column(length = 150)
    private String contrasenia;

    public Usuario(){}
    public Usuario(String usuario, String contrasenia)
    {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }
}
