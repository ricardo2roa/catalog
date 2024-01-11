package ws.product.modelo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ws.brand.modelo.dto.BrandDTO;
import ws.information.modelo.dto.InformationDTO;
import ws.reference.modelo.dto.ReferenceDTO;
import java.util.List;

@Getter
@AllArgsConstructor
public class SolicitudCrearProducto {
    private final int tag;
    private final int category;
    private final BrandDTO brand;
    private final String name;
    private final InformationDTO information;
    private final List<ReferenceDTO> references;
}
