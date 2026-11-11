package utez.edu.mx.CBRR_Apreza_Carlos_CC7.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Producto {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@NotBlank
private String nombre;
private String categoria;
private Double precio;
private Integer stock;
private boolean activo;
}
