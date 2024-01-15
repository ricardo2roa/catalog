package ws.reference.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReferenceDTO {
    private long peso;
    private long precio;
    private String sku;
    private int stock;
}
