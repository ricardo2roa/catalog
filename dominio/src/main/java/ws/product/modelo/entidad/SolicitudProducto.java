package ws.product.modelo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ws.information.modelo.dto.InformationDTO;
import ws.reference.modelo.dto.ReferenceDTO;

import java.util.List;

@Getter
@AllArgsConstructor
public class SolicitudProducto {
    private final int tag;
    private final int category;
    private final int brand;
    private final String name;
    private final InformationDTO information;
    private final List<SolicitudReferencia> references;
}
