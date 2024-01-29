package ws.error.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ErrorDTO {
    private ErrorDetalleDTO error;
}
