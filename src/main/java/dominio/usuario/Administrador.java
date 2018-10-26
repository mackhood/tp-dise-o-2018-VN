package dominio.usuario;

import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoEstandar;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Entity
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idAdministrador")
    protected Long id;

    private String nombre;
    private String apellido;
    @Embedded
    private Domicilio domicilio;
    private LocalDate fechaAlta;
    private long numId;
    private String usuario;
    private String contraseña;
    //private DispositivoABM dispositivoABM;

    public Administrador() {
    }
    public Administrador(String nombre, String unApellido, LocalDate fecha,String username , String password) {
        this.nombre = nombre;
        this.apellido = unApellido;
        this.fechaAlta = fecha;
        //this.dispositivoABM = new DispositivoABM();
        this.usuario = username;
        this.contraseña=password;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public LocalDate fechaActual() {
        return LocalDate.now();
    }

    public LocalDate fechaAlta() {
        return fechaAlta;
    }

    public long cantMesesComoAdmin() {
        LocalDate ahora = LocalDate.now();
        return fechaAlta.until(ahora, ChronoUnit.MONTHS);
    }
    public String getContrasenia() {
        return contraseña;
    }


    public void agregarDispositivoDB(Dispositivo dispositivo) {
      //  dispositivoABM.agregarDispositivoBD(dispositivo);
		/*
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ejemplo");
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		try {

			t.begin();
			em.persist(dispositivo);
			t.commit();
			System.out.println("se inserto dispositivo ");
		} catch (Exception e) {

			t.rollback();
			System.out.println("Fallo" + e.getMessage());

		} finally {
			em.close();
		}
		*/

    }

    public void editarDispositivoDB(Long idDispositivoViejo, DispositivoEstandar dispositivoNuevo) {
        //dispositivoABM.modificarDispositivoBD(idDispositivoViejo, dispositivoNuevo);
		/*
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ejemplo2");
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		try {

			Dispositivo d = em.find(Dispositivo.class, idviejo);

			d = nuevo;


			t.begin();
			em.merge(d);
			t.commit();
			System.out.println("se modifico dispositivo ");
		} catch (Exception e) {

			t.rollback();
			System.out.println("Fallo" + e.getMessage());

		} finally {
			em.close();
		}*/

    }


    public void eliminarDispositivoDB(Long idDispositivoEliminar) {
        //dispositivoABM.eliminarDispositivoInteligenteBD(idDispositivoEliminar);

		/*
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ejemplo2");
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();


		try {


			Dispositivo d= em.find(Dispositivo.class,id);




			t.begin();
			em.remove(d);
			t.commit();
			System.out.println("se borro dispositivo ");
		}


		catch(Exception e){

			t.rollback();
			System.out.println("Fallo" +e.getMessage());

		}

		finally {
			em.close();
		}*/


    }



    }

