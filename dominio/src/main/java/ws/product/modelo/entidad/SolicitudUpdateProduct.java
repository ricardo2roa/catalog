package ws.product.modelo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ws.information.modelo.dto.InformationDTO;

@Getter
@AllArgsConstructor
public class SolicitudUpdateProduct {
    private final int tag;
    private final int category;
    private final int brand;
    private final String name;
    private final InformationDTO information;
}
