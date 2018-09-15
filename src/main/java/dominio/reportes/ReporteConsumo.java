package dominio.reportes;

import dominio.usuario.Cliente;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReporteConsumo implements WithGlobalEntityManager {

    /*clase creada en correcion del TPPersistencia
    public ReporteConsumo obtenerReporte (List<Long> clientesId, String desde, String hasta) {

        Long idCliente = null;
        Cliente cliente = entityManager().find(Cliente.class, idCliente);

        ;
        LocalDateTime from = LocalDateTime.parse((desde), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDateTime to = LocalDateTime.parse((hasta), DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        cliente.getPromedioConsumo(from, to);
    }*/
}
