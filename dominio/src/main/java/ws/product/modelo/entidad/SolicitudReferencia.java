package ws.product.modelo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SolicitudReferencia {
    private final long peso;
    private final long precio;
    private final int stock;
    private final String codeImg;
}
