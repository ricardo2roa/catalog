package ws.infraestructura.error.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ErrorDetalleDTO {
    private String code;
    private String message;
}
