package utez.edu.mx.CBRR_Apreza_Carlos_CC7.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
 @NoArgsConstructor
@Getter
@Setter
public class CrearPedido {
    @NotBlank
private String nombre;
    private Long idPedido;
@Positive
    private Integer cantidadSolicitada;
}
