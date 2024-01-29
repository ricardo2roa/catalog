package ws.product.comando;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ws.brand.modelo.dto.BrandDTO;
import ws.information.modelo.dto.InformationDTO;
import ws.reference.modelo.dto.ReferenceDTO;

import java.util.List;
@Data
@NoArgsConstructor
public class ComandoSolicitudCrearEnvio {
    private int tag;
    private int category;
    private int brand;
    private String name;
    private InformationDTO information;
    private List<ReferenceDTO> references;
}
