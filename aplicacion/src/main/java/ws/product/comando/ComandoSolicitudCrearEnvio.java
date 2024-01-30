package ws.product.comando;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ws.information.modelo.dto.InformationDTO;
import ws.reference.modelo.dto.ReferenceDTO;

import java.util.List;
@Getter
@NoArgsConstructor
public class ComandoSolicitudCrearEnvio {
    private int tag;
    private int category;
    private int brand;
    private String name;
    private InformationDTO information;
    private List<ReferenceDTO> references;
}
