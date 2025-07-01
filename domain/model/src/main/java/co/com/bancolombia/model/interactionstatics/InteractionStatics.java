package co.com.bancolombia.model.interactionstatics;

import lombok.*;
//import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class InteractionStatics {
    private Integer totalContactoClientes;

    private Integer motivoReclamo;

    private Integer motivoGarantia;

    private Integer motivoDuda;

    private Integer motivoCompra;

    private Integer motivoFelicitaciones;

    private Integer motivoCambio;

    private String hash;
}
