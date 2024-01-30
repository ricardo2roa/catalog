package ws.product.modelo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ws.brand.modelo.dto.BrandDTO;
import ws.information.modelo.dto.InformationDTO;
import ws.reference.modelo.dto.ReferenceDTO;
import java.util.List;

@Getter
@AllArgsConstructor
public class SolicitudCrearProducto {
    private final SolicitudProducto producto;
    private final List<MultipartFile> imageReference;
}
