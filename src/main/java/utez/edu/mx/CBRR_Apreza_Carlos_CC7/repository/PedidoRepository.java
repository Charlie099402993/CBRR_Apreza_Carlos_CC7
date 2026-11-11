package utez.edu.mx.CBRR_Apreza_Carlos_CC7.repository;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.CBRR_Apreza_Carlos_CC7.model.EstadoPedido;
import utez.edu.mx.CBRR_Apreza_Carlos_CC7.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
}
