package utez.edu.mx.CBRR_Apreza_Carlos_CC7.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String nombreCliente;
    @Positive
    private Integer cantidad;
private Double precioUnitario;
private Double total;
@Enumerated(EnumType.STRING)
private EstadoPedido estadoPedido;
@ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

}
