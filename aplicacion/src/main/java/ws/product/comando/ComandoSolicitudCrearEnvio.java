package ws.product.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ws.brand.modelo.dto.BrandDTO;
import ws.information.modelo.dto.InformationDTO;
import ws.reference.modelo.dto.ReferenceDTO;

import java.util.List;
@Getter
@AllArgsConstructor
public class ComandoSolicitudCrearEnvio {
    private int tag;
    private int category;
    private BrandDTO brand;
    private String name;
    private InformationDTO information;
    private List<ReferenceDTO> references;
}
